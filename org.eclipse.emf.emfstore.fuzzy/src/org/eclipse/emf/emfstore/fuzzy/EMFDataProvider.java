package org.eclipse.emf.emfstore.fuzzy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.emfstore.client.model.ProjectSpace;
import org.eclipse.emf.emfstore.fuzzy.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyDataProvider;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutator;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorConfiguration;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorUtil;
import org.junit.runner.notification.RunListener;
import org.junit.runners.model.TestClass;

public class EMFDataProvider implements FuzzyDataProvider<EObject> {
	
	private Random random;
	
	private EPackage ePackage;
	
	private int count;
	
	private int seedCount;
	
	private TestClass testClass;

	private TestRun testRun;
	
	private Resource runResource;

	private TestConfig config;

	private AdapterFactoryEditingDomain editingDomain;
		
	@Override
	public void init(){
				
		// load testconfig from file			
		editingDomain = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack());
		
		Resource loadResource = editingDomain.createResource(FuzzyConstants.TEST_CONFIG_PATH);
		try {			
			loadResource.load(null);			
		} catch (IOException e) {
			throw new RuntimeException("Could not load " + FuzzyConstants.TEST_CONFIG_PATH, e);
		}

		// get the testconfig fitting to the current testclass
		config = getTestConfig(loadResource, testClass);
				
		// create new config for one run
		long testTime = System.currentTimeMillis();
		
		runResource = editingDomain.createResource(FuzzyConstants.PROTOCOL_PREFIX + getRunPath(testTime, config));
		
		runResource.getContents().add(config);
		
		// init variables
		random = new Random(config.getSeed());
		count = config.getCount();
		seedCount = 0;

		ePackage = ModelMutatorUtil.getEPackage(config.getNsURI());
		if(ePackage == null){
			throw new IllegalArgumentException("Wrong configuration: Could not find the EPackage with the nsURI : " + config.getNsURI());
		}
		
		// create new TestRun with config
		testRun = ConfigFactory.eINSTANCE.createTestRun();
		testRun.setConfig(config);
		testRun.setTime(new Date(testTime));
	}
	
	@Override
	public EObject next() {		
		ProjectSpace projectSpace = createProjectSpace();
		
		// create a new configuration for the next model
		ModelMutatorConfiguration mmc = new ModelMutatorConfiguration(ePackage,
				projectSpace.getProject(), random.nextLong());
		
		// generate the model
		ModelMutator.generateModel(mmc);
		
		seedCount++;
			
		return projectSpace;	
	}
		
	@SuppressWarnings("unchecked")
	public void finish(){
		// add testrun
		EList<EObject> contents = runResource.getContents();
		contents.add(testRun);
		
		// add testresults of testrun
		contents.addAll(testRun.getResults());
		
		try {
			// save the resource
			runResource.save(null);
			
			// add a new config file if it does not exist
			String configPath = getConfigPath(config);
			Resource configResource = editingDomain.createResource(FuzzyConstants.PROTOCOL_PREFIX + configPath + FuzzyConstants.CONFIG_FILE);
			configResource.getContents().add(config);
			configResource.save(null);
			
			// add this run to the runs file
			FileWriter fw = new FileWriter(configPath + FuzzyConstants.RUNS_FILE, true);			
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(String.valueOf(testRun.getTime().getTime()));
			bw.newLine();
			bw.close();
			fw.close();
		} catch (IOException e) {
			throw new RuntimeException("Could not save the config after running!", e);
		}
	}
	
	@Override
	public int size() {
		return count;
	}

	@Override
	public void setTestClass(TestClass testClass) {
		this.testClass = testClass;
	}
	
	@Override
	public List<RunListener> getListener() {
		return Arrays.asList(
				new RunListener[]{new EMFRunListener(this, testRun)});
	}
	
	public int getCurrentSeedCount(){
		return seedCount;
	}
	
	private static TestConfig getTestConfig(Resource resource, TestClass testClass){
		// TODO modify/check to be more robust against wrong input
		// TODO add a standard TestConfig? e.g. where clazz = null / or testconfig for complete packages
		for(EObject object : resource.getContents()){
			if(object instanceof TestConfig){
				TestConfig config = (TestConfig) object;
				Class<?> clazz = config.getTestClass();
				if(clazz.getName().equals(testClass.getJavaClass().getName())){
					return config;
				}
			}
		}
		
		throw new IllegalArgumentException("No fitting testconfig for " + 
					testClass.getName() + " in " + FuzzyConstants.TEST_CONFIG_PATH + " found.");		
	}
	
	public static String getRunPath(long millis, TestConfig config){
		String runPath = String.valueOf(millis).substring(0, 5) + FuzzyConstants.PATH_SEPARATOR + millis + FuzzyConstants.XML_SUFFIX;
		return getConfigPath(config) + runPath;
	}
	
	public static String getConfigPath(TestConfig config){
		return FuzzyConstants.CONFIG_FOLDER + FuzzyConstants.PATH_SEPARATOR + config.getId() + FuzzyConstants.PATH_SEPARATOR;
	}
	
	private static ProjectSpace createProjectSpace() {
		ProjectSpace projectSpace = org.eclipse.emf.emfstore.client.model.ModelFactory.eINSTANCE.createProjectSpace();
		projectSpace.setProject(org.eclipse.emf.emfstore.common.model.ModelFactory.eINSTANCE.createProject());
		projectSpace.setProjectName("Project");
		projectSpace.setProjectDescription("Project created by EMFDataProvider");	
		return projectSpace;
	}
}

package org.eclipse.emf.emfstore.fuzzy.emfdataprovider;

import java.io.IOException;
import java.util.Arrays;
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
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun;
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
	
	public static final String FILE_PATH = "fuzzyConfig.xml";
	
	private Resource resource;
		
	@Override
	public void init(){
				
		// load config from file			
		resource = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack()).createResource(FILE_PATH);
		try {			
			resource.load(null);			
		} catch (IOException e) {
			throw new RuntimeException("Could not load " + FILE_PATH, e);
		}
		
		// get the testconfig fitting to the current testclass
		TestConfig config = getTestConfig(resource);
		
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
	
	@SuppressWarnings("unchecked")
	public void finish(){
		// add testrun
		EList<EObject> contents = resource.getContents();
		contents.add(testRun);
		
		// add testresults of testrun
		EList<TestResult> results = testRun.getResults();
		for(TestResult result : results){
			contents.add(result);
		}
		
		try {
			resource.save(null);
		} catch (IOException e) {
			throw new RuntimeException("Could not save the config after running!", e);
		}
	}
	
	public int getCurrentSeedCount(){
		return seedCount;
	}
	
	private TestConfig getTestConfig(Resource resource){
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
					testClass.getName() + " in " + FILE_PATH + " found.");		
	}
	
	private ProjectSpace createProjectSpace() {
		ProjectSpace projectSpace = org.eclipse.emf.emfstore.client.model.ModelFactory.eINSTANCE.createProjectSpace();
		projectSpace.setProject(org.eclipse.emf.emfstore.common.model.ModelFactory.eINSTANCE.createProject());
		projectSpace.setProjectName("Project");
		projectSpace.setProjectDescription("Project created by EMFDataProvider");	
		return projectSpace;
	}

}

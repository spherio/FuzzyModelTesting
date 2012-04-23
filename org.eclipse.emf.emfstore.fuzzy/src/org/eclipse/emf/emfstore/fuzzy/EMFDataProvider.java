package org.eclipse.emf.emfstore.fuzzy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
import org.eclipse.emf.emfstore.fuzzy.config.TestDiff;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;
import org.eclipse.emf.emfstore.fuzzy.diff.DiffGenerator;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyDataProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.Test;
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
	
	private TestConfig config;

	private AdapterFactoryEditingDomain editingDomain;
	
	// TODO add possibility to configure filterTests
	private boolean filterTests = false;
		
	@Override
	public void init(){
				
		// load testconfig from file			
		editingDomain = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack());
		
		Resource loadResource = editingDomain.createResource(FuzzyUtil.TEST_CONFIG_PATH);
		try {			
			loadResource.load(null);			
		} catch (IOException e) {
			throw new RuntimeException("Could not load " + FuzzyUtil.TEST_CONFIG_PATH, e);
		}

		// get the testconfig fitting to the current testclass
		config = FuzzyUtil.getTestConfig(loadResource, testClass);
		
		// add a new config file if it does not exist
		Resource configResource = editingDomain.createResource(FuzzyUtil.PROTOCOL_PREFIX + FuzzyUtil.getConfigPath(config) + FuzzyUtil.CONFIG_FILE);
		configResource.getContents().add(config);
		try {
			configResource.save(null);
		} catch (IOException e) {
			throw new RuntimeException("Could not save config!", e);
		}
						
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
		testRun.setTime(new Date());
	}
	
	@Override
	public EObject next() {		
		ProjectSpace projectSpace = FuzzyUtil.createProjectSpace();
		
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
		// create run resource
		Resource runResource = editingDomain.createResource(FuzzyUtil.PROTOCOL_PREFIX + FuzzyUtil.getRunPath(testRun.getTime().getTime(), config));
		EList<EObject> contents = runResource.getContents();
		contents.add(testRun);
		
		// add testresults of testrun
		contents.addAll(testRun.getResults());
		
		try {
			// save the resource
			runResource.save(null);
			
			// add this run to the runs file
			FileWriter fw = new FileWriter(FuzzyUtil.getConfigPath(config) + FuzzyUtil.RUNS_FILE, true);			
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
	
	@Override
	public List<Test> getTestsToRun() {
		if(!filterTests){
			return null;
		} 
		
		Resource diffResource = editingDomain.createResource(DiffGenerator.DIFF_PATH);
		
		try {
			diffResource.load(null);
		} catch (IOException e) {
			throw new RuntimeException("Could not load diff file!", e);
		}
		
		EList<EObject> contents = diffResource.getContents();
		List<Test> tests = new ArrayList<Test>();
		for(EObject obj : contents){
			if(obj instanceof TestDiff){
				TestDiff diff = (TestDiff) obj;
				if(diff.getConfig().equals(config)){
					TestResult result = FuzzyUtil.getValidTestResult(diff);
					tests.add(new Test(result.getTestName(), result.getSeedCount()));
				}
			}
		}
		
		return tests;
	}
	
	public int getCurrentSeedCount(){
		return seedCount;
	}
}

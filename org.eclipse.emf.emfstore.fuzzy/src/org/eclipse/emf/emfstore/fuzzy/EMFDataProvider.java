package org.eclipse.emf.emfstore.fuzzy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfstore.client.model.ProjectSpace;
import org.eclipse.emf.emfstore.common.model.ModelPackage;
import org.eclipse.emf.emfstore.fuzzy.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestDiff;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;
import org.eclipse.emf.emfstore.fuzzy.diff.HudsonTestRunProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyDataProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.Test;
import org.eclipse.emf.emfstore.fuzzy.junit.Util;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutator;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorConfiguration;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorUtil;
import org.junit.runner.notification.RunListener;
import org.junit.runners.model.TestClass;

/**
 * This implementation of a {@link FuzzyDataProvider} provides generated models using the functionality of {@link ModelMutator}.
 * <br><br>
 * The run of a test is based on a {@link TestConfig}, defining model etc.
 * <br><br>
 * During the run it records {@link TestResult}s to create a {@link TestRun} for reporting purpose.
 *  
 * @author Julian Sommerfeldt
 *
 */
public class EMFDataProvider implements FuzzyDataProvider<EObject> {
	
	private Random random;
	
	private EPackage ePackage;
	
	private int count;
	
	private int seedCount;
	
	private TestClass testClass;

	private TestRun testRun;
	
	private TestConfig config;
	
	private boolean filterTests;
	
	private String configFile;
	
	public static final String PROP_EMFDATAPROVIDER = ".emfdataprovider";
		
	public static final String PROP_CONFIGS_FILE = ".configsFile";
	
	private static Collection<EStructuralFeature> featuresToIgnore;
	
	@Override
	public void init(){
		// fill properties like the config file
		fillProperties();
				
		// load testconfig from file
		Resource loadResource = FuzzyUtil.createResource(configFile);
		try {			
			loadResource.load(null);
		} catch (IOException e) {
			throw new RuntimeException("Could not load " + configFile, e);
		}
		
		// get the testconfig fitting to the current testclass
		config = FuzzyUtil.getTestConfig(loadResource, testClass);
		
		// add the config to the configs file
		addToConfigFile();
								
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
	
	/**
	 * Add the config to the file containing all configs.
	 */
	private void addToConfigFile() {
		Resource resource = FuzzyUtil.createResource(FuzzyUtil.ARTIFACT_FOLDER + FuzzyUtil.TEST_CONFIG_FILE);
		try {
			if(FuzzyUtil.resourceExists(resource)){
				resource.load(null);
			}
			if(!FuzzyUtil.containsConfig(resource, config)){
				resource.getContents().add(config);
				resource.save(null);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not save config!", e);
		}		
	}

	@Override
	public EObject next() {		
		ProjectSpace projectSpace = FuzzyUtil.createProjectSpace();
		
		// create a new configuration for the next model
		ModelMutatorConfiguration mmc = new ModelMutatorConfiguration(ePackage,
				projectSpace.getProject(), random.nextLong());		
		mmc.seteStructuralFeaturesToIgnore(getFeaturesToIgnore());
		
		// generate the model
		ModelMutator.generateModel(mmc);
		
		seedCount++;
			
		return projectSpace;	
	}
		
	public void finish(){
		// create run resource
		Resource runResource = FuzzyUtil.createResource(FuzzyUtil.ARTIFACT_FOLDER + FuzzyUtil.RUN_FOLDER + config.getId() + FuzzyUtil.FILE_SUFFIX);
		runResource.getContents().add(testRun);

		try {
			runResource.save(null);
		} catch (IOException e) {
			throw new RuntimeException("Could not save the run result after running!", e);
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
								
		Resource diffResource = HudsonTestRunProvider.getDiffResource();
		
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
				if(diff.getConfig().getId().equals(config.getId())){
					TestResult result = FuzzyUtil.getValidTestResult(diff);
					tests.add(new Test(result.getTestName(), result.getSeedCount()));
				}
			}
		}
		
		return tests;
	}
	
	/**
	 * @return The current seed used to create the model
	 */
	public int getCurrentSeedCount(){
		return seedCount;
	}
	
	public EPackage getEPackage(){
		return ePackage;
	}
	
	private void fillProperties(){		
		String filterTests = System.getProperty("filterTests");
		if(filterTests == null){
			this.filterTests = false;
		} else {
			this.filterTests = Boolean.parseBoolean(filterTests);
		}
		configFile = FuzzyUtil.getProperty(PROP_EMFDATAPROVIDER + PROP_CONFIGS_FILE, FuzzyUtil.TEST_CONFIG_PATH);	
	}

	@Override
	public Util getUtil() {
		return new MutateUtil(this);
	}
	
	public static Collection<EStructuralFeature> getFeaturesToIgnore(){
		if(featuresToIgnore == null){
			featuresToIgnore = new ArrayList<>();
			featuresToIgnore.add(ModelPackage.eINSTANCE.getProject_CutElements());
		}
		return featuresToIgnore;
	}
}

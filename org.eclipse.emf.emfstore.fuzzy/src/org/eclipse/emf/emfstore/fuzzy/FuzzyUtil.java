package org.eclipse.emf.emfstore.fuzzy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.emfstore.client.model.ProjectSpace;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestDiff;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;
import org.junit.runners.model.TestClass;

public class FuzzyUtil {
	
	// TODO make folders etc configurable
			
	public static final String FUZZY_FOLDER = "fuzzy/";
	
	public static final String ARTIFACT_FOLDER = "../" + FUZZY_FOLDER;
	
	public static final String RUN_FOLDER = "testruns/";
	
	public static final String PATH_SEPARATOR = System.getProperty("file.separator");

	public static final String XML_SUFFIX = ".xml";
	
	public static final String TEST_CONFIG_FILE = "fuzzyConfig.xml";
	
	public static final String TEST_CONFIG_PATH = FUZZY_FOLDER + TEST_CONFIG_FILE;
			
	public static final String DIFF_FILE = ARTIFACT_FOLDER + "diff" + XML_SUFFIX;
	
	public static final String PROPERTIES_FILE = FUZZY_FOLDER + "fuzzy.properties";
	
	public static final String PROP_PRE = "fuzzy";
	
	private static final AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack());

	private static Properties properties;
	
	public static TestConfig getTestConfig(Resource resource, TestClass testClass){
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
					testClass.getName() + " in " + resource.getURI() + " found.");		
	}
	
	public static boolean containsConfig(Resource resource, TestConfig config){
		for(EObject obj : resource.getContents()){
			if (obj instanceof TestConfig) {
				TestConfig c = (TestConfig) obj;
				if(c.getId().equals(config.getId())){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean resourceExists(Resource resource){
		return resource.getResourceSet().getURIConverter().exists(resource.getURI(), null);
	}
	
	public static Resource createResource(String fileNameURI){
		return editingDomain.createResource(fileNameURI);
	}
	
	public static TestResult getValidTestResult(TestDiff diff){
		TestResult result = diff.getOldResult();
		if(result != null){
			return result;
		}
		result = diff.getNewResult();
		if(result != null){
			return result;
		}
		throw new RuntimeException("Configuration of TestDiff is wrong! (Does not contain any TestResult)");
	}
	
	public static ProjectSpace createProjectSpace() {
		ProjectSpace projectSpace = org.eclipse.emf.emfstore.client.model.ModelFactory.eINSTANCE.createProjectSpace();
		projectSpace.setProject(org.eclipse.emf.emfstore.common.model.ModelFactory.eINSTANCE.createProject());
		projectSpace.setProjectName("Project");
		projectSpace.setProjectDescription("Project created by EMFDataProvider");	
		return projectSpace;
	}	
	
	public static String getProperty(String key, String defaultValue){
		initProperties();
		return properties.getProperty(PROP_PRE + key, defaultValue);
	}
		
	private static void initProperties(){
		if(properties != null){
			return;
		}
		
		File file = new File(PROPERTIES_FILE);
		properties = new Properties();
		 
		if(file.exists()){
			try {
				FileInputStream fs = new FileInputStream(file);				
				properties.load(fs);				
				fs.close();
			} catch (IOException e) {
				throw new RuntimeException("Could not load properties from " + file.getAbsolutePath(), e);
			}
		}
	}
}

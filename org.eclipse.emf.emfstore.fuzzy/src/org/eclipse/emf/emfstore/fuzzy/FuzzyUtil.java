package org.eclipse.emf.emfstore.fuzzy;

import java.io.BufferedInputStream;
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
	
	public static final String PROTOCOL_PREFIX = "file://";
	
	public static final String CONFIG_FOLDER = "D:/downloads/fuzzy";
	
	public static final String FUZZY_FOLDER = "fuzzy";
	
	public static final String PATH_SEPARATOR = System.getProperty("file.separator");

	public static final String XML_SUFFIX = ".xml";
	
	public static final String TEST_CONFIG_PATH = PROTOCOL_PREFIX + "D:/downloads/fuzzyConfig.xml";
	
	public static final String CONFIG_FILE = "config.xml";
	
	public static final String RUNS_FILE = "runs.txt";
	
	public static final String DIFF_FILE = FuzzyUtil.CONFIG_FOLDER + "diff" + FuzzyUtil.XML_SUFFIX;
	
	public static final String PROPERTIES_FILE = "fuzzy.properties";
	
	public static final String PROP_PRE = "fuzzy";
	
	private static final AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack());

	private static Properties properties;
	
	public static TestConfig getTestConfig(Resource resource, TestClass testClass){
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
					testClass.getName() + " in " + resource.getURI() + " found.");		
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
	
	public static String getRunPath(long millis, TestConfig config){
		String runPath = String.valueOf(millis).substring(0, 5) + PATH_SEPARATOR + millis + XML_SUFFIX;
		return getConfigPath(config) + runPath;
	}
	
	public static String getConfigPath(TestConfig config){
		return CONFIG_FOLDER + PATH_SEPARATOR + config.getId() + PATH_SEPARATOR;
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
				BufferedInputStream bis = new BufferedInputStream(fs);
				properties.load(bis);
				bis.close();
				fs.close();
			} catch (IOException e) {
				throw new RuntimeException("Could not load properties from " + file.getAbsolutePath(), e);
			}
		}
	}
}

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
import org.eclipse.emf.emfstore.client.model.Workspace;
import org.eclipse.emf.emfstore.client.model.WorkspaceManager;
import org.eclipse.emf.emfstore.client.model.util.EMFStoreCommand;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestDiff;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;
import org.junit.runners.model.TestClass;

/**
 * Utility class for different methods used in {@link EMFDataProvider} context.
 * 
 * @author Julian Sommerfeldt
 *
 */
public class FuzzyUtil {
	
	// TODO make folders etc configurable
	/**
	 * The main folder containing all files. 		
	 */
	public static final String FUZZY_FOLDER = "fuzzy/";
	
	/**
	 * The folder where to put the artifacts.
	 */
	public static final String ARTIFACT_FOLDER = "../" + FUZZY_FOLDER;
	
	/**
	 * The folder where to store the {@link TestRun}s.
	 */
	public static final String RUN_FOLDER = "testruns/";
	
	/**
	 * The file suffix for the files.
	 */
	public static final String FILE_SUFFIX = ".xml";
	
	/**
	 * The file containing the {@link TestConfig}s.
	 */
	public static final String TEST_CONFIG_FILE = "fuzzyConfig.xml";
	
	/**
	 * The path to the TEST_CONFIG_FILE.
	 */
	public static final String TEST_CONFIG_PATH = FUZZY_FOLDER + TEST_CONFIG_FILE;
			
	/**
	 * The path to the file containing the {@link TestDiff}.
	 */
	public static final String DIFF_FILE = ARTIFACT_FOLDER + "diff" + FILE_SUFFIX;
	
	/**
	 * The path to the properties file. 
	 */
	public static final String PROPERTIES_FILE = FUZZY_FOLDER + "fuzzy.properties";
	
	/**
	 * The prefix for all fuzzy properties in the properties file.
	 */
	public static final String PROP_PRE = "fuzzy";
	
	private static final AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack());

	private static Properties properties;
	
	/**
	 * Searches in the resource for a {@link TestConfig} fitting to the given {@link TestClass}.
	 * 
	 * @param resource The resource where to search in.
	 * @param testClass The TestClass to which the {@link TestConfig} should fit.
	 * @return The {@link TestConfig} fitting to the {@link TestClass}.
	 * @throws IllegalArgumentException If the resource does not contain a fitting {@link TestConfig}.
	 */
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
	
	/**
	 * Checks if a resource contains a {@link TestConfig}.
	 * 
	 * @param resource The resource where to search in.
	 * @param config The {@link TestConfig} to check.
	 * @return <code>true</code> if the resource contains the {@link TestConfig}, else <code>false</code>.
	 */
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
	
	/**
	 * Checks if the resource exists.
	 * 
	 * @param resource
	 * @return <code>true</code> if the resource exists, <code>false</code> otherwise.
	 */
	public static boolean resourceExists(Resource resource){
		return resource.getResourceSet().getURIConverter().exists(resource.getURI(), null);
	}
	
	/**
	 * Create a new {@link Resource}.
	 * 
	 * @param fileNameURI The uri of the resource.
	 * @return The newly created {@link Resource}.
	 */
	public static Resource createResource(String fileNameURI){
		return editingDomain.createResource(fileNameURI);
	}
	
	/**
	 * Get a valid {@link TestResult} out of a {@link TestDiff}. Valid means non null.
	 * 
	 * @param diff The {@link TestDiff} containing the {@link TestResult}.
	 * @return The valid {@link TestResult} of the {@link TestDiff}.
	 * @throws RuntimeException If the {@link TestDiff} contains no valid {@link TestResult}. 
	 */
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
	
	private static ProjectSpace localProject;
	
	/**
	 * @return A new {@link ProjectSpace}.
	 */
	public static ProjectSpace createProjectSpace() {
		final Workspace workspace = WorkspaceManager.getInstance().getCurrentWorkspace();
		new EMFStoreCommand() {

			@Override
			protected void doRun() {
				localProject = workspace.createLocalProject("testProject", "test Project");				
			}
		}.run(false);
		
		return localProject;
		
//		ProjectSpace projectSpace = org.eclipse.emf.emfstore.client.model.ModelFactory.eINSTANCE.createProjectSpace();
//		projectSpace.setProject(org.eclipse.emf.emfstore.common.model.ModelFactory.eINSTANCE.createProject());
//		projectSpace.setProjectName("Project");
//		projectSpace.setProjectDescription("Project created by EMFDataProvider");	
//		return projectSpace;
	}	
	
	/**
	 * Get a property out of the properties file.
	 * 
	 * @param key The key of the property.
	 * @param defaultValue The value if the properties do not contain the key. 
	 * @return The value if the properties contain the key or the defaultValue if not.
	 */
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

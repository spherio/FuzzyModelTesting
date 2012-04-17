package org.eclipse.emf.emfstore.fuzzy.emfdataprovider;

import java.io.IOException;
import java.util.Random;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.emfstore.client.model.ProjectSpace;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyDataProvider;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutator;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorConfiguration;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorUtil;
import org.junit.runners.model.TestClass;

public class EMFDataProvider implements FuzzyDataProvider<EObject> {

	private Random random;
	
	private EPackage ePackage;
	
	private int count;
	
	private TestClass testClass;
	
	public static final String FILE_PATH = "fuzzyConfig.xml";
	
	@Override
	public void init(){
				
		// load config from file
		URI fileURI = URI.createFileURI(FILE_PATH);
		Resource resource = new XMLResourceFactoryImpl().createResource(fileURI);
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

		ePackage = ModelMutatorUtil.getEPackage(config.getNsURI());
		if(ePackage == null){
			throw new IllegalArgumentException("Wrong configuration: Could not find the EPackage with the nsURI : " + config.getNsURI());
		}
	}
	
	@Override
	public EObject next() {
		ProjectSpace projectSpace = createProjectSpace();
		
		// create a new configuration for the next model
		ModelMutatorConfiguration mmc = new ModelMutatorConfiguration(ePackage,
				projectSpace.getProject(), random.nextLong());
		
		// generate the model
		ModelMutator.generateModel(mmc);
			
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
	
	private TestConfig getTestConfig(Resource resource){
		// TODO modify/check to be more robust against wrong input
		// TODO add a standard TestConfig? e.g. where clazz = null / or testconfig for complete packages
		for(EObject object : resource.getContents()){
			if(object instanceof TestConfig){
				TestConfig dataProvider = (TestConfig) object;
				Class<?> clazz = dataProvider.getTestClass();
				if(clazz.getName().equals(testClass.getJavaClass().getName())){
					return dataProvider;
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

package org.eclipse.emf.emfstore.fuzzy.test;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.EMFDataProvider;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig;
import org.junit.Test;

public class ConfigCreation {
	
	private static final String MODEL = "http://org/eclipse/example/bowling";

	@Test
	public void createConfig(){
		TestConfig config = ConfigFactory.eINSTANCE.createTestConfig();
		config.setNsURI(MODEL);
		config.setSeed(1);
		config.setCount(5);
		config.setTestClass(MyTest.class);
		
		URI fileURI = URI.createFileURI(EMFDataProvider.FILE_PATH);
		Resource poResource = new XMLResourceFactoryImpl().createResource(fileURI);
		poResource.getContents().add(config);
		try {
			poResource.save(null);
			System.out.println("saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

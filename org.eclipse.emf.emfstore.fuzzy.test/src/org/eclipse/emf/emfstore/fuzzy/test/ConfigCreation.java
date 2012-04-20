package org.eclipse.emf.emfstore.fuzzy.test;

import java.io.IOException;

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
		
		TestConfig config2 = ConfigFactory.eINSTANCE.createTestConfig();
		config2.setNsURI(MODEL);
		config2.setSeed(10);
		config2.setCount(90);
		config2.setTestClass(ConfigCreation.class);
				
		EMFDataProvider.resource.getContents().add(config);
		EMFDataProvider.resource.getContents().add(config2);
		
		try {
			EMFDataProvider.resource.save(null);
			System.out.println("saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package org.eclipse.emf.emfstore.fuzzy.test;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.emfstore.fuzzy.FuzzyUtil;
import org.eclipse.emf.emfstore.fuzzy.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.junit.Test;

public class ConfigCreation {
	
	private static final String MODEL = "http://org/eclipse/example/bowling";

//	@Test
	public void createConfig(){
		TestConfig config = ConfigFactory.eINSTANCE.createTestConfig();
		config.setNsURI(MODEL);
		config.setSeed(1);
		config.setCount(1);
		config.setTestClass(MyTest.class);
		config.setId("_sLQasYvPEeG1B5HDFm-I-w");
		
		TestConfig config2 = ConfigFactory.eINSTANCE.createTestConfig();
		config2.setNsURI(MODEL);
		config2.setSeed(1);
		config2.setCount(1);
		config2.setTestClass(SecondTest.class);
		config2.setId("_C11qUIvSEeGC3oFJZ-5VgQ");
		
		Resource resource = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack()).createResource(FuzzyUtil.TEST_CONFIG_PATH);
				
		resource.getContents().add(config);		
		resource.getContents().add(config2);
		
		try {
			resource.save(null);
			System.out.println("saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void readConfig(){
		Resource resource = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack()).createResource(FuzzyUtil.TEST_CONFIG_PATH);
				
		try {
			resource.load(null);
			
			List<EObject> list = resource.getContents();
			for (EObject obj : list) {
				if(obj instanceof TestConfig){
					TestConfig res = (TestConfig) obj;					
					System.out.println(res);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

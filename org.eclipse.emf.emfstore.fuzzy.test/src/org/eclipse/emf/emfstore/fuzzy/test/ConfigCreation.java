package org.eclipse.emf.emfstore.fuzzy.test;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.EMFDataProvider;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig;

public class ConfigCreation {
	
	private static final String MODEL = "http://org/eclipse/example/bowling";

	//@Test
	public void createConfig(){
		TestConfig config = ConfigFactory.eINSTANCE.createTestConfig();
		config.setNsURI(MODEL);
		config.setSeed(1);
		config.setCount(5);
		config.setTestClass(MyTest.class);
		
		Resource resource = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack()).createResource(EMFDataProvider.TEST_CONFIG_PATH);
				
				
		resource.getContents().add(config);
		
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
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack()).createResource(EMFDataProvider.TEST_CONFIG_PATH);
				
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

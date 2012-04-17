package org.eclipse.emf.emfstore.fuzzy.test;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.EMFDataProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyRunner;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.Data;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.DataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(FuzzyRunner.class)
@DataProvider(EMFDataProvider.class)
public class MyTest {
	
	@Data
	private EObject model;
		
	@Test
	public void test(){
		System.out.println("##############################");
		printElements(model);
		System.out.println();
	}
	
	private void printElements(EObject root){
		for(EObject obj : root.eContents()){
			System.out.println(obj);
			printElements(obj);
		}
	}
}

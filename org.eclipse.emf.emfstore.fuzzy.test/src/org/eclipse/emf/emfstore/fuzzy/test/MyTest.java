package org.eclipse.emf.emfstore.fuzzy.test;

import java.util.Random;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.emfstore.fuzzy.EMFDataProvider;
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
		printElements(model);
	}
	
//	@Test
//	public void test2(){
//		if(new Random().nextInt(2) == 0){
//			Assert.assertTrue(false);
//		}
//	}
	
	private void printElements(EObject root){
		for(EObject obj : root.eContents()){
			System.out.println(obj);
			printElements(obj);
		}
	}
}

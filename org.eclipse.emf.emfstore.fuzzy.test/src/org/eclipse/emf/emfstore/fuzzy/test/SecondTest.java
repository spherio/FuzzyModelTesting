package org.eclipse.emf.emfstore.fuzzy.test;

import java.util.Random;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.emfstore.fuzzy.EMFDataProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.Data;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.DataProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(FuzzyRunner.class)
@DataProvider(EMFDataProvider.class)
public class SecondTest {
	
	@Data
	private EObject obj;
	
	@Test
	public void secondTest(){	
		if(new Random().nextInt(2) == 0){
			throw new RuntimeException("test");
		}
	}
	
	@Test
	public void fail(){
		if(new Random().nextInt(2) == 0){
			Assert.assertTrue(false);
		}
	}
}

package org.eclipse.emf.emfstore.fuzzy.junit;

import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.Data;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.DataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Example class to illustrate the use of {@link FuzzyRunner}.
 * 
 * @author Julian Sommerfeldt
 *
 */
@RunWith(FuzzyRunner.class)
@DataProvider(IntDataProvider.class)
public class MyTest {
	
	@Data
	private int i;	
		
	@Test
	public void test(){
		System.out.println(i);
	}
}

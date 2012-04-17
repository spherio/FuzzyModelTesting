package org.eclipse.emf.emfstore.fuzzy.junit;

import java.util.Random;

import org.junit.runners.model.TestClass;

/**
 * Sample implementation of {@link FuzzyDataProvider}.
 * 
 * @author Julian Sommerfeldt
 *
 */
public class IntDataProvider implements FuzzyDataProvider<Integer> {
	
	private Random random;
	
	private TestClass testClass;

	@Override
	public void init() {	
		random = new Random();
	}
		
	@Override
	public Integer next() {
		return random.nextInt(testClass.hashCode());
	}

	@Override
	public int size() {
		return 5;
	}

	@Override
	public void setTestClass(TestClass testClass) {
		this.testClass = testClass;
	}

}

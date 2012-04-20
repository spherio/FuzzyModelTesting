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
	
	private int hash;

	@Override
	public void init() {	
		random = new Random();
	}
		
	@Override
	public Integer next() {
		return random.nextInt(hash);
	}

	@Override
	public int size() {
		return 5;
	}

	@Override
	public void setTestClass(TestClass testClass) {
		hash = testClass.hashCode();
	}

}

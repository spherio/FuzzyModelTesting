package org.eclipse.emf.emfstore.fuzzy.junit;

import org.junit.runners.model.TestClass;


/**
 * A Data Provider for the JUnit Runner: {@link FuzzyRunner}.<br><br>
 * An implementation of it has to have a zero-parameter constructor.
 * 
 * @author Julian Sommerfeldt
 *
 * @param <T> Type to specify the values created by this data provider.
 */
public interface FuzzyDataProvider<T> {	
		
	/**
	 * Creates and returns the data for the next run.<br><br>
	 * Note that it is strongly recommended to instantiate the data
	 * in this method for every call and not in the instantiation of the class, e.g. the constructor.,
	 * to avoid overloading the memory. 
	 * 
	 * @return The data for the next run of the test class.
	 */
	public T next();
	
	/**
	 * This method is called after the {@link FuzzyDataProvider} was created and everything was set BEFORE the first run.<br/>
	 * Should be used to to create internal stuff depending on e.g. the {@link TestClass}.
	 */
	public void init();
		
	/**
	 * @return The total size(count) of the repetition of the tests. 
	 */
	public int size();
	
	/**
	 * @param testClass The {@link TestClass} of the calling {@link FuzzyRunner}.
	 */
	public void setTestClass(TestClass testClass);
}

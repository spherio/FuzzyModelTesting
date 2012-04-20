package org.eclipse.emf.emfstore.fuzzy.junit;

import java.lang.reflect.Field;

import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.Data;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/**
 * A {@link Runner} for each {@link TestClass}. 
 * Used in the {@link FuzzyRunner}.
 * 
 * @author Julian Sommerfeldt
 *
 */
public class FuzzyTestClassRunner extends BlockJUnit4ClassRunner {
	
	/**
	 * Which run is it?
	 */
	private int counter;
	
	/**
	 * The {@link FuzzyDataProvider}, which "contains" the data for the test.
	 */
	private FuzzyDataProvider<?> dataProvider;
	
	/**
	 * The {@link FrameworkField} of the {@link TestClass} where to put in the data.
	 */
	private FrameworkField dataField;
	
	FuzzyTestClassRunner(Class<?> type, FuzzyDataProvider<?> dataProvider, FrameworkField dataField, int counter) throws InitializationError {
		super(type);
		this.counter = counter;
		this.dataField = dataField;
		this.dataProvider = dataProvider;
	}

	@Override
	public Object createTest() throws Exception {
		Object testInstance = getTestClass().getOnlyConstructor().newInstance();
		Field field = dataField.getField();
		field.setAccessible(true);
		try{
			field.set(testInstance, dataProvider.next());
		}catch (IllegalArgumentException e){
			throw new IllegalArgumentException(
					"The field anntoted with " + Data.class.getSimpleName() + 
					" does not fit to the type of the dataprovider (" + dataProvider.getClass() + ").", e);
		} finally {
			field.setAccessible(false);
		}
		return testInstance;
	}

	@Override
	protected String getName() {
		return String.format("[%s]", counter);
	}

	@Override
	protected String testName(final FrameworkMethod method) {
		return String.format("%s [%s]", method.getName(), counter);
	}

	@Override
	protected Statement classBlock(RunNotifier notifier) {
		return childrenInvoker(notifier);
	}
}
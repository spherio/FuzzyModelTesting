package org.eclipse.emf.emfstore.fuzzy.junit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
	
	private Object data;
		
	FuzzyTestClassRunner(Class<?> type, FuzzyDataProvider<?> dataProvider, FrameworkField dataField, int counter) throws InitializationError {
		super(type);
		this.counter = counter;
		this.dataField = dataField;
		this.dataProvider = dataProvider;
	}

	@Override
	public Object createTest() throws Exception {
		if(data == null){
			data = dataProvider.next();
		}
				
		Object testInstance = getTestClass().getOnlyConstructor().newInstance();
		Field field = dataField.getField();
		field.setAccessible(true);
		try{
			field.set(testInstance, data);
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
	protected List<FrameworkMethod> getChildren() {
		// filter for tests contained in config		
		List<FrameworkMethod> children = new ArrayList<FrameworkMethod>();		
		List<Test> testsToRun = dataProvider.getTestsToRun();
		if(testsToRun != null){
			for(Test test : testsToRun){
				for (FrameworkMethod child : super.getChildren()) {
					if(test.getSeedCount() == counter && test.getName().equals(child.getName())){
						children.add(child);
					}
				}
			}
			
			// if there is no test, the dataProvider has to be set to the next element
			if(children.size() == 0){
				dataProvider.next();
			}
			
		} else {
			children = super.getChildren();
		}
		
		return children;
	}
	
	private String testName(String name){
		return String.format("%s%s[%s]", name, FuzzyRunner.NAME_SEPARATOR, counter);
	}
	
	@Override
	protected String testName(final FrameworkMethod method) {
		return testName(method.getName());
	}
	
	@Override
	protected String getName() {
		return String.format("[%s]", counter);
	}

	@Override
	protected Statement classBlock(RunNotifier notifier) {
		return childrenInvoker(notifier);
	}
}

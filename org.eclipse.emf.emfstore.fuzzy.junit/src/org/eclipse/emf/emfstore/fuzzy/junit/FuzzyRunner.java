package org.eclipse.emf.emfstore.fuzzy.junit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.Data;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.DataProvider;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkField;

/**
 * A {@link Runner} for JUnit, to realize multiple runs with different values for a data field.
 * <br/>
 * <br/>
 * Activate with the {@link RunWith} annotation: <code>@RunWith(FuzzyRunner.class)</code>.
 * <br/>
 * <br/>
 * The test class must have a field, which is not static and annotated with {@link Data}, e.g.<br/>
 * <br/>
 * <code>@Data<br/>private Integer i;</code>
 * <br/>
 * <br/>
 * To provide data an implementation of {@link FuzzyDataProvider} can be set via the {@link DataProvider} annotation, e.g.<br/>
 * <br/>
 * <code>@DataProvider(IntDataProvider.class)</code><br/>
 * <br/>
 * This class must implement the interface {@link FuzzyDataProvider}. The default value is the example implementation: {@link IntDataProvider}.<br/>
 * <br/>
 * The {@link MyTest} class illustrates an example usage of the {@link FuzzyRunner}.
 * 
 * @author Julian Sommerfeldt
 *
 */
public class FuzzyRunner extends Suite {
	
	private ArrayList<Runner> runners = new ArrayList<Runner>();
	
	private FrameworkField dataField;
	
	private FuzzyDataProvider<?> dataProvider;
	
	private Class<?> defaultDataProviderClass = IntDataProvider.class;
		
	/**
	 * Default constructor, called by JUnit. 
	 */
	public FuzzyRunner(Class<?> clazz) throws Exception{
		super(clazz, Collections.<Runner>emptyList());
		dataProvider = getDataProvider();
		dataProvider.setTestClass(getTestClass());
		dataProvider.init();
		dataField = getModelField();	
		for (int i = 0; i < dataProvider.size(); i++) {
			runners.add(new FuzzyTestClassRunner(clazz, dataProvider, dataField, i));
		}
	}
	
	/*
	 * Override to add RunListeners of the FuzzyDataProvider 
	 * 
	 * (non-Javadoc)
	 * @see org.junit.runners.ParentRunner#run(org.junit.runner.notification.RunNotifier)
	 */
	@Override
	public void run(final RunNotifier notifier) {
		List<RunListener> listener = dataProvider.getListener();
		if(listener != null){
			for(RunListener runListener : listener){
				notifier.addListener(runListener);
			}
		}		
		super.run(notifier);
	}
	
	/**
	 * @return The field annotated with {@link Data}.
	 * @throws Exception If there is not exact one fitting field.
	 */
	private FrameworkField getModelField() throws Exception {
		List<FrameworkField> fields = getTestClass().getAnnotatedFields(Data.class);
		
		// Check if there are more than one Data field in the class
		if(fields.size() > 1){
			throw new Exception("Only one field annotated with " + Data.class.getSimpleName() +
					" permitted: " + getTestClass().getName() + " contains " + fields.size());
		}
		
		// get the field and check modifiers
		for (FrameworkField field : fields) {
			int modifiers = field.getField().getModifiers();
			if (!Modifier.isStatic(modifiers)){
				return field;
			}
		}
		
		// if there is no fitting field tell to user
		throw new Exception("No non-static model field anntoted with " + Data.class.getSimpleName() + " in class " + getTestClass().getName());
	}
	
	/**
	 * @return The {@link FuzzyDataProvider} defined by the {@link DataProvider} annotation or the default one.
	 * @throws NoSuchMethodException If there is no zero parameter constructor.
	 * @throws Exception If the data provider does not implement the {@link FuzzyDataProvider} interface.
	 */
	private FuzzyDataProvider<?> getDataProvider() throws Exception{
		// Get the DataProvider Annotation
		Annotation[] annotations = getTestClass().getAnnotations();
		
		// take default DataProvider, if there is no annotation
		Class<?> dataProviderClass = defaultDataProviderClass;
		
		// check for the dataprovider annotation
		for (Annotation annotation : annotations) {
			if(annotation instanceof DataProvider){
				
				// Check if the given class is an implementation of FuzzyDataProvider
				dataProviderClass = ((DataProvider) annotation).value();
				if(!FuzzyDataProvider.class.isAssignableFrom(dataProviderClass)){
					throw new Exception(dataProviderClass + " is not an implementation of " + FuzzyDataProvider.class.getSimpleName());
				}				
			}
		}
						
		// create a new instance of the DataProvider
		try{
			return (FuzzyDataProvider<?>) dataProviderClass.getConstructor().newInstance();
		} catch (NoSuchMethodException e){
			throw new NoSuchMethodException("The DataProvider must have a zero-parameter constructor!");
		}
	}
	
	@Override
	protected List<Runner> getChildren() {
		return runners;
	}
}

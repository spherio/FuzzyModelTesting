package org.eclipse.emf.emfstore.fuzzy.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class Annotations {
	
	/**
	 * Annotation to declare the field in the test, where to set the data.
	 * 
	 * @author Julian Sommerfeldt
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.FIELD})
	public @interface Data {}
	
	/**
	 * Annotations to declare the field for the {@link Util}.
	 * 
	 * @author Julian Sommerfeldt
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.FIELD})
	public @interface Util {}
		
	/**
	 * An annotation to set the {@link FuzzyDataProvider} for the {@link FuzzyRunner}.
	 * 
	 * @author Julian Sommerfeldt
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE})
	public @interface DataProvider {
		
		/**
		 * @return The class of the {@link FuzzyDataProvider}.
		 */
		Class<?> value();
	}
}

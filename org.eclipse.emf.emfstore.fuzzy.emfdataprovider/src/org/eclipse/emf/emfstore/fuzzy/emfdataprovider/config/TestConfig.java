/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getSeed <em>Seed</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getNsURI <em>Ns URI</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getCount <em>Count</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getTestClass <em>Test Class</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigPackage#getTestConfig()
 * @model
 * @generated
 */
public interface TestConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Seed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seed</em>' attribute.
	 * @see #setSeed(long)
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigPackage#getTestConfig_Seed()
	 * @model
	 * @generated
	 */
	long getSeed();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getSeed <em>Seed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seed</em>' attribute.
	 * @see #getSeed()
	 * @generated
	 */
	void setSeed(long value);

	/**
	 * Returns the value of the '<em><b>Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ns URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ns URI</em>' attribute.
	 * @see #setNsURI(String)
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigPackage#getTestConfig_NsURI()
	 * @model
	 * @generated
	 */
	String getNsURI();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getNsURI <em>Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ns URI</em>' attribute.
	 * @see #getNsURI()
	 * @generated
	 */
	void setNsURI(String value);

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(int)
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigPackage#getTestConfig_Count()
	 * @model
	 * @generated
	 */
	int getCount();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(int value);

	/**
	 * Returns the value of the '<em><b>Test Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Class</em>' attribute.
	 * @see #setTestClass(Class)
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigPackage#getTestConfig_TestClass()
	 * @model
	 * @generated
	 */
	Class getTestClass();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getTestClass <em>Test Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Class</em>' attribute.
	 * @see #getTestClass()
	 * @generated
	 */
	void setTestClass(Class value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigPackage#getTestConfig_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // TestConfig

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigFactory
 * @model kind="package"
 * @generated
 */
public interface ConfigPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "config";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org/eclipse/emf/emfstore/fuzzy/emfdataprovider/config";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConfigPackage eINSTANCE = org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.ConfigPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl <em>Test Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.ConfigPackageImpl#getTestConfig()
	 * @generated
	 */
	int TEST_CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Seed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIG__SEED = 0;

	/**
	 * The feature id for the '<em><b>Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIG__NS_URI = 1;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIG__COUNT = 2;

	/**
	 * The feature id for the '<em><b>Test Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIG__TEST_CLASS = 3;

	/**
	 * The number of structural features of the '<em>Test Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIG_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig <em>Test Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Config</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig
	 * @generated
	 */
	EClass getTestConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getSeed <em>Seed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seed</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getSeed()
	 * @see #getTestConfig()
	 * @generated
	 */
	EAttribute getTestConfig_Seed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getNsURI <em>Ns URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ns URI</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getNsURI()
	 * @see #getTestConfig()
	 * @generated
	 */
	EAttribute getTestConfig_NsURI();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getCount()
	 * @see #getTestConfig()
	 * @generated
	 */
	EAttribute getTestConfig_Count();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getTestClass <em>Test Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Class</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig#getTestClass()
	 * @see #getTestConfig()
	 * @generated
	 */
	EAttribute getTestConfig_TestClass();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConfigFactory getConfigFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl <em>Test Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl
		 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.ConfigPackageImpl#getTestConfig()
		 * @generated
		 */
		EClass TEST_CONFIG = eINSTANCE.getTestConfig();

		/**
		 * The meta object literal for the '<em><b>Seed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CONFIG__SEED = eINSTANCE.getTestConfig_Seed();

		/**
		 * The meta object literal for the '<em><b>Ns URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CONFIG__NS_URI = eINSTANCE.getTestConfig_NsURI();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CONFIG__COUNT = eINSTANCE.getTestConfig_Count();

		/**
		 * The meta object literal for the '<em><b>Test Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CONFIG__TEST_CLASS = eINSTANCE.getTestConfig_TestClass();

	}

} //ConfigPackage

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
import org.eclipse.emf.ecore.EReference;

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
	 * The meta object id for the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestRunImpl <em>Test Run</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestRunImpl
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.ConfigPackageImpl#getTestRun()
	 * @generated
	 */
	int TEST_RUN = 1;

	/**
	 * The feature id for the '<em><b>Config</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RUN__CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RUN__TIME = 1;

	/**
	 * The feature id for the '<em><b>Results</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RUN__RESULTS = 2;

	/**
	 * The number of structural features of the '<em>Test Run</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RUN_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestResultImpl <em>Test Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestResultImpl
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.ConfigPackageImpl#getTestResult()
	 * @generated
	 */
	int TEST_RESULT = 2;

	/**
	 * The feature id for the '<em><b>Seed Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RESULT__SEED_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Test Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RESULT__TEST_NAME = 1;

	/**
	 * The feature id for the '<em><b>Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RESULT__ERROR = 2;

	/**
	 * The feature id for the '<em><b>Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RESULT__FAILURE = 3;

	/**
	 * The feature id for the '<em><b>Execution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RESULT__EXECUTION_TIME = 4;

	/**
	 * The number of structural features of the '<em>Test Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RESULT_FEATURE_COUNT = 5;


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
	 * Returns the meta object for class '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun <em>Test Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Run</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun
	 * @generated
	 */
	EClass getTestRun();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun#getConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Config</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun#getConfig()
	 * @see #getTestRun()
	 * @generated
	 */
	EReference getTestRun_Config();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun#getTime()
	 * @see #getTestRun()
	 * @generated
	 */
	EAttribute getTestRun_Time();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun#getResults <em>Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Results</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun#getResults()
	 * @see #getTestRun()
	 * @generated
	 */
	EReference getTestRun_Results();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult <em>Test Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Result</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult
	 * @generated
	 */
	EClass getTestResult();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getSeedCount <em>Seed Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seed Count</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getSeedCount()
	 * @see #getTestResult()
	 * @generated
	 */
	EAttribute getTestResult_SeedCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getTestName <em>Test Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Name</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getTestName()
	 * @see #getTestResult()
	 * @generated
	 */
	EAttribute getTestResult_TestName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getError <em>Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getError()
	 * @see #getTestResult()
	 * @generated
	 */
	EAttribute getTestResult_Error();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getFailure <em>Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failure</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getFailure()
	 * @see #getTestResult()
	 * @generated
	 */
	EAttribute getTestResult_Failure();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getExecutionTime <em>Execution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution Time</em>'.
	 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult#getExecutionTime()
	 * @see #getTestResult()
	 * @generated
	 */
	EAttribute getTestResult_ExecutionTime();

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

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestRunImpl <em>Test Run</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestRunImpl
		 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.ConfigPackageImpl#getTestRun()
		 * @generated
		 */
		EClass TEST_RUN = eINSTANCE.getTestRun();

		/**
		 * The meta object literal for the '<em><b>Config</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_RUN__CONFIG = eINSTANCE.getTestRun_Config();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_RUN__TIME = eINSTANCE.getTestRun_Time();

		/**
		 * The meta object literal for the '<em><b>Results</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_RUN__RESULTS = eINSTANCE.getTestRun_Results();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestResultImpl <em>Test Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestResultImpl
		 * @see org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.ConfigPackageImpl#getTestResult()
		 * @generated
		 */
		EClass TEST_RESULT = eINSTANCE.getTestResult();

		/**
		 * The meta object literal for the '<em><b>Seed Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_RESULT__SEED_COUNT = eINSTANCE.getTestResult_SeedCount();

		/**
		 * The meta object literal for the '<em><b>Test Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_RESULT__TEST_NAME = eINSTANCE.getTestResult_TestName();

		/**
		 * The meta object literal for the '<em><b>Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_RESULT__ERROR = eINSTANCE.getTestResult_Error();

		/**
		 * The meta object literal for the '<em><b>Failure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_RESULT__FAILURE = eINSTANCE.getTestResult_Failure();

		/**
		 * The meta object literal for the '<em><b>Execution Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_RESULT__EXECUTION_TIME = eINSTANCE.getTestResult_ExecutionTime();

	}

} //ConfigPackage

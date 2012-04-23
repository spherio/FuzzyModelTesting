/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.emfstore.fuzzy.config.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.emfstore.fuzzy.config.ConfigPackage;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestDiff;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Diff</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.config.impl.TestDiffImpl#getLastUpdate <em>Last Update</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.config.impl.TestDiffImpl#getConfig <em>Config</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.config.impl.TestDiffImpl#getOldResult <em>Old Result</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.config.impl.TestDiffImpl#getNewResult <em>New Result</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestDiffImpl extends EObjectImpl implements TestDiff {
	/**
	 * The default value of the '{@link #getLastUpdate() <em>Last Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastUpdate()
	 * @generated
	 * @ordered
	 */
	protected static final Date LAST_UPDATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastUpdate() <em>Last Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastUpdate()
	 * @generated
	 * @ordered
	 */
	protected Date lastUpdate = LAST_UPDATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfig() <em>Config</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfig()
	 * @generated
	 * @ordered
	 */
	protected TestConfig config;

	/**
	 * The cached value of the '{@link #getOldResult() <em>Old Result</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldResult()
	 * @generated
	 * @ordered
	 */
	protected TestResult oldResult;

	/**
	 * The cached value of the '{@link #getNewResult() <em>New Result</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewResult()
	 * @generated
	 * @ordered
	 */
	protected TestResult newResult;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestDiffImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.Literals.TEST_DIFF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastUpdate(Date newLastUpdate) {
		Date oldLastUpdate = lastUpdate;
		lastUpdate = newLastUpdate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_DIFF__LAST_UPDATE, oldLastUpdate, lastUpdate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestConfig getConfig() {
		if (config != null && config.eIsProxy()) {
			InternalEObject oldConfig = (InternalEObject)config;
			config = (TestConfig)eResolveProxy(oldConfig);
			if (config != oldConfig) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.TEST_DIFF__CONFIG, oldConfig, config));
			}
		}
		return config;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestConfig basicGetConfig() {
		return config;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfig(TestConfig newConfig) {
		TestConfig oldConfig = config;
		config = newConfig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_DIFF__CONFIG, oldConfig, config));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestResult getOldResult() {
		if (oldResult != null && oldResult.eIsProxy()) {
			InternalEObject oldOldResult = (InternalEObject)oldResult;
			oldResult = (TestResult)eResolveProxy(oldOldResult);
			if (oldResult != oldOldResult) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.TEST_DIFF__OLD_RESULT, oldOldResult, oldResult));
			}
		}
		return oldResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestResult basicGetOldResult() {
		return oldResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOldResult(TestResult newOldResult) {
		TestResult oldOldResult = oldResult;
		oldResult = newOldResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_DIFF__OLD_RESULT, oldOldResult, oldResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestResult getNewResult() {
		if (newResult != null && newResult.eIsProxy()) {
			InternalEObject oldNewResult = (InternalEObject)newResult;
			newResult = (TestResult)eResolveProxy(oldNewResult);
			if (newResult != oldNewResult) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.TEST_DIFF__NEW_RESULT, oldNewResult, newResult));
			}
		}
		return newResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestResult basicGetNewResult() {
		return newResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewResult(TestResult newNewResult) {
		TestResult oldNewResult = newResult;
		newResult = newNewResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_DIFF__NEW_RESULT, oldNewResult, newResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConfigPackage.TEST_DIFF__LAST_UPDATE:
				return getLastUpdate();
			case ConfigPackage.TEST_DIFF__CONFIG:
				if (resolve) return getConfig();
				return basicGetConfig();
			case ConfigPackage.TEST_DIFF__OLD_RESULT:
				if (resolve) return getOldResult();
				return basicGetOldResult();
			case ConfigPackage.TEST_DIFF__NEW_RESULT:
				if (resolve) return getNewResult();
				return basicGetNewResult();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConfigPackage.TEST_DIFF__LAST_UPDATE:
				setLastUpdate((Date)newValue);
				return;
			case ConfigPackage.TEST_DIFF__CONFIG:
				setConfig((TestConfig)newValue);
				return;
			case ConfigPackage.TEST_DIFF__OLD_RESULT:
				setOldResult((TestResult)newValue);
				return;
			case ConfigPackage.TEST_DIFF__NEW_RESULT:
				setNewResult((TestResult)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case ConfigPackage.TEST_DIFF__LAST_UPDATE:
				setLastUpdate(LAST_UPDATE_EDEFAULT);
				return;
			case ConfigPackage.TEST_DIFF__CONFIG:
				setConfig((TestConfig)null);
				return;
			case ConfigPackage.TEST_DIFF__OLD_RESULT:
				setOldResult((TestResult)null);
				return;
			case ConfigPackage.TEST_DIFF__NEW_RESULT:
				setNewResult((TestResult)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ConfigPackage.TEST_DIFF__LAST_UPDATE:
				return LAST_UPDATE_EDEFAULT == null ? lastUpdate != null : !LAST_UPDATE_EDEFAULT.equals(lastUpdate);
			case ConfigPackage.TEST_DIFF__CONFIG:
				return config != null;
			case ConfigPackage.TEST_DIFF__OLD_RESULT:
				return oldResult != null;
			case ConfigPackage.TEST_DIFF__NEW_RESULT:
				return newResult != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (lastUpdate: ");
		result.append(lastUpdate);
		result.append(')');
		return result.toString();
	}

} //TestDiffImpl

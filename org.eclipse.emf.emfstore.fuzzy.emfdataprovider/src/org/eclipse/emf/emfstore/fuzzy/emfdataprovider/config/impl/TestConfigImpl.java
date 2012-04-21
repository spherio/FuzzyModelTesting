/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigPackage;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestConfig;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl#getSeed <em>Seed</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl#getNsURI <em>Ns URI</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl#getCount <em>Count</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl#getTestClass <em>Test Class</em>}</li>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.impl.TestConfigImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestConfigImpl extends EObjectImpl implements TestConfig {
	/**
	 * The default value of the '{@link #getSeed() <em>Seed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeed()
	 * @generated
	 * @ordered
	 */
	protected static final long SEED_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSeed() <em>Seed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeed()
	 * @generated
	 * @ordered
	 */
	protected long seed = SEED_EDEFAULT;

	/**
	 * The default value of the '{@link #getNsURI() <em>Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNsURI()
	 * @generated
	 * @ordered
	 */
	protected static final String NS_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNsURI() <em>Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNsURI()
	 * @generated
	 * @ordered
	 */
	protected String nsURI = NS_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final int COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected int count = COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestClass() <em>Test Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestClass()
	 * @generated
	 * @ordered
	 */
	protected static final Class TEST_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestClass() <em>Test Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestClass()
	 * @generated
	 * @ordered
	 */
	protected Class testClass = TEST_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ConfigPackage.Literals.TEST_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeed(long newSeed) {
		long oldSeed = seed;
		seed = newSeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_CONFIG__SEED, oldSeed, seed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNsURI() {
		return nsURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNsURI(String newNsURI) {
		String oldNsURI = nsURI;
		nsURI = newNsURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_CONFIG__NS_URI, oldNsURI, nsURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCount(int newCount) {
		int oldCount = count;
		count = newCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_CONFIG__COUNT, oldCount, count));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Class getTestClass() {
		return testClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestClass(Class newTestClass) {
		Class oldTestClass = testClass;
		testClass = newTestClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_CONFIG__TEST_CLASS, oldTestClass, testClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TEST_CONFIG__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConfigPackage.TEST_CONFIG__SEED:
				return new Long(getSeed());
			case ConfigPackage.TEST_CONFIG__NS_URI:
				return getNsURI();
			case ConfigPackage.TEST_CONFIG__COUNT:
				return new Integer(getCount());
			case ConfigPackage.TEST_CONFIG__TEST_CLASS:
				return getTestClass();
			case ConfigPackage.TEST_CONFIG__ID:
				return getId();
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
			case ConfigPackage.TEST_CONFIG__SEED:
				setSeed(((Long)newValue).longValue());
				return;
			case ConfigPackage.TEST_CONFIG__NS_URI:
				setNsURI((String)newValue);
				return;
			case ConfigPackage.TEST_CONFIG__COUNT:
				setCount(((Integer)newValue).intValue());
				return;
			case ConfigPackage.TEST_CONFIG__TEST_CLASS:
				setTestClass((Class)newValue);
				return;
			case ConfigPackage.TEST_CONFIG__ID:
				setId((String)newValue);
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
			case ConfigPackage.TEST_CONFIG__SEED:
				setSeed(SEED_EDEFAULT);
				return;
			case ConfigPackage.TEST_CONFIG__NS_URI:
				setNsURI(NS_URI_EDEFAULT);
				return;
			case ConfigPackage.TEST_CONFIG__COUNT:
				setCount(COUNT_EDEFAULT);
				return;
			case ConfigPackage.TEST_CONFIG__TEST_CLASS:
				setTestClass(TEST_CLASS_EDEFAULT);
				return;
			case ConfigPackage.TEST_CONFIG__ID:
				setId(ID_EDEFAULT);
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
			case ConfigPackage.TEST_CONFIG__SEED:
				return seed != SEED_EDEFAULT;
			case ConfigPackage.TEST_CONFIG__NS_URI:
				return NS_URI_EDEFAULT == null ? nsURI != null : !NS_URI_EDEFAULT.equals(nsURI);
			case ConfigPackage.TEST_CONFIG__COUNT:
				return count != COUNT_EDEFAULT;
			case ConfigPackage.TEST_CONFIG__TEST_CLASS:
				return TEST_CLASS_EDEFAULT == null ? testClass != null : !TEST_CLASS_EDEFAULT.equals(testClass);
			case ConfigPackage.TEST_CONFIG__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (seed: ");
		result.append(seed);
		result.append(", nsURI: ");
		result.append(nsURI);
		result.append(", count: ");
		result.append(count);
		result.append(", testClass: ");
		result.append(testClass);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //TestConfigImpl

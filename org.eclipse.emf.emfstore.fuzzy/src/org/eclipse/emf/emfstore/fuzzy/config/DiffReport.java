/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.emfstore.fuzzy.config;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diff Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.emfstore.fuzzy.config.DiffReport#getDiffs <em>Diffs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.emfstore.fuzzy.config.ConfigPackage#getDiffReport()
 * @model
 * @generated
 */
public interface DiffReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Diffs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.emfstore.fuzzy.config.TestDiff}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diffs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diffs</em>' containment reference list.
	 * @see org.eclipse.emf.emfstore.fuzzy.config.ConfigPackage#getDiffReport_Diffs()
	 * @model type="org.eclipse.emf.emfstore.fuzzy.config.TestDiff" containment="true"
	 * @generated
	 */
	EList getDiffs();

} // DiffReport

package org.eclipse.emf.emfstore.fuzzy.test;

import junit.framework.Assert;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.emfstore.client.model.impl.ProjectSpaceImpl;
import org.eclipse.emf.emfstore.common.model.util.ModelUtil;
import org.eclipse.emf.emfstore.fuzzy.EMFDataProvider;
import org.eclipse.emf.emfstore.fuzzy.MutateUtil;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.Data;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.DataProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.Util;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyRunner;
import org.eclipse.emf.emfstore.server.model.versioning.operations.AbstractOperation;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(FuzzyRunner.class)
@DataProvider(EMFDataProvider.class)
public class ChangeTrackingTest {
	
	@Data
	private ProjectSpaceImpl projectSpace;
	
	@Util
	private MutateUtil util;
	
	@Test
	public void changeTrackingTest(){
		projectSpace.getOperations().clear();
		ProjectSpaceImpl copy = (ProjectSpaceImpl) EcoreUtil.copy(projectSpace);	
		
		copy.setProject(ModelUtil.clone(projectSpace.getProject()));

		util.mutate(projectSpace.getProject());
						
		for (AbstractOperation op : projectSpace.getOperations()) {
			op.apply(copy.getProject());			
		}

		projectSpace.getOperations().clear();
		
		Assert.assertTrue(ModelUtil.areEqual(projectSpace, copy));
	}
}

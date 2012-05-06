package org.eclipse.emf.emfstore.fuzzy.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.emfstore.client.model.ProjectSpace;
import org.eclipse.emf.emfstore.client.model.impl.ProjectSpaceImpl;
import org.eclipse.emf.emfstore.common.model.Project;
import org.eclipse.emf.emfstore.common.model.impl.ProjectImpl;
import org.eclipse.emf.emfstore.common.model.util.ModelUtil;
import org.eclipse.emf.emfstore.common.model.util.SerializationException;
import org.eclipse.emf.emfstore.fuzzy.EMFDataProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.Data;
import org.eclipse.emf.emfstore.fuzzy.junit.Annotations.DataProvider;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyRunner;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutator;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorConfiguration;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorUtil;
import org.eclipse.emf.emfstore.server.model.versioning.operations.AbstractOperation;
import org.junit.Test;
import org.junit.runner.RunWith;

import bowling.BowlingFactory;
import bowling.League;
import bowling.Player;

@RunWith(FuzzyRunner.class)
@DataProvider(EMFDataProvider.class)
public class ChangeTrackingTest {
	
	@Data
	private ProjectSpaceImpl projectSpace;
	
//	@Test
	public void changeTrackingTest(){
		projectSpace.getOperations().clear();
		ProjectSpaceImpl copy = (ProjectSpaceImpl) EcoreUtil.copy(projectSpace);		

		System.out.println(ModelUtil.areEqual(projectSpace, copy));
		
//		ModelMutatorConfiguration mmc = new ModelMutatorConfiguration(ModelMutatorUtil.getEPackage("http://org/eclipse/example/bowling"),
//				projectSpace.getProject(), 5);
//		mmc.setDepth(1);
//		mmc.setWidth(1);
//		ModelMutator.changeModel(mmc);
		
//		League league = BowlingFactory.eINSTANCE.createLeague();
//		ModelMutatorUtil.addPerCommand(projectSpace.getProject(), projectSpace.getProject().eClass().getEAllContainments().get(0), 
//				league, new HashSet<RuntimeException>(), false);		
//		projectSpace.getProject().addModelElement(league);

		EObject content = projectSpace.getProject().getModelElements().get(0);
		projectSpace.getProject().deleteModelElement(content);
					
//		ModelMutatorUtil.removePerCommand(projectSpace.getProject(), projectSpace.getProject().eClass().getEAllContainments().get(0), 
//				Collections.singleton(league), new HashSet<RuntimeException>(), false);
		
//		ModelMutatorUtil.removeFullPerCommand(league, new HashSet<RuntimeException>(), false);
						
		for (AbstractOperation op : projectSpace.getOperations()) {
			System.out.println(op);
			op.apply(copy.getProject());			
		}

		projectSpace.getOperations().clear();
		
		System.out.println(ModelUtil.areEqual(projectSpace, copy));
	}
}

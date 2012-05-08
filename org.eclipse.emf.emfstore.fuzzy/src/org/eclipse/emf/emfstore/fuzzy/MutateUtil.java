package org.eclipse.emf.emfstore.fuzzy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.emfstore.client.model.util.EMFStoreCommand;
import org.eclipse.emf.emfstore.fuzzy.junit.Util;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutator;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorConfiguration;

public class MutateUtil extends Util {
	
	public static final long STANDARD_SEED = 5;

	private EMFDataProvider dataProvider;
	
	public MutateUtil(EMFDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	public EPackage getEPackage(){
		return dataProvider.getEPackage();
	}	
	
	public void mutate(EObject root){
		mutate(root, STANDARD_SEED);
	}
	
	public void mutate(EObject root, long seed){
		ModelMutatorConfiguration mmc = new ModelMutatorConfiguration(getEPackage(), root, seed);
		mmc.seteStructuralFeaturesToIgnore(EMFDataProvider.getFeaturesToIgnore());
		mutate(mmc);
	}
	
	public void mutate(final ModelMutatorConfiguration mmc){
		new EMFStoreCommand() {
			@Override
			protected void doRun() {
				ModelMutator.changeModel(mmc);
			}
		}.run(false);
	}
}

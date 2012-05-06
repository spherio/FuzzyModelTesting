package org.eclipse.emf.emfstore.fuzzy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.emfstore.client.model.util.EMFStoreCommand;
import org.eclipse.emf.emfstore.fuzzy.junit.Util;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutator;
import org.eclipse.emf.emfstore.modelmutator.api.ModelMutatorConfiguration;

public class MutateUtil extends Util {

	private EMFDataProvider dataProvider;
	
	public MutateUtil(EMFDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	public EPackage getEPackage(){
		return dataProvider.getEPackage();
	}	
	
	public void mutate(EObject root){
		mutate(root, 5);
	}
	
	public void mutate(EObject root, long seed){
		mutate(new ModelMutatorConfiguration(getEPackage(), root, seed));
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

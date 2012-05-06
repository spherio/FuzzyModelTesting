package org.eclipse.emf.emfstore.fuzzy.diff;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;

/**
 * Abstract TestRunProvider to provide the {@link TestRun}s needed in the {@link DiffGenerator}.
 * 
 * @author Julian Sommerfeldt
 *
 */
public abstract class TestRunProvider {
	
	protected TestConfig config;

	public abstract TestRun[] getTestRuns() throws Exception;

	public void setConfig(TestConfig config){
		this.config = config;
	}
	
	protected TestRun getTestRun(Resource resource){
		for(EObject obj : resource.getContents()){
			if(obj instanceof TestRun){
				return (TestRun) obj;
			}
		}
		throw new RuntimeException("Could not load TestRuns from config!");
	}
}
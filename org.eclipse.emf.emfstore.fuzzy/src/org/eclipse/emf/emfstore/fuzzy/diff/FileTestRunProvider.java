package org.eclipse.emf.emfstore.fuzzy.diff;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfstore.fuzzy.FuzzyUtil;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;

public class FileTestRunProvider extends TestRunProvider {
		
	@Override
	public TestRun[] getTestRuns() throws Exception {
				
		Resource run1Resource = FuzzyUtil.createResource( "file://D:/downloads/1.xml" );
		Resource run2Resource = FuzzyUtil.createResource( "file://D:/downloads/2.xml" );
		
		if(FuzzyUtil.resourceExists(run1Resource)){
			run1Resource.load(null);
		}
		if(FuzzyUtil.resourceExists(run2Resource)){
			run2Resource.load(null);
		}
		
		return new TestRun[]{getTestRun(run1Resource), getTestRun(run2Resource)};
	}
}

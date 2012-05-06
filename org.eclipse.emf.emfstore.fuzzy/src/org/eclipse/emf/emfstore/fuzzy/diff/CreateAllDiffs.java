package org.eclipse.emf.emfstore.fuzzy.diff;

import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;
import org.junit.Test;

public class CreateAllDiffs {	
	
	@Test
	public void createAllDiffs(){
		DiffGenerator diffGenerator = new DiffGenerator();

		try {
//			HudsonTestRunProvider runProvider = new HudsonTestRunProvider();
//			for(TestConfig config : runProvider.getAllConfigs()){
//				runProvider.setConfig(config);
//				TestRun[] runs = runProvider.getTestRuns();
//				diffGenerator.createDiff(runs[0], runs[1]);
//			}
			TestRun[] runs = new FileTestRunProvider().getTestRuns();
			diffGenerator.createDiff(runs[0], runs[1]);
		} catch (Exception e) {			
			throw new RuntimeException("Could not create diffs.", e);
		} 
		
	}
}

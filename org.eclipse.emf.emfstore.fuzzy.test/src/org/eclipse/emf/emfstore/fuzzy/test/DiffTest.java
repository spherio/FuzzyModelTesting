package org.eclipse.emf.emfstore.fuzzy.test;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfstore.fuzzy.FuzzyUtil;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;
import org.eclipse.emf.emfstore.fuzzy.diff.DiffGenerator;
import org.eclipse.emf.emfstore.fuzzy.diff.HudsonTestRunProvider;
import org.eclipse.emf.emfstore.fuzzy.diff.TestRunProvider;
import org.junit.Test;

public class DiffTest {
	
	//@Test
	public void diff(){
		try {
			Resource configResource = FuzzyUtil.createResource(FuzzyUtil.TEST_CONFIG_PATH);		
			configResource.load(null);
						
			List<TestConfig> configs = new ArrayList<TestConfig>();
			for(EObject obj : configResource.getContents()){
				if(obj instanceof TestConfig){
					configs.add((TestConfig) obj);
				}
			}
			
			createDiffs(configs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void createDiffs(List<TestConfig> configs) throws Exception{
		DiffGenerator diffGenerator = new DiffGenerator();
		TestRunProvider runProvider = new HudsonTestRunProvider();
		
		for(TestConfig config : configs){
			runProvider.setConfig(config);
			TestRun[] runs = runProvider.getTestRuns();
			diffGenerator.createDiff(runs[0], runs[1]);
		}
	}
	
}

package org.eclipse.emf.emfstore.fuzzy.diff;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfstore.fuzzy.FuzzyUtil;
import org.eclipse.emf.emfstore.fuzzy.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestDiff;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;

public class DiffGenerator {
		
	private EList<EObject> diffContents;
	
	private Resource diffResource;
	
	public DiffGenerator(){
		this(FuzzyUtil.DIFF_FILE);
	}
	
	public DiffGenerator(String diffPath){
		this(FuzzyUtil.createResource(diffPath));
	}
	
	public DiffGenerator(Resource diffResource){
		this.diffResource = diffResource;
		
		if(FuzzyUtil.resourceExists(diffResource)){			
			try {
				diffResource.load(null);
			} catch (IOException e) {
				throw new RuntimeException("Could not load resource: " + diffResource.getURI(), e);
			}			
		}
		
		diffContents = diffResource.getContents();
	}
	
	@SuppressWarnings("unchecked")
	public void createDiff(TestRun firstRun, TestRun secondRun) throws IOException{	
		
		TestConfig config = firstRun.getConfig();
				
		// check if it already contains the config
		boolean containsConfig = false;
		// create map containing existing diffs; identified through testname + seedcount (e.g. test1)
		HashMap<String, TestDiff> existingDiffs = new HashMap<String, TestDiff>();
		for(EObject obj : diffContents){
			
			// add existing diffs
			if(obj instanceof TestDiff){
				TestDiff diff = (TestDiff) obj;
				TestResult result = FuzzyUtil.getValidTestResult(diff);
				existingDiffs.put(getResultIdentifier(result), diff);
			
			// check for configs
			} else if(obj instanceof TestConfig){
				TestConfig c = (TestConfig) obj;
				if(c.getId().equals(config.getId())){
					containsConfig = true;
					config = c;
				}
			}
		}
		
		// if the resource does not contain the config already add it 
		if(!containsConfig) {
			diffContents.add(config);
		}
		
		// create diffs for test in run1
		// also handle tests not contained in the second config 
		EList<TestResult> firstResults = firstRun.getResults();
		EList<TestResult> secondResults = secondRun.getResults();
		
		for(TestResult result : firstResults){
			TestResult corrResult = getCorrespondingTestResult(result, secondResults);
					
			TestDiff diff = getChangedTestDiff(result, corrResult);
			if(diff != null) {
				diff.setConfig(config);
				diffContents.add(result);
				if(corrResult != null) {
					diffContents.add(corrResult);
				}
				
				// remove diff if it already contains it
				removeDiff(existingDiffs.get(getResultIdentifier(result)));
				
				diffContents.add(diff);
			}
		}
		
		// also iterate through results of run2, to get tests not contained in run1
		for(TestResult result : secondResults){
			TestResult corrResult = getCorrespondingTestResult(result, firstResults);
			// if the test exists only in the second run
			if(corrResult == null){
				TestDiff diff = createTestDiff(null, result);
				diff.setConfig(config);
				removeDiff(existingDiffs.get(getResultIdentifier(result)));
				diffContents.add(result);
				diffContents.add(diff);
			}
		}
		
		diffResource.save(null);		
	}
	
	private void removeDiff(TestDiff diff){
		if(diff == null){
			return;
		}
		
		diffContents.remove(diff);
		diffContents.remove(diff.getNewResult());
		diffContents.remove(diff.getOldResult());
	}
	
	private String getResultIdentifier(TestResult result){
		return result.getTestName() + result.getSeedCount();
	}
	
	private TestDiff getChangedTestDiff(TestResult fRes, TestResult sRes){
		boolean changed = false;
		
		// check if a state switch occured
		// TODO test state switches
		if(fRes == null || sRes == null){
			changed = true;
		} else if(changed(fRes.getFailure(), sRes.getFailure())){
			changed = true;
		} else if(changed(fRes.getError(), sRes.getError())){
			changed = true;
		} else if(fRes.getFailure() != null && sRes.getError() != null){
			changed = true;
		}
		
		// if it changed, create a new TestDiff 
		if(changed){			
			return createTestDiff(fRes, sRes);
		}
		
		return null;
	}
	
	private boolean changed(Object o1, Object o2){
		if(o1 == null && o2 != null){
			return true;
		}
		if(o1 != null && o2 == null){
			return true;
		}
		return false;
	}
	
	private TestDiff createTestDiff(TestResult fRes, TestResult sRes){
		TestDiff diff = ConfigFactory.eINSTANCE.createTestDiff();
		diff.setLastUpdate(new Date(System.currentTimeMillis()));
		diff.setOldResult(fRes);
		diff.setNewResult(sRes);
		return diff;
	}
	
	private TestResult getCorrespondingTestResult(TestResult result, List<TestResult> results){
		for(TestResult res : results){
			if(res.getSeedCount() == result.getSeedCount() && res.getTestName().equals(result.getTestName())){
				return res;
			}
		}
		return null;
	}
}

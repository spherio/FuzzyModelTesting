package org.eclipse.emf.emfstore.fuzzy.diff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfstore.fuzzy.FuzzyUtil;
import org.eclipse.emf.emfstore.fuzzy.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.config.DiffReport;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestDiff;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;

/**
 * Generates {@link TestDiff}s out of {@link TestRun}.
 * 
 * @author Julian Sommerfeldt
 *
 */
public class DiffGenerator {
			
	private DiffReport diffReport;
	
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
		
		diffReport = getDiffReport(diffResource);
	}
	
	@SuppressWarnings("unchecked")
	public void createDiff(TestRun firstRun, TestRun secondRun) throws IOException {	
		
		TestConfig config = firstRun.getConfig();
				
		// check if it already contains the config
		boolean containsConfig = false;
		// create map containing existing diffs
		// identified through testname + seedcount (e.g. test1)
		HashMap<String, TestDiff> existingDiffs = new HashMap<String, TestDiff>();
		List<TestDiff> diffs = diffReport.getDiffs();
		for(TestDiff diff : diffs){
			
			// add existing diffs
			TestResult result = FuzzyUtil.getValidTestResult(diff);
			existingDiffs.put(getResultIdentifier(result), diff);
			
			// check for configs
			if( (!containsConfig) && (diff.getConfig().getId().equals(config.getId())) ){
				containsConfig = true;
				config = diff.getConfig();
			}			
		}
		
		// if the resource does not contain the config already add it 
		if(!containsConfig) {
			diffResource.getContents().add(config);
		}
		
		// create diffs for the two testruns
		checkForDiffs(firstRun.getResults(), secondRun.getResults(), config, existingDiffs);
		checkForDiffs(secondRun.getResults(), firstRun.getResults(), config, existingDiffs);
		
		diffResource.getContents().add(diffReport);
		diffResource.save(null);		
	}
	
	@SuppressWarnings("unchecked")
	private void checkForDiffs(List<TestResult> firstResults, List<TestResult> secondResults, TestConfig config, HashMap<String, TestDiff> existingDiffs){
		EList<TestDiff> diffs = diffReport.getDiffs();
		for(TestResult result : new ArrayList<>(firstResults)){
			TestResult corrResult = getCorrespondingTestResult(result, secondResults);
					
			TestDiff diff = getChangedTestDiff(result, corrResult);
			if(diff != null) {
				diff.setConfig(config);
				
				// remove diff if it already contains it
				diffs.remove(existingDiffs.get(getResultIdentifier(result)));				
				diffs.add(diff);
			}
		}
	}
	
	private static String getResultIdentifier(TestResult result){
		return result.getTestName() + result.getSeedCount();
	}
	
	private static TestDiff getChangedTestDiff(TestResult fRes, TestResult sRes){
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
		} else if(fRes.getError() != null && sRes.getFailure() != null){
			changed = true;
		}
		
		// if it changed, create a new TestDiff 
		if(changed){			
			return createTestDiff(fRes, sRes);
		}
		
		return null;
	}
	
	private static boolean changed(Object o1, Object o2){
		if(o1 == null && o2 != null){
			return true;
		}
		if(o1 != null && o2 == null){
			return true;
		}
		return false;
	}
	
	private static TestDiff createTestDiff(TestResult fRes, TestResult sRes){
		TestDiff diff = ConfigFactory.eINSTANCE.createTestDiff();
		diff.setLastUpdate(new Date(System.currentTimeMillis()));
		diff.setOldResult(fRes);
		diff.setNewResult(sRes);
		return diff;
	}
	
	private static TestResult getCorrespondingTestResult(TestResult result, List<TestResult> results){
		for(TestResult res : results){
			if(res.getSeedCount() == result.getSeedCount() && res.getTestName().equals(result.getTestName())){
				return res;
			}
		}
		return null;
	}
	
	private static DiffReport getDiffReport(Resource resource){
		for(EObject obj : resource.getContents()){
			if (obj instanceof DiffReport) {
				return (DiffReport) obj;					
			}
		}
		return ConfigFactory.eINSTANCE.createDiffReport();
	}
}

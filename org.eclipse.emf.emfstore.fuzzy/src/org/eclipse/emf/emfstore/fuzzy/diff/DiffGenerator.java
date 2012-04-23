package org.eclipse.emf.emfstore.fuzzy.diff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.emfstore.fuzzy.FuzzyUtil;
import org.eclipse.emf.emfstore.fuzzy.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestDiff;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;

public class DiffGenerator {
	
	public static final String DIFF_PATH = FuzzyUtil.PROTOCOL_PREFIX + FuzzyUtil.CONFIG_FOLDER + "diff" + FuzzyUtil.XML_SUFFIX;
	
	// TODO work on configs e.g. create special diffs
	@SuppressWarnings("unchecked")
	public void createDiff() throws IOException{
		AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack());
		
		Resource configs = editingDomain.createResource(FuzzyUtil.TEST_CONFIG_PATH);
		
		configs.load(null);
				
		// TODO check and enable diffs for all configs
		TestConfig config = (TestConfig) configs.getContents().get(0);
		
		// load last two runs
		long[] lastRuns = getLastTwoRuns(config);
		
		String path1 = FuzzyUtil.PROTOCOL_PREFIX + FuzzyUtil.getRunPath(lastRuns[0], config);
		String path2 = FuzzyUtil.PROTOCOL_PREFIX + FuzzyUtil.getRunPath(lastRuns[1], config);
		
		Resource run1Resource = editingDomain.createResource(path1);
		Resource run2Resource = editingDomain.createResource(path2);
		
		run1Resource.load(null);
		run2Resource.load(null);
		
		
		TestRun firstRun = getTestRun(run1Resource);
		TestRun secondRun = getTestRun(run2Resource);
		
		if(firstRun == null || secondRun == null){
			throw new RuntimeException("Could not load TestRuns from config!");
		}
		
		// get results and create diffs
		// TODO load and only update if a testdiff exists
		Resource diffResource = editingDomain.createResource(DIFF_PATH);
		diffResource.load(null);
		EList<EObject> diffContents = diffResource.getContents();
		
		diffContents.add(config);
		
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
				diffContents.add(diff);
			}
		}
		
		// also go through testresults of run2, not contained in run1
		for(TestResult result : secondResults){
			TestResult corrResult = getCorrespondingTestResult(result, firstResults);
			// if the test exists only in the second run
			if(corrResult == null){
				TestDiff diff = createTestDiff(null, result);
				diff.setConfig(config);
				diffContents.add(result);
				diffContents.add(diff);
			}
		}
		
		diffResource.save(null);		
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
	
	private TestDiff createTestDiff(TestResult fRes, TestResult sRes){
		TestDiff diff = ConfigFactory.eINSTANCE.createTestDiff();
		diff.setLastUpdate(new Date(System.currentTimeMillis()));
		diff.setOldResult(fRes);
		diff.setNewResult(sRes);
		return diff;
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
	
	private TestResult getCorrespondingTestResult(TestResult result, List<TestResult> results){
		for(TestResult res : results){
			if(res.getSeedCount() == result.getSeedCount() && res.getTestName().equals(result.getTestName())){
				return res;
			}
		}
		return null;
	}
	
	private TestRun getTestRun(Resource resource){
		for(EObject obj : resource.getContents()){
			if(obj instanceof TestRun){
				return (TestRun) obj;
			}
		}
		return null;
	}	
	
	private long[] getLastTwoRuns(TestConfig config){
		try {
			FileReader fr = new FileReader(FuzzyUtil.getConfigPath(config) + FuzzyUtil.RUNS_FILE);
			BufferedReader bf = new BufferedReader(fr);
			
			List<String> lines = new ArrayList<String>();
			
			String runTime;
			while((runTime = bf.readLine()) != null){
				lines.add(runTime);
			}
			
			int size = lines.size();
			if(size < 2){
				throw new RuntimeException("Could not generate diff, because there are not two runs at least.");
			}
			
			bf.close();
			fr.close();
			
			long run1 = Long.valueOf(lines.get(size - 1));
			long run2 = Long.valueOf(lines.get(size - 2));
			return new long[]{run1, run2};
		} catch (IOException e) {
			throw new RuntimeException("Could not load last two runs information!", e);
		}
	}
}

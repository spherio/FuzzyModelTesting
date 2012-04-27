package org.eclipse.emf.emfstore.fuzzy;

import java.io.PrintWriter;
import java.io.StringWriter;

import junit.framework.AssertionFailedError;

import org.eclipse.emf.emfstore.fuzzy.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;
import org.eclipse.emf.emfstore.fuzzy.junit.FuzzyRunner;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class EMFRunListener extends RunListener {
	
	private TestRun testRun;
	
	private EMFDataProvider dataProvider;
	
	private TestResult testResult;
	
	private long testStartTime;
	
	public EMFRunListener(EMFDataProvider dataProvider, TestRun testRun){
		this.dataProvider = dataProvider;
		this.testRun = testRun;
	}
	
	public void testRunStarted(Description description) throws Exception {}
	
	public void testRunFinished(Result result) throws Exception {		
		dataProvider.finish();
	}	
	
	public void testStarted(Description description) throws Exception {
		testResult = ConfigFactory.eINSTANCE.createTestResult();		
		testResult.setTestName(description.getMethodName().split(FuzzyRunner.NAME_SEPARATOR)[0]);		
		testStartTime = System.currentTimeMillis();
	}

	@SuppressWarnings("unchecked")
	public void testFinished(Description description) throws Exception {
		testResult.setExecutionTime(System.currentTimeMillis() - testStartTime);
		testResult.setSeedCount(dataProvider.getCurrentSeedCount());
		testRun.getResults().add(testResult);	
	}

	public void testFailure(Failure failure) throws Exception {
		Throwable throwable = failure.getException();
		StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    throwable.printStackTrace(new PrintWriter(sw));
		
	    // TODO filter stack trace ?
		if(throwable instanceof AssertionFailedError){
			testResult.setFailure(sw.toString());
		} else {
			testResult.setError(sw.toString());
		}
		
		sw.close();
		pw.close();
	}
}



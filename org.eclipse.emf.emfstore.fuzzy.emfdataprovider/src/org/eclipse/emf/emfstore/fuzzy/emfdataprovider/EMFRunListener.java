package org.eclipse.emf.emfstore.fuzzy.emfdataprovider;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import junit.framework.AssertionFailedError;

import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.ConfigFactory;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestResult;
import org.eclipse.emf.emfstore.fuzzy.emfdataprovider.config.TestRun;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class EMFRunListener extends RunListener {
	
	private TestRun testRun;
	
	private EMFDataProvider dataProvider;
	
	private TestResult testResult;
	
	public EMFRunListener(EMFDataProvider dataProvider, TestRun testRun){
		this.dataProvider = dataProvider;
		this.testRun = testRun;
	}
	
	public void testRunStarted(Description description) throws Exception {
		testRun.setTime(new Date(System.currentTimeMillis()));
	}
	
	public void testRunFinished(Result result) throws Exception {		
		dataProvider.finish();
	}	
	
	public void testStarted(Description description) throws Exception {
		testResult = ConfigFactory.eINSTANCE.createTestResult();
		testResult.setTestName(description.getMethodName());
	}

	@SuppressWarnings("unchecked")
	public void testFinished(Description description) throws Exception {
		testRun.getResults().add(testResult);	
		dataProvider.addContentToResource(testResult);
	}

	public void testFailure(Failure failure) throws Exception {
		Throwable throwable = failure.getException();
		StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    throwable.printStackTrace(new PrintWriter(sw));
		
		if(throwable instanceof AssertionFailedError){
			testResult.setFailure(sw.toString());
		} else {
			testResult.setError(sw.toString());
		}
		
		sw.close();
		pw.close();
	}
}



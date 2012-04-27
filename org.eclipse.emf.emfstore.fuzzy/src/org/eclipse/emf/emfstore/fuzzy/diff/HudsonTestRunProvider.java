package org.eclipse.emf.emfstore.fuzzy.diff;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfstore.fuzzy.FuzzyUtil;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;

// TODO use secure auth
public class HudsonTestRunProvider extends TestRunProvider {
	
	private SAXReader saxReader = new SAXReader();

	private String hudsonUrl;
		
	private String jobUrl;
	
	private int firstBuildNumber;
	
	private int secondBuildNumber;
	
	public static final String PROP_HUDSON = ".hudson";
	
	public static final String PROP_URL = ".url";
	
	public static final String PROP_PORT = ".port";
	
	public static final String PROP_JOB = ".job";
	
	public static final String[] VALID_STATES = new String[]{"SUCCESS", "UNSTABLE"};
	
	public HudsonTestRunProvider() throws DocumentException, IOException{
		initProperties();
		
		firstBuildNumber = Integer.parseInt(getFirstElementValue(jobUrl + "lastCompletedBuild" + "/api/xml?tree=number"));		
		secondBuildNumber = getLastValidBuildNumber(firstBuildNumber - 1);
	}
	
	public HudsonTestRunProvider(int firstBuildNumber, int secondBuildNumber){
		initProperties();
		
		this.firstBuildNumber = firstBuildNumber;
		this.secondBuildNumber = secondBuildNumber;
	}
	
	private void initProperties(){
		hudsonUrl = FuzzyUtil.getProperty(PROP_HUDSON + PROP_URL, "http://localhost") + ":" + 
				FuzzyUtil.getProperty(PROP_HUDSON + PROP_PORT, "8080") + "/";
		
		jobUrl = hudsonUrl + "job/" + FuzzyUtil.getProperty(PROP_HUDSON + PROP_JOB, "Test") + "/";
	}
	
	private int getLastValidBuildNumber(int maxBuildNumber) throws MalformedURLException, DocumentException{
		if(maxBuildNumber < 0){
			throw new RuntimeException("There are not enough valid builds till now!");
		}
		if(isValidBuild(maxBuildNumber)){
			return maxBuildNumber;
		} else {
			return getLastValidBuildNumber(maxBuildNumber - 1);
		}
	}
	
	private boolean isValidBuild(int buildNumber) throws MalformedURLException, DocumentException{
		String result = getFirstElementValue(jobUrl + buildNumber + "/api/xml?tree=result");
		for(String valid : VALID_STATES){
			if(valid.equals(result)){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private String getFirstElementValue(String url) throws MalformedURLException, DocumentException{
		Document doc = saxReader.read(new URL(url));
		List<Element> elements = doc.getRootElement().elements();
		if(elements.size() == 0){
			throw new RuntimeException("There are no elements in the result of the url: " + url);
		}
		return elements.get(0).getText();
	}
		
	@Override
	public TestRun[] getTestRuns() throws Exception {
		
		TestRun[] runs = new TestRun[2];
		
		Resource resource = getResource(firstBuildNumber);		
		resource.load(null);
		
		runs[0] = getTestRun(resource);
		
		resource = getResource(secondBuildNumber);		
		resource.load(null);
		
		runs[1] = getTestRun(resource);
		
		return runs;
	}
	
	private Resource getResource(int buildNumber){
		return FuzzyUtil.createResource(jobUrl + buildNumber + "/artifact/" +
				FuzzyUtil.FUZZY_FOLDER + "/" + config.getId() + FuzzyUtil.XML_SUFFIX);
	}

}
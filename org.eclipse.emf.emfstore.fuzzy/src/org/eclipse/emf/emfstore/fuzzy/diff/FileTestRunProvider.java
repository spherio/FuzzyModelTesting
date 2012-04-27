package org.eclipse.emf.emfstore.fuzzy.diff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfstore.fuzzy.FuzzyUtil;
import org.eclipse.emf.emfstore.fuzzy.config.TestConfig;
import org.eclipse.emf.emfstore.fuzzy.config.TestRun;

public class FileTestRunProvider extends TestRunProvider {
	
	private TestConfig config;
	
	public FileTestRunProvider(TestConfig config){
		this.config = config;
	}
	
	@Override
	public TestRun[] getTestRuns() throws Exception {
		long[] lastRuns = getLastTwoRuns(config);
				
		Resource run1Resource = FuzzyUtil.createResource( FuzzyUtil.PROTOCOL_PREFIX + FuzzyUtil.getRunPath(lastRuns[0], config) );
		Resource run2Resource = FuzzyUtil.createResource( FuzzyUtil.PROTOCOL_PREFIX + FuzzyUtil.getRunPath(lastRuns[1], config) );
		
		if(FuzzyUtil.resourceExists(run1Resource)){
			run1Resource.load(null);
		}
		if(FuzzyUtil.resourceExists(run2Resource)){
			run2Resource.load(null);
		}
		
		return new TestRun[]{getTestRun(run1Resource), getTestRun(run2Resource)};
	}
	
	private long[] getLastTwoRuns(TestConfig config){
		try {
			File file = new File(FuzzyUtil.getConfigPath(config) + FuzzyUtil.RUNS_FILE);
			if(!file.exists()){
				return null;
			}
			
			FileReader fr = new FileReader(file);
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

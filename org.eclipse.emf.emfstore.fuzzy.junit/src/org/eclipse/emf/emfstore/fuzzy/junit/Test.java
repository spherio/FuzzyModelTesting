package org.eclipse.emf.emfstore.fuzzy.junit;

public class Test {
	
	private String name;
	
	private int seedCount;
	
	public Test(String name, int seedCount){
		this.name = name;
		this.seedCount = seedCount;
	}

	public String getName() {
		return name;
	}

	public int getSeedCount() {
		return seedCount;
	}
}

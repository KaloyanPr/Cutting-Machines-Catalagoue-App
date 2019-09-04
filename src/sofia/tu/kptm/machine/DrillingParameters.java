package sofia.tu.kptm.machine;

public class DrillingParameters {
	private int maxDrillDiameter;
	private int numberOfFeeds;
	
	public DrillingParameters(int maxDrillDiameter, int numberOfFeeds) {
		super();
		this.maxDrillDiameter = maxDrillDiameter;
		this.numberOfFeeds = numberOfFeeds;
	}
	
	public int getMaxDrillDiameter() {
		return maxDrillDiameter;
	}
	public void setMaxDrillDiameter(int maxDrillDiameter) {
		this.maxDrillDiameter = maxDrillDiameter;
	}
	public int getNumberOfFeeds() {
		return numberOfFeeds;
	}
	public void setNumberOfFeeds(int numberOfFeeds) {
		this.numberOfFeeds = numberOfFeeds;
	}
}

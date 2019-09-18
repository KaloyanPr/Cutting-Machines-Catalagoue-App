package sofia.tu.kptm.machine;

public class LatheParameters {
	private int maxProcessedWidth;
	private int maxProcessedDiameter;
	
	public LatheParameters(int maxProcessedWidth, int maxProcessedDimeter) {
		this.maxProcessedDiameter = maxProcessedDimeter;
		this.maxProcessedWidth = maxProcessedWidth;
	}

	public int getMaxProcessedWidth() {
		return maxProcessedWidth;
	}

	public void setMaxProcessedWidth(int maxProcessedWidth) {
		this.maxProcessedWidth = maxProcessedWidth;
	}

	public int getMaxProcessedDiameter() {
		return maxProcessedDiameter;
	}

	public void setMaxProcessedDiameter(int maxProcessedDiameter) {
		this.maxProcessedDiameter = maxProcessedDiameter;
	}

}

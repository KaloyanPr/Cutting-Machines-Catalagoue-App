package sofia.tu.kptm.machine;

public class LatheParameters {
	private int maxProcessedWidth;
	private int maxProcessedDiameter;
	private int maxTransverseDisplacement;
	
	public LatheParameters(int maxProcessedWidth, int maxProcessedDimeter,int maxTransverseDisplacement) {
		this.maxProcessedDiameter = maxProcessedDimeter;
		this.maxProcessedWidth = maxProcessedWidth;
		this.maxTransverseDisplacement = maxTransverseDisplacement;
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

	public int getMaxTransverseDisplacement() {
		return maxTransverseDisplacement;
	}

	public void setMaxTransverseDisplacement(int maxTransverseDisplacement) {
		this.maxTransverseDisplacement = maxTransverseDisplacement;
	}
}

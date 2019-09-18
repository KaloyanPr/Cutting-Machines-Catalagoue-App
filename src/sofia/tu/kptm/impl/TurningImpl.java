package sofia.tu.kptm.impl;

import sofia.tu.kptm.machine.LatheParameters;

public class TurningImpl {
	private LatheParameters latheParameters;
	public static void handleTurningMachineRequest(LatheParameters parameters) {
		
	}
	public LatheParameters getLatheParameters(int param1, int param2) {
		latheParameters.setMaxProcessedDiameter(param1);
		latheParameters.setMaxProcessedWidth(param2);
		return latheParameters;
	}
}

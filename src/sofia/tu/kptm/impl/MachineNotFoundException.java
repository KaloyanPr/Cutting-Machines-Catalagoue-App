package sofia.tu.kptm.impl;

public class MachineNotFoundException extends Exception {
	/**
	 * Thrown when query result is null
	 */
	private static final long serialVersionUID = 5316552679471299042L;

	public MachineNotFoundException(String message) {
		super(message);
	}
}

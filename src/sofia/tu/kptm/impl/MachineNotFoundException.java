package sofia.tu.kptm.impl;

/**
 * Thrown when query result is null
 */
public class MachineNotFoundException extends Exception {

	private static final long serialVersionUID = 5316552679471299042L;

	public MachineNotFoundException(String message) {
		super(message);
	}
}

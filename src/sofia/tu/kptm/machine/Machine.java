package sofia.tu.kptm.machine;

public interface Machine {
	
	public String setName (String name);
	
	public String getName();
	
	public LatheParameters setParameters (LatheParameters latheParameters);
	
	public LatheParameters getParameters();
	
	public String setDescription (String description);
	
	public String getDescription();
}

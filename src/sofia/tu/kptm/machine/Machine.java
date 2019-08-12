package sofia.tu.kptm.machine;

public interface Machine {
	
	public String setName (String name);
	
	public String getName();
	
	public Parameters setParameters (Parameters parameters);
	
	public Parameters getParameters();
	
	public String setDescription (String description);
	
	public String getDescription();
}

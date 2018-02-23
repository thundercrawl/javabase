package serverPrototype_generation_one;
import java.util.UUID;
public class UserSession {

	private String username="";
	private String uniqueID="";
	
	public UserSession(String username)
	{
		this.username = username;
		this.uniqueID=username+"@"+UUID.randomUUID();
		
	}
	
	public String getUniqueID()
	{
		return this.uniqueID;
	}
}

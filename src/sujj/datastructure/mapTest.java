package sujj.datastructure;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
class Barrier
{
	private String _sessionid = null;
	public Barrier(String sessionID)
	{
		_sessionid = sessionID;
	}
	public String toString()
	{
		return _sessionid;
	}
	}
public class mapTest {

	
	
	public static void main(String[] strs)
	{
		
		Map<String,Barrier> mapCon =  Collections.synchronizedMap(new HashMap<String,Barrier>());
		String s1 = "NewBallence";
		String s2 = "NewBallence";
		mapCon.put(s1,new Barrier("1"));
		Object obj = mapCon.put(s2, new Barrier("2"));
		if (null != obj )
			{
			
				System.out.println("duplicate give up the new queue add in "+obj);
			};
		
		System.out.println("map size is "+mapCon.size());
		
	}
}

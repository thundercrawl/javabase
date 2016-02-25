package sujj.concurrent;

class r1{
public static int status = 1;
private r1(){
	logger.Log.info("Create singleton instance "+this.getClass()
	.getName());
}
private static class innerJob
{
private static r1 instance = new r1();
}
public static r1 getInstance()
{
return innerJob.instance;
}
}


class r2
{
	public static int status = 1;
	private r2()
	{	
		logger.Log.info("Create singleton instance "+this.getClass()
		.getName());
	}
	private static r2 instance = new r2();
	
	public static r2 getInstance()
	{
		
		return instance;
	}
	
	
}
class r3
{
	public static int status = 1;
	private static r3 instance = null;
	private r3()
	{
		logger.Log.info("Create singleton instance "+this.getClass()
		.getName());
	}
	
	public synchronized r3 getInstance()
	{
		
		if(instance == null)
		{
			
			return new r3();
		}
		return instance;
	}
	
}
public class Singleton {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		r2.status = 2;
		r1.status = 1;
		r3.status = 3;
	}

}

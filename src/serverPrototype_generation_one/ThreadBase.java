package serverPrototype_generation_one;

public  class ThreadBase implements Runnable{

	private ThreadLocal<UserSession> usercontext = new ThreadLocal<UserSession>();
	private UserSession us;
	public ThreadBase(UserSession us)
	{
		this.us = us;
	}
	
	private void initLog()
	{
		System.out.println("UserUniqueID="+usercontext.get().getUniqueID()+" echo message");
	}
	
	public void run()
	{
		usercontext.set(us);
		initLog();
		
	}
}

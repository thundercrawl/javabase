package sujj.concurrent.profiling;

import java.lang.management.*;
import java.util.concurrent.locks.LockSupport;

class ProfileThread extends Thread
{
	
	public void run()
	{
		LockSupport.park();
		
	}
}
class MonitorThread extends Thread
{
	public void run()
	{
		JavaThreadProfile.profile();
		
	}
}
public class JavaThreadProfile {
	public static void profile()
	{
		ThreadMXBean tb = ManagementFactory.getThreadMXBean();
		long its[] = tb.getAllThreadIds();
		for(long it :tb.getAllThreadIds())
		{
			logger.Log.info(it);
			
		}
		for(ThreadInfo in:tb.getThreadInfo(its, 150))
		{
			
			//logger.Log.info(in.getStackTrace());
			logger.Log.info(in.getThreadId());
			logger.Log.info(in.getThreadName());
			logger.Log.info(in.getLockOwnerName());
		}
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ProfileThread t1 = new ProfileThread();
		MonitorThread m1 = new MonitorThread();
		t1.start();
		m1.start();
		t1.join();
		m1.join();
	}

}

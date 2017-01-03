package sujj.concurrent;


class inLock
{
	
	public void cl1_lock() throws InterruptedException
	{
		synchronized(this)
		{
			logger.Log.info("lock1 entered, sleep 10s");
			Thread.sleep(10000);
			
		}
	}
	
	
	public void cl2_lock() throws InterruptedException
	{
		synchronized(this)
		{
			logger.Log.info("lock2 entered, sleep 10s");
			Thread.sleep(10000);
			
		}
		
	}
}

class inLockWorker extends Thread
{
	public void run()
	{
		while(true)
		{
			try {
				InstanceLock.multiAccess.cl1_lock();
				InstanceLock.multiAccess.cl2_lock();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	}
public class InstanceLock {
	public static inLock multiAccess = new inLock();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new inLockWorker().start();
		new inLockWorker().start();
		
	}

}

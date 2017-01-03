package sujj.concurrent;

class monitorWorker extends Thread
{
	
	public void run()
	{
		
		try {
			checkStatus.check();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class checkStatus
{
	static public void check() throws InterruptedException
	{
		synchronized(checkStatus.class)
		{
			logger.Log.info("Enter the locker");
			Thread.sleep(1000*1000);
			logger.Log.info("Exit the locker");
			checkStatus.class.notifyAll();
		}
		
	}
}
public class MonitorPrint {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		monitorWorker t1 = new monitorWorker();
		t1.start();
		monitorWorker t2 = new monitorWorker();
		
		t2.start();
		
		t1.join();
		t2.join();
	}

}

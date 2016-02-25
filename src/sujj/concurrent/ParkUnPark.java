package sujj.concurrent;

import java.util.concurrent.locks.LockSupport;


class Park_w1 extends Thread
{
	public void run()
	{
		try {
			synchronized(ParkUnPark.obj)
			{
				ParkUnPark.obj.wait();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
public class ParkUnPark {
	public static Object obj = new Object();
	public static void main(String[] args) throws InterruptedException
	{
		//LockSupport.park();
		Thread t1 = new Park_w1();
		t1.start();
		t1.join();
		LockSupport.park(null);
		
		LockSupport.unpark(null);
		logger.Log.info("done!");
	}
}

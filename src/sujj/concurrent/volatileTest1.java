package sujj.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

//volatile to  control the thread cache value been synchronized with the memory 
//and tell the compiler the value will be changed by other threads.

//jvm have optimized compiling when come up with -server params , so
//this code snippet only have issue with server trigger enabled. (should be verify the -server parameters
//in jdk 1.7)
class Novisiable extends Thread
{
	public void run()
	{
		while(!volatileTest1.readyB.get());
		logger.Log.info("Recieved signal : "+volatileTest1.number);
	}
	public synchronized void getStatus()
	{
		logger.Log.info("Thread status is : -- >");
		
	}
}
public class volatileTest1 {
public  static   boolean ready;//add volatile here.
public static AtomicBoolean readyB = new AtomicBoolean(false);
public  static long number;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		logger.Log.info(ready);
		logger.Log.info(number);
		readyB.set(false);
		Novisiable t1 = new Novisiable();
		t1.start();
		logger.Log.info("Just sleep 1000 ms");
		Thread.sleep(1000);
		logger.Log.info("Stop sleep 1000 ms");;
		number =42;
		ready =true;
		readyB.set(true);
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

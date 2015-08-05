package sujj.concurrent;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MonitorStatus
{
	static private int _count = -1;
	static private long _countStart = -1L;
	public static void setCounter(int count)
	{
		_count = count;
	}
	static synchronized public void decreaseCount()
	{
		_count --;
	}
	
	public static boolean getFinished()
	{
		return _count ==0 ? true:false;
	}
	public static void startTimeCount()
	{
		_countStart = System.currentTimeMillis();
	}
	public static long getTimeConsumed()
	{
		long rt = (System.currentTimeMillis()-_countStart);
		System.out.println("Time consumed :"+rt);
		return rt;
	}
	
}
class PresentationMonitor extends Thread
{
	
	public void run()
	{
		synchronized(multiConcurrentControl.preLocker)
		{
			
		multiConcurrentControl.preLocker.notifyAll();
		}
	}
}
class PresentationContorl extends Thread
{
	private long threadid = -1;
	Lock lock = new ReentrantLock(); 
	public PresentationContorl(long threadid)
	{
		this.threadid = threadid;
	}
	public long getThreadID()
	{
		return threadid;
	}
	public void run()
	{
				
		System.out.println("id: "+this.threadid+"start" );
		while(true)
		{
				if(!multiConcurrentControl.preLocker.isLocked())
				{
					System.out.println("id:"+this.threadid+"do something .......");
					multiConcurrentControl.preLocker.lock();
					long tips;
					for( tips=0;tips< 1000000000L;tips++);
					multiConcurrentControl.preLocker.unlock();
					System.out.println(this.threadid+ " finished"+" tips is:+"+tips);
					synchronized(multiConcurrentControl.preLocker)
					{
						multiConcurrentControl.preLocker.notify();
					}
					MonitorStatus.decreaseCount();
					break;
				}
				else{
					synchronized(multiConcurrentControl.preLocker)
					{
						try {
							multiConcurrentControl.preLocker.wait();
							System.out.println("id:"+this.threadid+" went into wait");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			
		}
		
		}
	}
		
	

class Locker
{
	private int lockerNumberContorl = -1;
	
	public Locker(int pageNumber)
	{
		this.lockerNumberContorl = pageNumber;
	}
	synchronized  public boolean isLocked()
	{
		
		if(lockerNumberContorl>0)
		return false;
		else return true;
	}
	synchronized public void lock()
	{
		 lockerNumberContorl--;
		 System.out.println("locker count:"+lockerNumberContorl);
		 
	}
	synchronized public void unlock()
	 {
		 lockerNumberContorl++;
		 System.out.println("unlocker count:"+lockerNumberContorl);
	 }
	}
public class multiConcurrentControl {
	
	static public Locker preLocker = new Locker(10);
	static public long calculateByThreads(int activeThreads,int threadN) throws InterruptedException
	{
		//graduate 1 - 10
		System.out.println("------------------------------>calculating..........");
		MonitorStatus.setCounter(activeThreads);
		MonitorStatus.startTimeCount();
		PresentationContorl threads[] = new PresentationContorl[threadN];
		for(int i=10001;i<10001+threadN;i++)
		{	
			threads[i-10001] = new PresentationContorl(i);
			threads[i-10001].start();
			
		}
		
		while(!MonitorStatus.getFinished())
		{
			Thread.sleep(20);
		}
		long rt= MonitorStatus.getTimeConsumed();
		
		Thread.sleep(2000);
		System.out.println("<------------------------------Out fo calculating---------");
		return rt;
	}
	static public void main(String []  args) throws InterruptedException
	{
		int totalThreads = 50;
		int graduateN = 10;
		Long[] consumedTimes = new Long[graduateN];
		for(int i=1;i<=10;i++)
			consumedTimes[i-1] = calculateByThreads(i,totalThreads);
		for(long each:consumedTimes)
			System.out.print("	"+each);
	}
}

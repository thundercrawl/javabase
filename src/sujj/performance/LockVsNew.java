package sujj.performance;

import logger.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;


class testUnitNew
{
	public void linkString ()
	{
		String a ="1"+"2";
		
		}
	
	}

class testUnitLock
{
	static ReentrantLock lock = new ReentrantLock();
	static AtomicInteger lockInt = new AtomicInteger(0);
	public static  synchronized void linkString()
	{
		String a = "1"+"2";
	}
	
	public static  synchronized void linkStringConcurrent()
	{
		String a;
		while(LockVsNew.count-->0)
			a = "1"+"2";
	}
	public static void linkStringConcurrentLock()
	{
		String a;
		lock.lock();
		while(LockVsNew.count-->0)
			a = "1"+"2";
		
		lock.unlock();
		 
	}
	
	public static void linkStringConcurrentAtomic()
	{
		String a;
		while(!lockInt.compareAndSet(0, 1));
		while(LockVsNew.count-->0)
			a = "1"+"2";
		
		
		lockInt.set(0);
	}
	
	public static void computeShort()
	{
		
	}
	
	public static void computeLong()
	{
		
	}
}
public class LockVsNew {

	public static final int cnt =100000000;
	
	public  volatile static int  count = cnt;
	public synchronized static boolean   isCountBiggerThanZero()
	{
		if(count >0) return true;
		else return false;
	}
	public static void test1()
	{
		long start = System.currentTimeMillis();
		int lcnt =cnt;
		System.out.println("test1 exec "+lcnt);
		while(lcnt-->0)
		new testUnitNew().linkString();
		Log.info("test1 finished in "+new Long(System.currentTimeMillis()-start).toString()+" ms");
	}
	public static void test2()
	{
		long start = System.currentTimeMillis();
		int lcnt =cnt;
		System.out.println("test2 exec "+lcnt);
		while(lcnt-->0)
		 testUnitLock.linkString();
		Log.info("test2 finished in "+new Long(System.currentTimeMillis()-start).toString()+" ms");
	}
	
	class test3R implements Runnable
	{
		ThreadLocal<Integer> cnt = new ThreadLocal<Integer>();
	
		public void run()
		{
			
				while(LockVsNew.count>0)
				testUnitLock.linkStringConcurrentAtomic();
				
				System.out.println("finsihed count="+LockVsNew.count);
		}
	}
	public static void test3()
	{
		int nthreads = 10;
		ExecutorService ex = Executors.newFixedThreadPool(nthreads);
		
		long start = System.currentTimeMillis();
		int lcnt =cnt;
		System.out.println("test3 exec "+LockVsNew.count);
		
		while(nthreads-->0)
		ex.submit(new LockVsNew().new test3R());
	
		ex.shutdown();
		while(!ex.isTerminated());
		Log.info("test1 finished in "+new Long(System.currentTimeMillis()-start).toString()+" ms");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
		test3();
	}

}

package sujj.performance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import logger.Log;

public class BlockVsNoBlocking {

	public static final int cnt= 10;
	public static Counter ct = new Counter();
	public static NonblockingCounter nct = new NonblockingCounter();
	class testBlock implements Runnable
	{
		
	
		public void run()
		{
			System.out.println("Start thread:="+Thread.currentThread().getId());;
			ct.increment();
		}
	}
	
	class testNonBlock implements Runnable
	{
		
	
		public void run()
		{
			nct.increment();
		}
	}
	public static void test1()
	{
		int nthreads = 10;
		ExecutorService ex = Executors.newFixedThreadPool(nthreads);
		
		long start = System.currentTimeMillis();
		int lcnt =cnt;
		System.out.println("test1 exec "+BlockVsNoBlocking.cnt);
		
		while(lcnt-->0)
		ex.submit(new BlockVsNoBlocking().new testBlock());
	
		ex.shutdown();
		while(!ex.isTerminated());
		Log.info("test1 finished in "+new Long(System.currentTimeMillis()-start).toString()+" ms, counter="+ct.getValue());
	}
	
	public static void test2()
	{
		String testname = "test2";
		int nthreads = 10;
		ExecutorService ex = Executors.newFixedThreadPool(nthreads);
		
		long start = System.currentTimeMillis();
		int lcnt =cnt;
		System.out.println(testname+" exec "+BlockVsNoBlocking.cnt);
		
		while(lcnt-->0)
		ex.submit(new BlockVsNoBlocking().new testNonBlock());
	
		ex.shutdown();
		while(!ex.isTerminated());
		Log.info(testname+" finished in "+new Long(System.currentTimeMillis()-start).toString()+" ms, counter="+nct.getValue());
	}
	
	public static void test3()
	{

		String testname = "test3";
			
		long start = System.currentTimeMillis();
		int lcnt =cnt;
		int counter = 0;
		System.out.println(testname+" exec "+BlockVsNoBlocking.cnt);
		
		while(lcnt-->0) 
			{
				Util.cal1();
				counter++;
			}
			
		Log.info(testname+" finished in "+new Long(System.currentTimeMillis()-start).toString()+" ms, counter="+counter);
	}
	public static void main(String[] args)
	{
		test1();
		test2();
		test3();
	}
}

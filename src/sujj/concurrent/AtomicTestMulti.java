package sujj.concurrent;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

interface const1
{
	public  int max = 1000000;
}
class m1 extends Thread implements const1
{
	public void run()
	{
		
		int max1 = max;
		while(max1 -->0)
		{
			AtomicTestMulti.inc1();	
			
		}
		
	}
}
class m2 extends Thread implements const1
{
	public void run()
	{

		int max1 = max;
		while(max1 -->0)
		{
			AtomicTestMulti.inc2();	
			
		}
		
		
	}
}

class m3 extends Thread implements const1
{
	public void run()
	{
		int max1 = max;
		while(max1 -->0)
		{
			AtomicTestMulti.inc3();	
			
		}
		
	}
}
public class AtomicTestMulti {
	public static int c1 = 0;
	public static AtomicInteger c2 = new AtomicInteger(0);
	public static int c3 = 0;
	public static ReentrantLock lock = new ReentrantLock();
	public final static int MaxThreads = 20	;
	public static void run1() throws InterruptedException
	{
		int i = 0;
		Thread t = null;
		HashMap h1 = new HashMap();
		while(i  <MaxThreads)
		{
			t = new m1();
			h1.put(i++, t);
			t.start();
		}
		
		i=0;
		while( i < MaxThreads)
		{
			t= (Thread)h1.get(i++);
			t.join();
			
		}
	}
	
	public static void run2() throws InterruptedException
	{
		int i = 0;
		Thread t = null;
		HashMap h1 = new HashMap();
		while(i  <MaxThreads)
		{
			t = new m2();
			h1.put(i++, t);
			t.start();
		}
		
		i=0;
		while( i < MaxThreads)
		{
			t= (Thread)h1.get(i++);
			t.join();
			
		}
	}

	public static void run3() throws InterruptedException
	{
		int i = 0;
		Thread t = null;
		HashMap h1 = new HashMap();
		while(i  <MaxThreads)
		{
			t = new m3();
			h1.put(i++, t);
			t.start();
		}
		
		i=0;
		while( i < MaxThreads)
		{
			t= (Thread)h1.get(i++);
			t.join();
			
		}
	}
	
	public synchronized static void inc1(){
		
		
			c1++;
			
		
	}
	public static void inc2()
	{
		c2.incrementAndGet();
	}
	public static void inc3()
	{
		lock.lock();
		c3++;
		lock.unlock();
	}
	
	public AtomicTestMulti() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setOut(new PrintStream("c:\\1.txt"));
		
		
		long s = System.currentTimeMillis();
		run1();
		logger.Log.info(System.currentTimeMillis()-s);
		logger.Log.info("value:"+c1);
		
		
		s = System.currentTimeMillis();
		run2();
		logger.Log.info(System.currentTimeMillis()-s);
		logger.Log.info("value:"+c2);
		
		
	
		s = System.currentTimeMillis();
		run3();
		logger.Log.info(System.currentTimeMillis()-s);
		logger.Log.info("value:"+c3);
		
	}

}

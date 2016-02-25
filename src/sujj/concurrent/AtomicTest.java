package sujj.concurrent;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class w1 extends Thread
{
	public void run()
	{
		int max = 100000000;
		while(max-->0)
		{
			AtomicTestMulti.inc1();	
			
		}
		
	}
}
class w2 extends Thread
{
	public void run()
	{

		int max = 100000000;
		while(max-->0)
		{
			AtomicTestMulti.inc2();	
			
		}
		
		
	}
}

class w3 extends Thread
{
	public void run()
	{
		int max = 100000000;
		while(max-->0)
		{
			AtomicTestMulti.inc3();	
			
		}
		
	}
}
public class AtomicTest {
	public static int c1 = 0;
	public static AtomicInteger c2 = new AtomicInteger(0);
	public static int c3 = 0;
	public static ReentrantLock lock = new ReentrantLock();
	
	
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
	
	public AtomicTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		// TODO Auto-generated method stub
		System.setOut(new PrintStream("c:\\1.txt"));
		Thread t1_1 = new m1();
		Thread t1_2 = new m1();
		
		t1_1.start();
		t1_2.start();
		
		long s = System.currentTimeMillis();
		t1_1.join();
		t1_2.join();
		logger.Log.info(System.currentTimeMillis()-s);
		logger.Log.info("value:"+c1);
		
		Thread t2_1 = new m2();
		Thread t2_2 = new m2();
		
		t2_1.start();
		t2_2.start();
		s = System.currentTimeMillis();
		t2_1.join();
		t2_2.join();
		logger.Log.info(System.currentTimeMillis()-s);
		logger.Log.info("value:"+c2);
		
		
		Thread t3_1 = new m3();
		Thread t3_2 = new m3();
		
		t3_1.start();
		t3_2.start();
		s = System.currentTimeMillis();
		t3_1.join();
		t3_2.join();
		logger.Log.info(System.currentTimeMillis()-s);
		logger.Log.info("value:"+c3);
	}

}

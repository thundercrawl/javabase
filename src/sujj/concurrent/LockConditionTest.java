package sujj.concurrent;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

class LockWorker
{
	private  static ArrayList<String> biggems = new ArrayList<String>();
	private  static int count = 0;
	public static java.util.concurrent.locks.ReentrantLock lock = new ReentrantLock(); 
	
	
	private boolean breakthrough  =false;
	
	public LockWorker(boolean b)
	{
		this.breakthrough = b;
	}
	public static void printcount()
	{
		System.out.println(count);
	}
	
	public void add(String seed)
	{
		biggems.add(seed);
		
	}
	public void delete(String seed)
	{
		biggems.remove(seed);
	}
	synchronized public void increase()
	{
		lock.lock();
		count++;
		if(breakthrough) return;
		lock.unlock();
	}
	synchronized public void decrease()
	{
		count--;
	}
	
	
	
	}

class lock1 implements Runnable
{

	@Override
	public void run() {
		LockWorker.lock.lock();
		System.out.println("lock1 in working");
		try {
			Thread.sleep(3*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LockWorker.lock.unlock();
	}
	}

class lock2 implements Runnable
{

	@Override
	public void run() {
		LockWorker.lock.lock();
		System.out.println("lock2 in working");
	try {
		
		Thread.sleep(3*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	LockWorker.lock.unlock();}
	}

public class LockConditionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Thread(new lock1()).start();
		new Thread(new lock2()).start();
		
		while(true)
		{
			try {
				Thread.sleep(2000);
				LockWorker.printcount();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

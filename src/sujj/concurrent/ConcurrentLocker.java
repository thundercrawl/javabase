package sujj.concurrent;

import java.util.concurrent.atomic.AtomicLong;

class EngineWorker extends Thread
{
	private String _name;
	public EngineWorker(String name)
	{
		_name = name;
		
	}
	public void run()
	{
		try {
			new ConcurrentLocker().readName(_name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class NotQuantum implements Runnable
{
	static  volatile private long _seed = (0-1);
	static AtomicLong _seedC = new AtomicLong(-1);
	public long getDecrease()
	{
		synchronized(this)
		{
			return _seed--;
		}
	}
	public long getAtomicDecrease()
	{
		
		return _seedC.addAndGet(-1);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//long mySeed = new NotQuantum().getDecrease();
		long mySeed = new NotQuantum().getAtomicDecrease();
		logger.Log.info(mySeed);
		
	}
}

public class ConcurrentLocker {
	public Object lock = new Object();
	 public void readName(String who) throws InterruptedException
	{
		synchronized(this)
		{
			
			logger.Log.info(who+" entered");
			Thread.sleep(2000);
		}
		
	}
	
	
	
	public static  void main(String[] args) throws InterruptedException
	{
		
		Object A = new Object();
		Object B = new Object();
		logger.Log.info(ConcurrentLocker.class.getCanonicalName());
		
		System.out.println(A.hashCode()+"		"+B.hashCode());
		Thread t1Thread = new EngineWorker("T1");
		Thread t2Thread = new EngineWorker("T2");
		long ti = System.currentTimeMillis();
		t1Thread.start();
		t2Thread.start();
		t1Thread.join();
		t2Thread.join();
		logger.Log.info("Time elapse : "+( System.currentTimeMillis()- ti));
		for(int i = 1; i < 1000; i++)
		{
			
			new Thread(new NotQuantum()).start();
		}
	}
}

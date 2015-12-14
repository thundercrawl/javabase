package sujj.concurrent;

import java.util.ArrayList;

import logger.Log;

class MonitorThread extends Thread
{
	
	private ArrayList<InterruptTest> _threadStore;
	public MonitorThread()
	{
		_threadStore = new ArrayList<InterruptTest>();
		
	}
	public void addThread(InterruptTest obj)
	{
		_threadStore.add(obj);
	}
	private void stopThreads()
	{
		for(InterruptTest intr:_threadStore)
		{
			Log.info("Stop thread "+ intr.getId());
			intr.setExit();
			intr.interrupt();
			
		}
	}
	public void run()
	{
		Log.info("Monitor start");
		
		
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stopThreads();
		
		Log.info("Monitor exit");
		
	}
	
}
public class InterruptTest extends Thread {
	public boolean  threadContinue = true;
	public String block ="block";
	private boolean _exit = false;
	private String _threadid;
	public InterruptTest(String id)
	{
		_threadid = id;
	}
	public void setExit()
	{
		_exit = true;
	}
	public void run()
	{
		Log.info("run thread "+_threadid);
		try{
			while(threadContinue)
			{
				if(new Integer(_threadid) %2 !=0)
				synchronized(block){
					try {
						block.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						if(_exit == true)
							Log.info("Interrupt the wait thread exit");
						else
							e.printStackTrace();
						break;
					}
				}
				if(this.isInterrupted())
				{
					Log.info("Found interrupted,exit processing");
					break;
				}
			}
		}
		finally
		{
			Log.info("Finally for thread "+_threadid);;
			
		}
		Log.info("exit thread "+_threadid);
	}
	static public void main(String[] args)
	{
		InterruptTest t1 = new InterruptTest("1");
		
		InterruptTest t2 = new InterruptTest("2");
		
		t1.start();
		t2.start();
		MonitorThread mt = new MonitorThread();
		mt.addThread(t1);
		mt.addThread(t2);
		
		mt.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

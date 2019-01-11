package sujj.concurrent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Timer;

import logger.Log;
interface InterruptModule
{
	public void setExit();
	public long getId();
	public void interrupt();
}

class MonitorThread extends Thread
{
	
	private ArrayList<InterruptModule> _threadStore;
	public MonitorThread()
	{
		_threadStore = new ArrayList<InterruptModule>();
		
	}
	public void addThread(InterruptModule obj)
	{
		_threadStore.add(obj);
	}
	private void stopThreads()
	{
		for(InterruptModule intr:_threadStore)
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
class IOConnection extends Thread implements InterruptModule {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			String buffer = "abc";
			FileOutputStream myErrfile = null;
			
			try {
				myErrfile = new FileOutputStream("c:\\err.log");
				PrintStream myErr = new PrintStream(myErrfile);
				Timer timer = new Timer();
				
				while(true)
				{
					
					
					myErr.write(buffer.getBytes());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					logger.Log.info("Error found and exit IOConnection");
					if(null!=myErrfile)myErrfile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
	}

	@Override
	public void setExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		super.interrupt();
	}}


public class InterruptTest extends Thread implements InterruptModule {
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
		IOConnection ioconn = new IOConnection();
		ioconn.start();
		t1.start();
		t2.start();
		MonitorThread mt = new MonitorThread();
		mt.addThread(t1);
		mt.addThread(t2);
		mt.addThread(ioconn);
		mt.start();
		try {
			t1.join();
			t2.join();
			ioconn.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

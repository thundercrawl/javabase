package sujj.concurrent;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;



class  FairQueue <E> extends LinkedBlockingDeque<E>
{
	
}

class Eat implements Runnable
{
	private final int  id ;
	public static boolean out = false;
	public static Object baba = new Object() ;
	
	public Eat(int t)
	{
		id = t;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	synchronized(baba)
	{
		while(!out)
		{
			try {
				baba.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.Log.info(id+"  eat");
		}
	}
	}
	
}

public class fairOrUnfair {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ThreadPoolExecutor e1 = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
		for(int i=0;i<3;i++)
		{
			//new Thread(new Eat(i)).start();
			e1.submit(new Eat(i));
		}
		
		while(true)
		{
			synchronized(Eat.baba)
			{
				
				Eat.baba.notify();
				
			}
			Thread.sleep(1000);
		}
	}



}

package sujj.concurrent;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class V implements Runnable
{
	private int _b;
	public V(int j)
	{
		_b=j;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(100/_b);
	}
}
public class ThreadPoolT1 {

	public ThreadPoolT1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor the = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
		
		for(int i=0;i<5;i++)
			the.submit(new V(i));
	}

}

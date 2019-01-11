package sujj.env;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class T1 extends Thread
{
	public String t_n;
	public Date dt;
	public Map<String,A1> ThreadLocalData = new HashMap<String,A1>();
	
	public T1(String s1, Date t1)
	{	
		t_n = s1;
		dt =  t1;
	}

	public void run()
	{
		logger.Log.info("Thread "+t_n + " enter at time: "+dt.getTime());
	
		ThreadLocalData.put("1", new A1());
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ThreadLocal<A1> a1;
		//ThreadLocalData.clear();
		//ThreadLocalData = null;
		logger.Log.info("Thread "+t_n + " exit at time: "+dt.getTime());
	}
}

class A1 
{
	public void finalize()
	{
		logger.Log.info("Call A1 gc");
	}
}

public class GcTestWhen {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		A1 a1 = new A1();
		//a1 = null;
		System.gc();
		
		T1 t1 = new T1("GC Release Thread: ",new Date() );
		t1.start();
		t1.join();
		
		logger.Log.info("Call system gc");
		
		System.gc();
		//while(true);
	}

}

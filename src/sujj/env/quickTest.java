package sujj.env;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class x1
{
	public String name;
	public x1()
	{
		
		logger.Log.info("create x1 class");
	}
}

class x2 extends x1
{
	public x2()
	{
			logger.Log.info("create x2 class");
		
	}
}
class DelayElements implements Delayed
{

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
public class quickTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		x2 x_1 = new x2();
		x2 x_3 = new x2();
		java.util.SortedSet<String> st;
		logger.Log.info(x_1.hashCode());
		logger.Log.info(x_1.toString());
		logger.Log.info(x_1.toString().hashCode());
		logger.Log.info(x_3.hashCode());
		logger.Log.info(x_3.toString());
		
		DelayQueue<DelayElements> dq = new DelayQueue<DelayElements>();
		 java.util.concurrent.LinkedBlockingQueue<String> bq = new LinkedBlockingQueue<String>();
		 
		final AtomicInteger it =  new AtomicInteger();	 
		final AtomicInteger it1 = it;
		it.getAndIncrement();
		logger.Log.info("it:"+it.get());
		logger.Log.info("it1:"+it1.get());
		
		String file_separator = System.getProperty("file.separator");
		logger.Log.info("File separator : " +file_separator);;
		

}
	
}

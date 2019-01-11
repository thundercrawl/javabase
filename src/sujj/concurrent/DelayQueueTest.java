package sujj.concurrent;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

class DelayItem implements Delayed
{
	private long _start= -1;
	private long _timeout = -1;
	public  DelayItem(long timeout)
	{
		_start = System.currentTimeMillis()/1000/1000;
		_timeout = timeout;
	}
	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		if(System.currentTimeMillis()/1000/1000 -_start < _timeout)
			return System.currentTimeMillis()/1000/1000 -_start ;
		return 0;
	}
	
}

public class DelayQueueTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//assert 1 < 0;
		logger.Log.info("Print me....");
		BlockingQueue<DelayItem> blockingqueue = new DelayQueue();
		blockingqueue.add(new DelayItem(1000));
		Thread.sleep(100);
		
		blockingqueue.poll();
		
		logger.Log.info(Integer.toString(128&0x7f));;
	}

}

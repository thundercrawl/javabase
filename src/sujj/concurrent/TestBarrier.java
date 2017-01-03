package sujj.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class BReady implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(500);
			logger.Log.info("All ready should fire!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
class BWorker implements Runnable
{
	private CyclicBarrier _b;

	public BWorker(CyclicBarrier b)
	{
		
		_b = b;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			_b.await();
			logger.Log.info("Worker start work!");;
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
public class TestBarrier {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CyclicBarrier b = new CyclicBarrier(3, new BReady());
		
		for (int i =0;i <3;i++)
		{
			new Thread(new BWorker(b)).start();
			
		}
	}

}

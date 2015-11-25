package sujj.jniCall;

class latch
{
	

}

class NotifyThread extends Thread
{
	private latch _lb;
public NotifyThread(latch lb)
{
	_lb=lb;
	}
public void run()
{
	try {
		System.out.println("Entering the Notifyer area and wait 5 seconds to do that");
		Thread.sleep(5000);
		synchronized(_lb)
		{
			_lb.notifyAll();
		}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
public class MiniHttpServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long startTime = System.currentTimeMillis();
		
		latch lb = new latch();
		NotifyThread tr = new NotifyThread(lb);
		tr.start();
		try {
			synchronized(lb){
			lb.wait(10000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Console wait time:"+(System.currentTimeMillis()-startTime));
		
	}

}

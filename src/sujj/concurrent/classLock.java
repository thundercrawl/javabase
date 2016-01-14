package sujj.concurrent;

class workerMyboy extends Thread
{
	public int _threadid = -1;
	public  workerMyboy(int id)
	{
		_threadid = id;
	}
	public void run()
	{
		try {
			//logger.Log.info(_threadid+" getMyboy() "+classLock.getMyboy(_threadid));
			classLock.getInstance().printMsg();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

public class classLock {
	static int _myboy = -1;
	public static classLock _c = null;
	public classLock() throws InterruptedException
	{
		//Thread.sleep(5000);
	}
	
	public static classLock getInstance() throws InterruptedException
	{
		if(_c == null)
		{
			
			synchronized(classLock.class)
			{
				if( _c== null)
				{
					_c = new classLock();	
				}
			}
			
		}
		return _c;
		
	}
	public void printMsg()
	{
		logger.Log.info("hello dear !");
	}
	public static int getMyboy(int _id) throws InterruptedException
	{
		logger.Log.info(_id+" 1 ");
		if(_myboy!=1)
		{
			logger.Log.info(_id+" 2 ");
			if(_id == 10002)
			{
				Thread.sleep(1000);
				logger.Log.info(_id+"secondly boy is "+classLock._myboy);
			}
			synchronized(classLock.class)
			{
				
				logger.Log.info(_id+" boy is "+classLock._myboy + " sleeping");
				Thread.sleep(5000);
				_myboy = 1;
				
				return _myboy;
				
			}
			
		}
		
		return _myboy;
		
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Class cl =  classLock.class;
		
		
		Thread t1 = new workerMyboy(10001);
		Thread t2 = new workerMyboy(10002);
		t1.start();
		t2.start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.join();
		t2.join();
		
	}

}

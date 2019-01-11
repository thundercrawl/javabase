package sujj.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.itextpdf.text.log.LoggerFactory;




abstract class baseThread extends Thread
{
	private long duration;
	
	private void taskstart()
	{
		duration = System.currentTimeMillis();
	}
	private void  taskend()
	{
		System.out.println(getName()+" end in "+(System.currentTimeMillis()-duration));
	}
	
	public void run()
	{
		taskstart();
		runTask();
		taskend();
	}
	
	abstract void runTask();
	
}
class LoggingThreadFactory implements ThreadFactory
{

	
	
    @Override
    public Thread newThread(Runnable r)
    {
        Thread t =(Thread) new Thread(r);
        
        System.out.println("spawn new thread, for running");
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                            }
        });

        return t;
    }
}

class AddItemThread extends baseThread
{

	public AddItemThread()
	{
		this.setName("AddItemThread");
	}
	@Override
	void runTask() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
			
		}
	}
	

	
}

class deleteItemThread extends baseThread
{
	public deleteItemThread()
	{
		setName("deleteItemThread");
	}
	@Override
	void runTask() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			 
		}
	}
	}
public class ThreadFactoryTest {

	
	
	public static void main(String[] args)
	{
		
		ExecutorService executor = Executors.newFixedThreadPool(100, new LoggingThreadFactory());

		executor.submit(new deleteItemThread());
		executor.submit(new deleteItemThread());
		executor.submit(new AddItemThread() );
		
		executor.shutdown();
	}
}

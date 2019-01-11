package sujj.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.ThreadLocal;
import java.lang.ref.WeakReference;


class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override protected Integer initialValue() {
                return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
}

class A1
{
	private long t_id;
	public A1(long id)
	{
		t_id = id;
	}
	public void finalize()
	{
		
		logger.Log.info(t_id+"A1 gc call");;
	}
}

class CT1 implements Runnable
{
	private final ThreadLocal<A1> s1= new ThreadLocal<A1>();
	//private final WeakReference<A1> w1 = new WeakReference<A1>(new A1());
	public void run()
	{
		A1 a1 = new A1(ThreadId.get());
		//s1.set(new A1());;
		logger.Log.info("Thread id is : " + ThreadId.get());
	}
}
public class GCThreadLocal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CT1 t1 = new CT1();
		Thread t = new Thread(t1);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ExecutorService ex = Executors.newFixedThreadPool(50);
		for(int i =0; i <50 ; i ++)ex.submit(new CT1());
		
		//System.gc();
		while(true);
		
		
	}

}

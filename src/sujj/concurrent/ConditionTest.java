package sujj.concurrent;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


class waitCondition implements Runnable
{
private ConditionTest t;
	public waitCondition(ConditionTest t)
	{
		this.t= t;
	}
	@Override
	public void run() {
		t.waitqueue.lock();
		try {
			System.out.println("wait for signal");
			if(t.waitfinish == false)
				t.waitcondition.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("get singal and start work");
		t.waitqueue.unlock();
	}
	}
class workingCondition implements Runnable
{
	private ConditionTest t;
	public workingCondition(ConditionTest t)
	{
		this.t= t;
	}
	

	@Override
	public void run() {
		System.out.println("working get lock");
		t.waitqueue.lock();
		
		System.out.println("send wake signal");
		t.waitfinish = true;
		t.waitcondition.signal();
		t.waitqueue.unlock();
	}
	}

public class ConditionTest {
public ReentrantLock waitqueue = new ReentrantLock();
public Condition waitcondition = waitqueue.newCondition();
public boolean waitfinish = false;
public static void main(String[] args)
{
	ConditionTest t = new ConditionTest();
	Thread t2 = new Thread(new workingCondition(t));
	Thread t1 = new Thread(new waitCondition(t));
			
	
	t1.start();
	t2.start();
	
	try {
		t1.join();
		t2.join();
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	
}
}

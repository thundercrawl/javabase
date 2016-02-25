package sujj.concurrent;
import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;


class ob1 extends Object{
	
	static <T extends Object & Comparable<? super T>> T  min() {
		
		return null;
		
		
	}
	
}

class CheckT extends Thread
{
	public void run(){
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

}

public class ConnTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		new ob1().min();
		ReentrantLock lock = new ReentrantLock();
		Condition con = lock.newCondition();
		lock.lock();
		con.await();
		con.signal();
		lock.unlock();
		
		Thread t1 = new CheckT();
		t1.start();
		
		t1.join();
	}

}

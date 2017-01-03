package sujj.concurrent.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.LinkedBlockingDeque;

class WorkerRunnable implements Runnable
{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			String msg = (String)BlockingLinkedQueue.messagesQueue.get();
			logger.Log.info("consumer get the msg as :  " + msg);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
class WorkerGenerateMsg extends Thread
{
	public void run()
	{
		try {
			while(true)
			{
				logger.Log.info("Put message");
				BlockingLinkedQueue.messagesQueue.put("111");
				Thread.sleep(2000);
				}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String BigInteger(long currentTimeMillis) {
		// TODO Auto-generated method stub
		return null;
	}
}
public class BlockingLinkedQueue<E> {
	
	public static BlockingLinkedQueue<String> messagesQueue = new BlockingLinkedQueue<String>();
	/** Doubly-linked list node class */
	static final class Node<E> {
	    /**
	     * The item, or null if this node has been removed.
	     */
	    E item;

	    /**
	     * One of:
	     * - the real predecessor Node
	     * - this Node, meaning the predecessor is tail
	     * - null, meaning there is no predecessor
	     */
	    Node<E> prev;

	    /**
	     * One of:
	     * - the real successor Node
	     * - this Node, meaning the successor is head
	     * - null, meaning there is no successor
	     */
	    Node<E> next;

	    Node(E x) {
	        item = x;
	    }
	};

	public Node<E> firstNode;
	public Node<E> lastNode;
	final ReentrantLock lock = new ReentrantLock() ;
	//final ReentrantLock putlock = new ReentrantLock();
	Condition NotEmpty = lock.newCondition();
	public BlockingLinkedQueue()
	{
		firstNode = new Node<E>(null);
	}
	public E get() throws InterruptedException
	{
		
		while(firstNode.item == null)
			this.NotEmpty.await();
		
		lock.lockInterruptibly();
		
		E rt = (E) firstNode.item;
		firstNode = firstNode.next;
		lock.unlock();
		return rt;
	}
	public void put(E a) throws InterruptedException
	{
		lock.lockInterruptibly();
		
		if(firstNode.item == null)
		{
				firstNode.item =   a;
				lastNode = new Node<E>(a);
		}
		
		else
		{
			
			lastNode.next = new Node<E>(a);
			lastNode.next.prev = lastNode;
			lastNode =lastNode.next;
		}
		NotEmpty.signal();
		lock.unlock();
		
		
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WorkerGenerateMsg gm = new WorkerGenerateMsg();
		ExecutorService ex = Executors.newFixedThreadPool(50);
		
		int count =20;
		while(count-->0)
		{
			
			ex.submit(new WorkerRunnable());
		
		}
		gm.start();
		gm.join();
		
		//import java.util.concurrent.LinkedBlockingQueue<E>
	}

}



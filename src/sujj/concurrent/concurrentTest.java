package sujj.concurrent;

import java.util.ArrayList;

class TaskLocker 
{
	}
class Worker extends Thread
{
	public int workerID = -1;
	public Worker(int workerID)
	{
		this.workerID = workerID;
	}
	public void run()
	{
		while(true)
		{	
			if(concurrentTest.tasks.size() >0)
			{	
				TaskItem task = concurrentTest.getTask(0);
				if(task !=null)//make sure the task been get by others:)
				{	
					System.out.println("Processing task id:"+task.taskID+" by worker:"+this.workerID);
				
					task.exeucte();
				}
				/*
				try {
					System.out.println("Task executor run into waiting, by worker:"+this.workerID);
					synchronized(concurrentTest.loker)
					{
						concurrentTest.loker.wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			} else
				try {
					System.out.println("Task executor run into waiting");
					synchronized(concurrentTest.loker)
					{
						concurrentTest.loker.wait();
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
};
class ReadtaskItemImpl extends TaskItem 
{

	
	public ReadtaskItemImpl(int id)
	{
		super();
		this.taskID = id;
		this.taskContent = "ReadTask";
	}
	public void exeucte() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Doing Read Task....");
	}
	@Override
	public TaskItem clone() {
		// TODO Auto-generated method stub
		
			TaskItem cloned = new ReadtaskItemImpl(this.taskID);
			
			cloned.taskContent = this.taskContent;
			
		
		return cloned;
	}
	
	
}
class WritetaskItemImpl extends TaskItem
{
	public WritetaskItemImpl(int id)
	{
		super();
		this.taskID = id;
		this.taskContent = "WriteTask";
	}
	
	public void exeucte() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Doing Write Task....");
	}

	@Override
	public TaskItem clone() {
		// TODO Auto-generated method stub
		
		TaskItem cloned = new WritetaskItemImpl(this.taskID);
		
		cloned.taskID = this.taskID;
		cloned.taskContent = this.taskContent;
		
		
		return cloned;

	}
	
	
}

public class concurrentTest {
	
	static public TaskLocker loker = new TaskLocker();
	static public ArrayList<TaskItem> tasks = new ArrayList<TaskItem>();
	static public int Task_ID = 0; 
	static public int  Last_TASK_ID = 0;
	
	public static void main(String[] args) throws InterruptedException
	{
		//create 20 worker to do task
		for(int i =0; i< 25;i++)
		{	
			Worker task =new Worker(i);
			task.start();
		}
		while(true)
		{
			Thread.sleep(1000);
			addTask();
			printStat();
			synchronized(concurrentTest.loker)
			{
				concurrentTest.loker.notifyAll();
			}
		}
	}
	static synchronized public void addTask()
	{
		//spawn task by random..0
		
		
		for(int i=0;i<600;i++)
		{	
			tasks.add(new ReadtaskItemImpl(concurrentTest.Task_ID++));
			tasks.add(new ReadtaskItemImpl(concurrentTest.Task_ID++));
			tasks.add(new WritetaskItemImpl(concurrentTest.Task_ID++));
		}
		
		
	}
	static synchronized public void removeTask(int index)
	{
		tasks.remove(index);
		if(tasks.size() > 25)System.out.println("Current task queue size is "+tasks.size());
	}
	static synchronized public TaskItem getTask(int index)
	{
		if(tasks.size() >0)
		{	
			TaskItem task =  tasks.get(index).clone();
			removeTask(index);
			return task;
		}
		return null;
	}
	static synchronized public void printStat()
	{
		System.out.println("Task processed rate:"+(concurrentTest.Task_ID-concurrentTest.Last_TASK_ID));
		concurrentTest.Last_TASK_ID = concurrentTest.Task_ID;
	}
	
}

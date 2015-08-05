package sujj.concurrent;

abstract public  class TaskItem {
	public int taskID=-1;
	public String taskContent = null;
	abstract public void exeucte();
	public TaskItem()
	{
		
	}
	abstract public TaskItem clone();
	
}

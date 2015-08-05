package sujj.concurrent;

public class SimpleThread extends Thread {

	public void run()
	{
		long n = 1000000000;
		for(;n>0;n--);	
		System.out.println("Over!");
	}
	public static void main(String args[])
	{
		//new SimpleThread().start();
		
		long n = 1000000000;
		for(;n>0;n--)
		{
			//if(n/2>0)continue;	
			if(n/50000000>321) System.out.println("Big data");  
		}
		System.out.println("Over!");
		/*
		while(true)
		{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}

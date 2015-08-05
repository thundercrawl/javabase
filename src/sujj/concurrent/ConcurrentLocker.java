package sujj.concurrent;

public class ConcurrentLocker {

	
	
	public static  void main(String[] args)
	{
		
		Object A = new Object();
		Object B = new Object();
		for(;;)
			System.out.println(A.hashCode()+"		"+B.hashCode());
		
		
		
	}
}

package serverPrototype_generation_one;

import java.util.concurrent.ThreadPoolExecutor;

public class TestThreadBase {

	
	
	public static void main(String[] args) 
	{
		UserSession us1 = new UserSession("JinJun");
		 ThreadBase th = new ThreadBase(us1);
		 
		 Thread t1 = new Thread(th);
		 t1.start();
		 ThreadPoolExecutor ex = new ThreadPoolExecutor(0, 0, 0, null, null);
		 while(true);
	}
}

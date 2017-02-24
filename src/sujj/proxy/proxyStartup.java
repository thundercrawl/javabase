package sujj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;


 public class proxyStartup {
	 private interface modelBehavior
	 {
		 public void add();
		 public void delete();
	 }
	private class TestProxy1 implements modelBehavior
	{

		@Override
		public void add() {
			// TODO Auto-generated method stub
			logger.Log.entering();
			
		}

		@Override
		public void delete() {
			// TODO Auto-generated method stub
			logger.Log.entering();
		}
		
	}
	public class NoOpAddInvocationHandler implements InvocationHandler {
		 
		  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		    if (method.getName().startsWith("add")) {
		      return false;
		    }
		    return method.invoke(args);
		  }
		
		}
	public proxyStartup() {
		// TODO Auto-generated constructor stub
	}
	public static void proxyT1()
	{
		Object proxy =  Proxy.newProxyInstance(
				  proxyStartup.class.getClassLoader(),
				  new Class[] { modelBehavior.class },
				  new NoOpAddInvocationHandler());
		proxy.toString();
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		proxyT1();
	}

}

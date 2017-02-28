package sujj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import logger.Log;

interface modelBehavior
{
	 public void add();
	 public void delete();
}
class TestProxy1 implements modelBehavior
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

class QuickHandler implements InvocationHandler {
	 private Object obj =null;
	  public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		 Log.info("Proxy invoke called ,method is : "+m.getName());
		 
		 return m.invoke(obj, args);
	  }
	public QuickHandler(Object obj)
	{
		this.obj = obj;
	}
	}

 public class proxyStartup {
	 
	public proxyStartup() {
		// TODO Auto-generated constructor stub
	}
	public static void proxyT1()
	{
		Object obj = new TestProxy1();
		
		modelBehavior proxy = (modelBehavior) Proxy.newProxyInstance(
				obj.getClass().getClassLoader(),
				obj.getClass().getInterfaces(),
				new QuickHandler(obj));
		
		proxy.add();
		
	}
	//that a combine use case , call the methods any where.
	public static void proxyT2()
	{
		proxyT1();
		
		new TestProxy1().delete();
		
	}
	
	public static void proxyT4()
	{
			
	}
	
	//add proxy5
	public static void proxyT5()
	{}
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		Method[] ms =  proxyStartup.class.getMethods();
		
		for(Method m:ms)
		{
			if(m.getName().contains("proxyT"))
			{
				Log.info("running test case "+m.getName());
				m.invoke(null,null);
			}
		}
	}

}

package sujj.proxy;

import java.lang.reflect.Method;

import logger.Log;

public class proxyEndup {
	
	public static void proxyT1()
	{}
	
	public static void proxyT2(){
		
		Log.entering();
	}
	public proxyEndup() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(Method m:proxyEndup.class.getMethods())
		{
			Log.info(m.getName());
		}
		
		for(Method m:proxyStartup.class.getMethods())
		{
			Log.info(m.getName());
		}
	}

}

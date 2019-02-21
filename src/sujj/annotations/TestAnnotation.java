package sujj.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class TestAnnotation {

	public static void onProcess(String authorName,Class yourClass)
	{
		if(authorName.equals("Zhanghai"))
		{
			//call the test methods
			Method[] methods = yourClass.getMethods();
			
			for(Method each:methods)
			{
				System.out.println(each.getName());
				if(each.getName().equals("runData"))
				{
					try {
						each.invoke(yourClass.newInstance());
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<anotationTest> cl = anotationTest.class;
		
		if(cl.isAnnotationPresent(Author.class))
		{
			System.out.println("Author is :"+cl.getAnnotation(Author.class).name());
			onProcess(cl.getAnnotation(Author.class).name(),cl);
		}
		
		if(cl.isAnnotationPresent(Configure.class))
		{
			System.out.println("Configure is :"+cl.getAnnotation(Configure.class).type());
			if(cl.getAnnotation(Configure.class).type().equals( "Service"))
			{
				try {
					
					cl.getMethod("service").invoke(Configure.class.newInstance());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}

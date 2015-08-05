package sujj.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class TestAnnotation {

	public static void onProcess(String authorName,Class yourClass)
	{
		if(authorName.equals("Sujj"))
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
		Class<anotationTest> an = anotationTest.class;
		
		if(an.isAnnotationPresent(Author.class))
		{
			System.out.println("Author is :"+an.getAnnotation(Author.class).name());
			onProcess(an.getAnnotation(Author.class).name(),an);
		}
		
	}

}

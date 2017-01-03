package sujj.core;
import java.lang.reflect.*;
import sun.misc.Unsafe;

public class UnsafeTouch {

	static long static_memory_100M = 1000*1000*1000;
	public static void main(String[] strs) throws IllegalArgumentException, IllegalAccessException
	{
		Field javaField = Unsafe.class.getDeclaredFields()[0];
		javaField.setAccessible(true);
		
		Unsafe unsafe = (Unsafe) javaField.get(null);
		while(true)
		unsafe.allocateMemory(static_memory_100M);
				
	}
}

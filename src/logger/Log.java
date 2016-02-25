package logger;

public class Log {
	public static  void info(String str)
	{
		System.out.println(str);
		
	}
	public static  void info(long str)
	{
		System.out.println(str);
		
	}
	public static void info(boolean str)
	{
		
		System.out.println(str);
	}
	public static void entering()
	{
		final StackTraceElement ste[] = new Exception().getStackTrace();
		//info(ste[0].getClassName()+"."+ste[0].getClassName()+"."+ste[0].getMethodName()+"."+ste[0].getLineNumber());
		//info(ste[1].getClassName()+"."+ste[1].getClassName()+"."+ste[1].getMethodName()+"."+ste[1].getLineNumber());
		info(ste[ste.length-2].getClassName()+"."+ste[ste.length-2].getClassName()+"."+ste[ste.length-2].getMethodName()+"."+ste[ste.length-2].getLineNumber());
	}
}

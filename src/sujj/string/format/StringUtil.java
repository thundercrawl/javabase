package sujj.string.format;

public class StringUtil {

	public static void  exec_1()
	{
		String str1= "a11"+ "\r\n";
		String str2 = "\r\n";
		str1.concat(str2);//not sure why native array copy escape \r\n
		System.out.println("str1 length="+str1.getBytes().length);;
		System.out.print(str1);
		System.out.print(str1);
		
		
		
	}
	/*
	 * split empty and single string
	 */
	public static void exec_2()
	{
		String splitor = ";";
		
		String test1 = "admin.nsf;spr.nsf";
		String test2 = "admin.nsf";
		String test3 = "";
		
		System.out.println(test1.split(splitor).length);
		System.out.println(test2.split(splitor).length);
		System.out.println(test3.split(splitor).length);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec_2();
	}

}

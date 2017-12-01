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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec_1();
	}

}

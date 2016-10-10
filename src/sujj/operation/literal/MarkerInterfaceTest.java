package sujj.operation.literal;

class Test1 
{
	public Test1 clone() throws CloneNotSupportedException
	{
		return (Test1) super.clone();
		
	}

}

public class MarkerInterfaceTest {

	public static void test1() throws CloneNotSupportedException
	{
		Test1 t1 = new Test1();
		Test1 t2 = t1.clone();
		
		
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		test1();
	}

}

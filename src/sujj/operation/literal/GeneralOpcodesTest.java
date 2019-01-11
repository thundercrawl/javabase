package sujj.operation.literal;

class OP1
{
	public final int spe=1;
	static
	{
		System.out.println("OP1 init");
		
	}
	public static int value = 123;
}

class OP2 extends OP1
{
	static
	{
		System.out.println("OP2 init");
		
	}
}

public class GeneralOpcodesTest {

	private boolean checked = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//OP1.value = 321;
		
		Object op1s[] = new OP1[20];
		
		op1s[1] = "hello";
	}

}

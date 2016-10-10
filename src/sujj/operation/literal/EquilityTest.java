package sujj.operation.literal;

class Equility
{
	private int count = -1;
	public Equility(int cnt)
	{
		count = cnt;
	}
	public Equility Equility(Equility obj)
	{
		if(obj !=null)
			return new Equility(obj.count);
		return null;
	}
@Override	public boolean equals(Object e)
	{
		if (this.count ==(((Equility) e).count))
			return true;
		
		return false;
		
	}

@Override public int hashCode()
{
	return count*31;
}
}
public class EquilityTest {
	 enum Fruits {orange,apple,cherry, strawberry,pear};
	final static String string_1 = "const1";
	public static void testcase1()
	{
		String s1 = new String("st1");
		String s2 = new String("st1");
		if(s1 == s2)
			logger.Log.info("testcase1 " + 1);
		else
			logger.Log.info("testcase1 " + 2);
		
	}
	public static void testcase2()
	{
		int i1 = 1;
		int i2 = 2;
		if( i1 == i2)
			logger.Log.info("testcase2 " + 1);
		else
			logger.Log.info("testcas2 " + 2);;
		
	}
	public static void testcase3()
	{
		String s1 = "st3";
		String s2 = "st3";
		if(s1 == s2)
			logger.Log.info("testcase3 " + 1);
		else
			logger.Log.info("testcase3 " + 2);
		
	}
	
	public static void testcase4()
	{
		String s1= new String("testcase4");
		String s2 = s1;
		if(s1 == s2)
			logger.Log.info("testcase4 " + 1);
		else
			logger.Log.info("testcase4 " + 2);
		
		
	}
	public static void testcase5()
	{
		String s1 = "const1";
		String s2 = string_1;
		
		if(s1 == s2)
			logger.Log.info("testcase5 " + 1);
		else
			logger.Log.info("testcase5 " + 2);
	}
	
	
	public static void testcase6()
	{
		Fruits s1 = Fruits.apple;
		Fruits s2 = Fruits.apple;

		if(s1 == s2)
			logger.Log.info("testcase6 " + 1);
		else
			logger.Log.info("testcase6 " + 2);
	}
		
	public static void testcase7()
	{
		Integer len = new Integer(0);
		if(len ==0)
			logger.Log.info("testcase7 "+1);;
		
	}
	
	
	public static void testcase8()
	{
		Integer s1 = new Integer(0);
		Integer s2 = new Integer(0);
		if(s1 == s2)
			logger.Log.info("testcase8 " + 1);
		else
			logger.Log.info("testcase8 " + 2);
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Equility e1 = new Equility(1);
		Equility e2 = e1;
		
		if (e1==e2) logger.Log.info( 1);
		else logger.Log.info(2);
		
		testcase1();
		testcase2();
		testcase3();
		testcase4();
		testcase5();
		testcase6();
		testcase7();
		testcase8();
	}

}

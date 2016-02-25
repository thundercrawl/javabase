package sujj.env;
class x1
{
	public String name;
	public x1()
	{
		
		logger.Log.info("create x1 class");
	}
}

class x2 extends x1
{
	public x2()
	{
			logger.Log.info("create x2 class");
		
	}
}

public class quickTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		x2 x_1 = new x2();
		x2 x_3 = new x2();
		java.util.SortedSet<String> st;
		logger.Log.info(x_1.hashCode());
		logger.Log.info(x_1.toString());
		logger.Log.info(x_1.toString().hashCode());
		logger.Log.info(x_3.hashCode());
		logger.Log.info(x_3.toString());
	}

}

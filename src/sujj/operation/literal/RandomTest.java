package sujj.operation.literal;


import java.util.Random;


public class RandomTest {

	private static long cnt=0;
	
public static void test1()
{
	cnt =0;
	for(long i = 0;i<1000000000;i++)
	{
		if( Math.random() <0.5)
			cnt++;
		
	}

	logger.Log.info(cnt);
}

public static void test2()
{
	cnt =0;
	int seed = 1000000;
	Random ran = new Random(seed);
	;
	
	for(long i = 0;i<seed;i++)
	{
		if( ran.nextInt() <0.5*seed)
			cnt++;
		
	}
	
	logger.Log.info(cnt);
	
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		test2();
	}

}

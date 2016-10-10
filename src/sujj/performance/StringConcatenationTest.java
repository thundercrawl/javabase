package sujj.performance;

import logger.Log;

public class StringConcatenationTest {

	public static void test1()
	{
		String s1 = "t1";
		String s2 = "t2";
		String s3 = s1+s2;
		
		long start = System.currentTimeMillis();
		int cnt = 10000;
		while(cnt-->0)
			s3=s3+s1+s2;
		Log.info(System.currentTimeMillis()-start+" size:"+s3.length());
		//logger.Log.info(s1+s2);
	}
	public static void test2()
	{
		String s1="s1";
		String s2="s2";
		StringBuilder sb = new StringBuilder(s1+s2);
		byte[] b = new byte[s1.length()+s2.length()];
		
		
		long start = System.currentTimeMillis();
		int cnt = 10000;
		while(cnt-->0)
		{
			sb.append(s1);
			sb.append(s2);
		}
		Log.info(System.currentTimeMillis()-start+" size:"+sb.length());

		
		
	}
	
	public static void test3()
	{
		String s1 = "m1";
		String s2 = "m2";
		String s3 = "";
		int cnt = 10000;
		long start = System.currentTimeMillis();
		while(cnt-->0) {
			
			s3 = new StringBuilder(s3).append(s1+s2).toString();
			
		} 
		Log.info(System.currentTimeMillis()-start+" size:"+s3.length());
	}
	public static void perf1()
	{
		long start = System.currentTimeMillis();
		int cnt = 1000000;
		while(cnt-->0)
			test1();
		Log.info(System.currentTimeMillis()-start);
		
	}
	
	public static void perf2()
	{
		long start = System.currentTimeMillis();
		
		int cnt = 1000000;
		while (cnt-- >0)
			test2();
		
		Log.info(System.currentTimeMillis()-start);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
		test3();
	}

}

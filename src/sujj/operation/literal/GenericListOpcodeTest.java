package sujj.operation.literal;

import java.util.ArrayList;
import java.util.List;

public class GenericListOpcodeTest {

	public static void test1()
	{
		
		// Fails at runtime!
		Object[] objectArray = new Long[1];
		objectArray[0] = "I don't fit in"; // 
	}
	public static void test2()
	{
		//list
		List<Long> objectList = new ArrayList<Long>();
		//objectList.add("");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

}

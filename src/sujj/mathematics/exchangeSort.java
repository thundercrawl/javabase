package sujj.mathematics;

import java.util.ArrayList;
import java.util.Random;

public class exchangeSort {
	public static ArrayList<Integer> innerArray = null;
	public static void exchange(int a, int b)
	{
		int tmp = (Integer)innerArray.get(a);
		innerArray.set(a, innerArray.get(b));
		innerArray.set(b, tmp);
		
	}
	public static void printArray()
	{
		System.out.println(innerArray.toString());
	}
	public static void quickSort()
	{
		long startTime = System.currentTimeMillis();
		
		int length = innerArray.size();
		
		
		
		System.out.println("quick sort,Time consumed milliseconds:"+(System.currentTimeMillis()-startTime));
	}
	public static void BubbleSort()
	{
		long startTime = System.currentTimeMillis();
		//sort array then,[ Bubble sort ]
		int end = innerArray.size();
		for(int i=0;i<end;i++)
			for(int j=end-1;j>i;j--)
				if(innerArray.get(j)< innerArray.get(j-1))
					exchange(j-1,j);
		
		System.out.println("Bubble sort,Time consumed milliseconds:"+(System.currentTimeMillis()-startTime));
		//printArray();	
	}
	public static void SelectSort()
	{
		long startTime = System.currentTimeMillis();
		int end = innerArray.size();
		//sort array,[ Selection sort ]
		int SelectTmp = end-1;
		int minSelect = innerArray.get(innerArray.size()-1);
		for(int i=0;i<end;i++)
		{	
			SelectTmp = end-1;
			minSelect = innerArray.get(innerArray.size()-1);
			 for(int j=end-1;j>i;j--)
			 {	 
				if(innerArray.get(j-1)< minSelect)
				{	
					SelectTmp= j-1;
					minSelect = innerArray.get(j-1);
					//System.out.println("min Select :"+minSelect);
				}
				
				
			 }
			if(SelectTmp != -1)
				exchange(i,SelectTmp);
				//System.out.println(i+" "+minSelect);
			
			
		}
		System.out.println("select sort,Time consumed milliseconds:"+(System.currentTimeMillis()-startTime));
		
	}
	public static void insertSort()
	{
		long startTime = System.currentTimeMillis();
		ArrayList<Integer>insertArray = new ArrayList<Integer>();
		
		int end = innerArray.size()-1;
		insertArray.add(0,innerArray.get(0));
		for(int i =1; i<end+1;i++)
		{
			for(int j=0; j<insertArray.size();j++)
			{
				//System.out.println("Array size:"+insertArray.size());
				if(innerArray.get(i)<insertArray.get(j))
				{	
					insertArray.add(j,innerArray.get(i));
					//System.out.println("Insert item is:"+innerArray.get(i));
					break;
				}
				else if(j==(insertArray.size()-1))
				{
					insertArray.add(innerArray.get(i));
					break;
				}
			}
		}
		innerArray = insertArray;
		System.out.println("insert sort,Time consumed milliseconds:"+(System.currentTimeMillis()-startTime));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stubl
		ArrayList<Integer> arrayForSort = new ArrayList<Integer>();
		for(int i=0;i < 50000;i++)
			arrayForSort.add((int) (Math.random()*100));
		innerArray = arrayForSort;
		//printArray();
		BubbleSort();
		
		//insertSort();
		//SelectSort();
		//printArray();
		
	}

}

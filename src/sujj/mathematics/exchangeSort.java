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
	public static void print(Object obj)
	{
		
		System.out.println(obj);
	}
	public static void printArray()
	{
		System.out.println(innerArray.toString());
	}
	public static void quickSort()
	{
		if(low>=hi) return;
		
		
		int p = findPivat(a,low,hi);
		
		quickSort(a,low,p);
		quickSort(a,p+1,hi);
		
		
		
		//System.out.println("quick sort,Time consumed milliseconds:"+(System.currentTimeMillis()-startTime));
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
	private static void merge(ArrayList<Integer>a,int low,int hi)
	{
		print(low+" "+hi);
		ArrayList<Integer>aux = new ArrayList<Integer>();
		printArray();
		int mid=(low+hi)/2;
		for(int i=low,j=(low+hi)/2+1,index=0;index<(hi-low)+1;index++)
		{
			if(i<mid&&a.get(i)<a.get(j))
			{
				aux.add(index,a.get(i));
				i++;
			}
			else
			{
				
				aux.add(index,a.get(j));
				j++;
			}
				
			
			
		}
		print("aux:"+aux.toString());
		for(int i=0,j=low;i<aux.size();i++)
		{
			a.set(j++, aux.get(i));
			
		}
		
			
		
	}
	public static void mergeSort(ArrayList<Integer>a,int lo, int hi)
	{
		if ( hi == lo)
			return;
		
		mergeSort(a,lo,(lo+hi)/2);
		mergeSort(a,(lo+hi)/2+1,hi);
		
		merge(a,lo,hi);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stubl
		ArrayList<Integer> arrayForSort = new ArrayList<Integer>();
		for(int i=0;i < 3;i++)
			arrayForSort.add((int) (Math.random()*100));
		innerArray = arrayForSort;
		printArray();
		//mergeSort(innerArray,0,innerArray.size()-1);
		quickSort(innerArray,0,innerArray.size()-1);
		
		
		printArray();
		//BubbleSort();
		
		//insertSort();
		//SelectSort();
		//printArray();
		
	}

}

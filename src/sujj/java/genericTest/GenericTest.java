package sujj.java.genericTest;

import java.util.Comparator;


class AllinOneComparator implements Comparator,Comparable
{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class BigBom implements Comparator
{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class SortGroup<T>
{
	
	public void Sort(T[] a,Comparator<? extends T> c)
	{
		
		logger.Log.info("you call ");
	}
}
public class GenericTest {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortGroup<BigBom> bigs = new SortGroup<BigBom>();
		BigBom[] boms = null;
		bigs.Sort(boms,new BigBom() );
		assert 1==2;
	}

}

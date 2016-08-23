package sujj.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class CookiesInCheat<T> implements Iterable<T>
{

	private ArrayList<T> cheats = new ArrayList<T>();
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return  cheats.iterator();
	}
	public CookiesInCheat()
	{
		
	
	}
	public void addCookie(T e)
	{
		cheats.add(e);
		
	}
	
}
class CookiesNotInCheat<T> implements Iterator<T>
{

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}



public class IterabeForEachTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CookiesInCheat<String> st = new CookiesInCheat<String>();
		st.addCookie("1");
		st.addCookie("2");
		for(String e:st)
		{
			System.out.println(e);;
		}
		
	}

}

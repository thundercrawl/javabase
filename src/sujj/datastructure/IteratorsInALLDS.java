package sujj.datastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class IteratorsInALLDS {

	/**
	 * @param <E>
	 * @param args
	 */
	public static <E> void main(String[] args) {
		// TODO Auto-generated method stub
		Set st = new HashSet<Integer>();
		st.add(1);
		st.add(2);
		LinkedList lk = new LinkedList();
		lk.add(1);
		lk.add(2);
		ArrayList al = new ArrayList();
		al.add(1);
		al.add(2);
		
		Vector vt = new Vector();
		vt.add(1);
		vt.add(2);
		
		Stack<Integer> st1 = new Stack<Integer>();
		st1.push(1);
		st1.push(2);
		Iterator  it = lk.iterator();
		it = al.iterator();
		it = st1.iterator();
		it = vt.iterator();
		it = st1.iterator();
		
		Hashtable ht = new Hashtable();
		ht.put(1, 1);
		ht.put(1, 2);
		System.out.println(ht.size());
		while(it.hasNext())
		{
			System.out.println("value:"+it.next().toString());
		}
		
	}

}

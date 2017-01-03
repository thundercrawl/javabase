package sujj.mathematics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

/******************************************************************************
 *  Compilation:  javac BinarySearchST.java
 *  Execution:    java BinarySearchST
 *  
 *  Symbol table implementation with ordered array. Uses repeated
 *  doubling to resize the array.
 *
 *  % java BinarySearchST
 *  128.112.136.11
 *  208.216.181.15
 *  null
 *
 *
 ******************************************************************************/


class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_SIZE = 8;

    private Value[] vals;    // symbol table values
    private Key[] keys;      // symbol table keys
    private int n = 0;       // number of elements

    public BinarySearchST() {
        this(INIT_SIZE);
    }

    public BinarySearchST(int initCapacity) {
        vals = (Value[]) new Object[initCapacity];
        keys = (Key[]) new Comparable[initCapacity];
    }


    public boolean isEmpty() { return n == 0; }
    public int     size()    { return n;      }

    // double the size of the arrays
    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            tempv[i] = vals[i];
        for (int i = 0; i < n; i++)
            tempk[i] = keys[i];
        keys = tempk;
        vals = tempv;
    }

    // add key-value pair
    public void put(Key key, Value val) {
        if (n >= vals.length) resize(2*n);

        // duplicate
        if (contains(key)) {
            int i = bsearch(key);
            vals[i] = val;
            return;
        }

        // shift key-value pairs one position to right, and add new key-value pair
        int i = n;
        while ((i > 0) && (key.compareTo(keys[i-1]) < 0)) {
            keys[i] = keys[i-1];
            vals[i] = vals[i-1];
            i--;
        }
        vals[i] = val;
        keys[i] = key;
        n++;
    }

    // does symbol table contain given key?
    public boolean contains(Key key) {
        int i = bsearch(key);
        return i >= 0;
    }

    // return value associated with given key, or null if no such key
    public Value get(Key key) {
        int i = bsearch(key);
        if (i == -1) return null;
        return vals[i];
    } 

    // binary search in interval [lo, hi]
    private int bsearch(Key key) {
        int lo = 0, hi = n-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    } 

    // all keys, as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        
        
        for (int i = 0; i < n; i++)
            queue.add(keys[i]);
        return queue;
    }


   /***************************************************************************
    * Test routine.
    ***************************************************************************/
    public static void main(String[] args) {
        BinarySearchST<String, String> st = new BinarySearchST<String, String>();

        // insert some key-value pairs
        st.put("www.cs.princeton.edu",   "128.112.136.11");
        st.put("www.cs.princeton.edu",   "128.112.136.35");
        st.put("www.princeton.edu",      "128.112.130.211");
        st.put("www.math.princeton.edu", "128.112.18.11");
        st.put("www.yale.edu",           "130.132.51.8");
        st.put("www.amazon.com",         "207.171.163.90");
        st.put("www.simpsons.com",       "209.123.16.34");
        st.put("www.stanford.edu",       "171.67.16.120");
        st.put("www.google.com",         "64.233.161.99");
        st.put("www.ibm.com",            "129.42.16.99");
        st.put("www.apple.com",          "17.254.0.91");
        st.put("www.slashdot.com",       "66.35.250.150");
        st.put("www.whitehouse.gov",     "204.153.49.136");
        st.put("www.espn.com",           "199.181.132.250");
        st.put("www.snopes.com",         "66.165.133.65");
        st.put("www.movies.com",         "199.181.132.250");
        st.put("www.cnn.com",            "64.236.16.20");
        st.put("www.iitb.ac.in",         "202.68.145.210");

        // search for IP addresses given URL
        logger.Log.info("size = " + st.size());
        logger.Log.info(st.get("www.cs.princeton.edu"));
        logger.Log.info(st.get("www.amazon.com"));
        logger.Log.info(st.get("www.amazon.edu"));
        logger.Log.info(st.get("www.yale.edu"));
        //logger.Log.info();

    }

}


public class SymbolTBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

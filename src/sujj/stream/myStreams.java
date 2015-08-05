package sujj.stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;


public class myStreams {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		FileOutputStream myErrfile = new FileOutputStream("c:\\err.log");
		PrintStream myErr = new PrintStream(myErrfile);
		System.setErr(myErr);
		
		
		
		throw new Exception();
	}

}

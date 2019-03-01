package sujj.date;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class testDate {

	public static String getCurrentDate()
	{
		
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss") ;
		String currentTime = dateFormat.format(date);
		return currentTime;
		
	}
	public static void testFile() throws IOException
	{
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		File file = new File(dateFormat.format(date) + ".tsv") ;
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write("Writing to file");
		out.close();
	}
	private static String datepattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		try  
		{  
			//get current datetime
			System.out.println(getCurrentDate());
			//8/1/2018
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  
		    Date date = sdf.parse("8/1/2018");  
		    System.out.println(date);
		    SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");    
		    System.out.println(outFormat.format(date));
		    
		    //
		    
		    System.out.println("date 0 is: "+new SimpleDateFormat(datepattern).format(new Date(0)));
		    System.out.println("date 0 is: "+new Date(0).toString());
		    System.out.println("date 0 is: "+new Date(0));
		    
		    
		    testFile();
		}  
		catch (ParseException e)  
		{  
		    System.out.println(e.getMessage());  
		}  
		
		
	}

}

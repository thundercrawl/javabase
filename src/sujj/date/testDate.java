package sujj.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testDate {

	
	private static String datepattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try  
		{  
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
		}  
		catch (ParseException e)  
		{  
		    System.out.println(e.getMessage());  
		}  
		
		
	}

}

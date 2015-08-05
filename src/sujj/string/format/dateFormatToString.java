package sujj.string.format;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.concord.automation.utils.tools.date.Datefuncs;

public class dateFormatToString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //format the date string format output
		
			SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	        SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm:ss"); 
	        SimpleDateFormat myFmt2 = new SimpleDateFormat("MM/yy mm:ss");
	        
	        
	        System.out.println(myFmt.format(new Date()));
	        System.out.println(myFmt1.format(new Date()));
	        System.out.println(myFmt2.format(new Date()));
	        
	        //format the string to the date format and then do the comparation.
	        
	        System.out.println(Datefuncs.stringToDate("12/09/14 17:14:43", "yy/MM/dd HH:mm:ss").toString());
	        //format the datetime from 1970 to ...
	        System.out.println(System.currentTimeMillis());
	        Date dt = new Date(1394521066800L);
	        System.out.println(dt.toGMTString());
	}

}

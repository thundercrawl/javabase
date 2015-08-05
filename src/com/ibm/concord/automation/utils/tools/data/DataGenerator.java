package com.ibm.concord.automation.utils.tools.data;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import com.ibm.concord.automation.utils.tools.GenerateRandom;

/**
 * Supports creation of unique and random strings and other data types
 * @author lnodwell
 * @since 2005-04-22
*/
public class DataGenerator extends GenerateRandom {
	private static DateFormat DF = DateFormat.getDateTimeInstance(
			DateFormat.MEDIUM, DateFormat.FULL, Locale.getDefault());

	
	/**
	 * Creates a unique Filename
	 * To make a valid file name you will need to insert a 
	 * letter at the beginning of the file name.
	 * This is useful for saving files that you do not need 
	 * to check in such as for a screen capture when an 
	 * unexpected error is encountered.
	 * @param 	none
	 * @return	String of integers to be used as a file name
	 * @author mquatrale
	 * @since 2005-06-28
	 */
	public static String uniqueFileName () {
		Long lDate = new Long (new Date ().getTime());
		//System.out.println ("long is " + lDate.toString());
		String s = lDate.toString ();
		//String sFileName;
		StringBuffer sBuffer = new StringBuffer (s);
		//System.out.println ("converted is " + sBuffer.toString ());
		return sBuffer.toString ();
	}

	/**
	 * Returns a random subject generated with the current date/time
	 * @return subject
	 */
	public static String randomSubject() {
		Calendar cal = Calendar.getInstance();
		return DF.format(cal.getTime());
	}
	

	/**
	 * Generates a random integer between 1 and the max number
	 * 
	 * @param max
	 * @return the random number
	 */
	public static int randomInt (int max) {
		Random generator = new Random();
		return generator.nextInt(max);
	}
	
	/**
	 * Generates a random integer between 1 and 5000
	 * 
	 * @return the random number
	 */
	public static int randomInt () {
		Random generator = new Random();
		return generator.nextInt(5000);
	}

	/**
	 * Generates a unique string every second (based upon current date and time value)
	 * @return String 
	 * @author lnodwell
	 * @since 2005-04-22 
	 */
   public static String uniqueString () {
      GregorianCalendar today = new GregorianCalendar ();
      Integer iYear = new Integer (today.get(GregorianCalendar.YEAR));
      Integer iMonth = new Integer (today.get(GregorianCalendar.MONTH));
      Integer iDay = new Integer (today.get(GregorianCalendar.DAY_OF_MONTH));
      Integer iHour = new Integer (today.get (GregorianCalendar.HOUR));
      Integer iMinute = new Integer (today.get (GregorianCalendar.MINUTE));
      Integer iSecond = new Integer (today.get (GregorianCalendar.SECOND));
      String s = iYear.toString () + iMonth.toString () + iDay.toString () + iHour.toString () + iMinute.toString () + iSecond.toString (); 
      return (convertToAlpha (s));
      
    
   }
   
   
   /**
    * Converts digits in a string to characters.  
    * @param s
    * @return the convert result
	* @author lnodwell
	* @since 2005-04-22
    */
   private static String convertToAlpha (String s) {
      StringBuffer sBuffer = new StringBuffer ();
      for (int i = 0; i < s.length(); i++) {
         switch (s.charAt(i)) {
            case '0':
               sBuffer.append('a');
               break;
            case '1':
               sBuffer.append('b');
               break;
            case '2':
               sBuffer.append('c');
               break;
            case '3':
               sBuffer.append('d');
               break;
            case '4':
               sBuffer.append('e');
               break;
            case '5':
               sBuffer.append('f');
               break;
            case '6':
               sBuffer.append('g');
               break;
            case '7':
               sBuffer.append('h');
               break;
            case '8':
               sBuffer.append('i');
               break;
            case '9':
               sBuffer.append('j');
               break;
         }
      }
      return sBuffer.toString ();
   }

}

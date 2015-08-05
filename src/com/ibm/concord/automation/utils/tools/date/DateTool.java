package com.ibm.concord.automation.utils.tools.date;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ibm.concord.automation.utils.tools.data.DataGenerator;

/**
 * Processes Date values to and from String formats
 * 
 * @author lmccollu
 * @since 2005-07-01
 */
public class DateTool {
	// TODO : copied from SilkGlobalVars
	// LJN : this needs to be incorporated into DateTool and
	// sysconfig/LocalSettings for date formats
	public static String STANDARDDATEFORMAT = "mm/dd/yyyy";
	public static String STANDARDTIMEFORMAT = "hh:mm AM/PM";
	public static String EXTENDEDDATEFORMAT = "ddd mm/dd/yyyy"; // display Notes
	// format with
	// addition of
	// day of week

	public static String EXTENDEDTIMEFORMAT = "hh:mm:00 AM/PM"; // internal
	// Notes format
	// requires
	// seconds which
	// are always 00
	public static String DATESEPARATOR = "/"; // date separator as defined in
	// Windows regional settings
	public static String TIMESEPARATOR = ":"; // time separator as defined in
	// Windows regional settings
	public static String FULLDATETIMEFORMAT = "MM/dd/yyyy hh:mm a z"; // added
	// by
	// BLe

	public static DateTool SHORT_DATE = new DateTool("MM/dd/yyyy");
	public static DateTool LONG_DATE = new DateTool("MMMM dd, yyyy"); // MMMM =
	// long
	// month
	// name
	public static DateTool EXTENDED_DATE = new DateTool("EE MM/dd/yyyy"); // EE
	// =
	// short
	// weekday
	// name
	public static DateTool OUTLOOK_EXTENDED_DATE = new DateTool("EE M/d/yyyy"); // Outlook
	// has
	// Mon
	// 3/3/2014
	// while
	// Notes
	// has
	// Mon
	// 03/03/2014
	public static DateTool SHORT_TIME = new DateTool("hh:mm");
	public static DateTool LONG_TIME = new DateTool("hh:mm a"); // a = am/pm
	// indicator
	public static DateTool OUTLOOK_LONG_TIME = new DateTool("h:mm a"); // Outlook
	// has
	// 5:02
	// AM
	// while
	// Notes
	// has
	// 05:02
	// AM
	public static DateTool EXTENDED_TIME = new DateTool("hh:mm:00 a"); // a =
	// am/pm
	// indicator
	// and
	// 00
	// indicate
	// seconds
	// are
	// always
	// zero
	public static DateTool FULL_EXTENDED_TIME = new DateTool("hh:mm:ss a"); // a
	// =
	// am/pm
	// indicator
	public static DateTool SHORT_DATE_SHORT_TIME = new DateTool(
			DateTool.SHORT_DATE.toString() + " "
					+ DateTool.SHORT_TIME.toString());
	public static DateTool EXTENDED_DATE_EXTENDED_TIME = new DateTool(
			DateTool.EXTENDED_DATE.toString() + " "
					+ DateTool.EXTENDED_TIME.toString());
	public static DateTool EXTENDED_DATE_FULL_EXTENDED_TIME = new DateTool(
			DateTool.EXTENDED_DATE.toString() + " "
					+ DateTool.FULL_EXTENDED_TIME.toString());
	public static DateTool FULL_DATE_TIME = new DateTool(FULLDATETIMEFORMAT); // added
	// by
	// BLe
	public static String sTimeZone;
	// private static final String sAM = "AM";
	// private static final String sPM = "PM";

	public static String sRandDateValueFormat = "MMddHHmmss";
	/**
	 * Storage for local formats used by Notes <code>sFormat</code>
	 */
	public String sFormat;

	/**
	 * Constructor for date and time format strings Note these strings should be
	 * resourced for locales
	 * 
	 * @param String
	 *            - format string
	 * @author lnodwell
	 * @since 2005-10-14
	 */
	public DateTool(String format) {
		sFormat = new String(format);
	}

	/**
	 * Generates String containing unique random date-based value. Copied from
	 * the VisualReporter class.
	 * 
	 * @return a String containing a date-based unique value
	 * @author lmccollu
	 * @since 2005-07-01
	 */
	public static String genDateBasedRandVal() {
		// generates String containing unique random date-based value
		String s;
		Date d = new Date();
		// long l;

		// format
		SimpleDateFormat tmformat = new SimpleDateFormat(sRandDateValueFormat);

		// l = d.getTime();
		s = tmformat.format(d);

		return s;
	}

	/**
	 * Generates String containing unique random date-based value. million
	 * second based
	 * 
	 * @return a String containing a date-based unique value
	 * @author chenhaiq
	 * @since 2008-01-08
	 */
	public static String genDateBasedRandValLong() {
		// generates String containing unique random date-based value
		String s;
		Date d = new Date();
		// long l;

		// format
		SimpleDateFormat tmformat = new SimpleDateFormat("MMddHHmmssSSS");

		// l = d.getTime();
		s = tmformat.format(d);

		return s;
	}

	/**
	 * Generates a random GregorianCalendar-based value
	 * 
	 * @param int Minimum value added to YEAR
	 * @param int Maximum value added to YEAR
	 * @param [OPTIONAL] GregorianCalendar containing initial date/time value --
	 *        otherwise initialzed with current date/time.
	 * @returns a GregorianCalendar containing a random value
	 * @author apaci
	 * @since 2007-04-17
	 */
	public static GregorianCalendar genRandomDateTimeValue() {
		// Calls genRandomDateTimeValue to generate and returns a random
		// GregorianCalendar value
		GregorianCalendar gc = new GregorianCalendar();
		return genRandomDateTimeValue(0, 0, gc);
	}

	public static GregorianCalendar genRandomDateTimeValue(int iMinYears,
			int iMaxYears) {
		// Calls genRandomDateTimeValue to generate and returns a random
		// GregorianCalendar value
		GregorianCalendar gc = new GregorianCalendar();
		return genRandomDateTimeValue(iMinYears, iMaxYears, gc);
	}

	public static GregorianCalendar genRandomDateTimeValue(int iMinYears,
			int iMaxYears, GregorianCalendar gcDateTime) {
		// Generates and returns a random GregorianCalendar value

		int iMin = iMinYears;
		int iMax = iMaxYears;
		GregorianCalendar gc;
		gc = gcDateTime;

		gc.add(Calendar.YEAR, new Integer(DataGenerator
				.generateRandomIntAsString(iMin, iMax)).intValue());
		gc.add(Calendar.MONTH, new Integer(DataGenerator
				.generateRandomIntAsString(gc.getMinimum(Calendar.MONTH), gc
						.getMaximum(Calendar.MONTH))).intValue());
		gc
				.add(Calendar.DAY_OF_MONTH, new Integer(DataGenerator
						.generateRandomIntAsString(gc
								.getMinimum(Calendar.DAY_OF_MONTH), gc
								.getMaximum(Calendar.DAY_OF_MONTH))).intValue());
		gc.add(Calendar.HOUR_OF_DAY, new Integer(DataGenerator
				.generateRandomIntAsString(gc.getMinimum(Calendar.HOUR_OF_DAY),
						gc.getMaximum(Calendar.HOUR_OF_DAY))).intValue());
		gc.add(Calendar.MINUTE, new Integer(DataGenerator
				.generateRandomIntAsString(gc.getMinimum(Calendar.MINUTE), gc
						.getMaximum(Calendar.MINUTE))).intValue());
		return gc;

	}

	/**
	 * Returns formatted String version of date supplied as long. Copied from
	 * the VisualReporter class but removed offset calculation for GMT vs. EST.
	 * 
	 * @param dDateTime
	 *            Date and time in long data type
	 * @param sDateTimeFormat
	 *            The string format you would like the date and time to be
	 *            displayed as. (i.e. "HH:mm:ss:SSS")
	 * 
	 * @return String containing the formatted date time of the given long
	 *         datetime.
	 * @author lmccollu
	 * @since 2005-07-01
	 */
	public static String getFormattedDateTime(long dDateTime,
			String sDateTimeFormat) {
		SimpleDateFormat tmformat = new SimpleDateFormat(sDateTimeFormat);
		Date tm = new Date(dDateTime);
		return tmformat.format(tm);
	}

	/**
	 * @param date
	 * @param sDateTimeFormat
	 * @return
	 */
	public static String getFormattedDateTime(GregorianCalendar date,
			String sDateTimeFormat) {
		// Logger.logDebug("getFormattedDateTime: format string: [" +
		// sDateTimeFormat + "]");
		// Logger.logDebug("date object: " + date);

		SimpleDateFormat tmformat = new SimpleDateFormat(sDateTimeFormat);
		return tmformat.format(new Date(date.getTimeInMillis()));
	}

	/**
	 * Converts a string and the pattern of the string into a date
	 * 
	 * @param sDate
	 *            String representing date
	 * @param sDateFormat
	 *            date format of given string
	 * @return Date
	 * @author lmccollu
	 * @since 2005-08-11
	 */
	public static Date convertStringToDate(String sDate, String sDateFormat) {
		SimpleDateFormat formatter;

		formatter = new SimpleDateFormat(sDateFormat);
		ParsePosition p = new ParsePosition(0);
		Date result = formatter.parse(sDate, p);

		return result;
	}

	/**
	 * Parses string for date and time according to pattern and returns calendar
	 * representation of the date and time
	 * 
	 * @param sTimeStamp
	 *            : the time to parse
	 * @param sTimePattern
	 *            : the pattern with which to parse the timestamp e.g.
	 *            "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static Calendar parseTimeStamp(String sTimeStamp, String sTimePattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(sTimePattern);
		try {
			Date d = sdf.parse(sTimeStamp);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			return cal;
		} catch (ParseException e) {
			// Logger.logError("ParseTimeStamp got ParseException "+e);
			return null;
		}
	}
}

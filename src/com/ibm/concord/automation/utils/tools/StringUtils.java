/*
 * (C) Copyright International Business Machines Corp., 2005
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 */
package com.ibm.concord.automation.utils.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;


/**
 * Script Name   : <b>StringUtils</b>
 * Generated     : <b>Aug 21, 2005 1:11:20 PM</b>
 * Description   : Support utilties for string manipulation.
 * Original Host : WinNT Version 5.1  Build 2600 (S)
 * 
 * @since  2005/08/21
 * @author mnowacki, dkulju
 */
public class StringUtils {
    
    private static final String OPENTAG = "<"; /* NOI18N */
    private static final String CLOSETAG = ">"; /* NOI18N */
    private static final String COMMA = ","; /* NOI18N */
    private static final String EMPTYSTRING = ""; /* NOI18N */
    private static final String OPENPAREN = "("; /* NOI18N */
    private static final String CLOSEPAREN = ")"; /* NOI18N */

    /**
     * Default constructor for utility class.
     */
    public StringUtils() {}
 
    /**
	 * Converts a specified string to a string array using specified delimiter<p>
	 * @param 			String s - string to convert to string array
	 * @return			String[] - the string array (i.e. string "one, two, three, four," is returned as String [] = {"one","two","three","four"}
	 */
	public static String[] stringToStringArray(String s, String sDelim)
	{
		return s.split(sDelim);

//		int index = st.countTokens();
//
//		String[] lines = new String[index];
//
//		for (int i = 0; i < index; i++)
//		{
//			lines[i] = st.nextToken().trim();
//		}
//
//		return lines;
	}
    
    
    /**
     * Validates none of the objects passed to it are null
     * Prevents swallowing null parameter exceptions which will make debugging difficult
     * Will verify items in an array, but only one level deep (not arrays of arrays)
     * @param name  args
     * @throws IllegalArgumentException
     * 
     */    
    public static void checkNullArgs(Object[] args){
        // check parameters
    	for (int i = 0; i < args.length; i++){
    		if (args[i] == null){
                if (args instanceof String[]) {
                    System.out.println(arrayToString((String[])args));
                }
    			throw new IllegalArgumentException("StringUtils::checkNullArgs: Null parameter at location " + i);	 /* NOI18N */
    		}
//NOOOO - items in an array may be null without causing problems - lists
// of menu items sometimes contain a null as a separator and that is just fine
// so comenting out the array checking for the time being
//    		// check to see if the current arg is an array
//    		if (args[i].getClass().isArray()){ 
//    			Object [] args2 = (Object [])args[i];
//    			// if the argument is an array verify the items within
//    			for (int j = 0; j < args2.length; j++){
//    				if (args2[j] == null){
//    					throw new IllegalArgumentException("Null parameter");
//    				}
//    			}
//    		}
    	}
    }
    
    /**
     * Determine if a string exists in an array of strings.
     * @param name  String to find.
     * @param nameArray Array of strings.
     * @return boolean True: string found in array, false: string not found.
     */
    public static boolean findStringInArray(String name, String[] nameArray) {
    	checkNullArgs (new Object[] {name, nameArray});
        boolean bRetVal = false;

    	// loop through array
        for (int i = 0; i < nameArray.length; i++) {
//            if (nameArray[i].equalsIgnoreCase(name)) {
            // must use this order b/c causes NPEx if array item is null
        	if (name.equalsIgnoreCase(nameArray[i])) {
                bRetVal = true;
                break;
        	}
        }
        return bRetVal;
    }
    
    /**
     * Determine if a string exists in an array of strings.
     * @param name  String to find.
     * @param nameArray Array of strings.
     * @return boolean True: string found in array, false: string not found.
     */
    public static boolean findLikeStringInArray (String name, String[] nameArray) {
    	checkNullArgs (new Object[] {name, nameArray});
        boolean bRetVal = false;
        
    	// loop through array
        for (int i = 0; i < nameArray.length; i++) {
        	if (nameArray[i]!= null && !nameArray[i].equals("") && (subStrExists (nameArray[i], name) || subStrExists (name, nameArray[i]))) {
               bRetVal = true;
                break;
        	}
        }
        return bRetVal;
    }
    
    
    
    /**
     * Get position of string in an array of strings.  Returns first match found.
     * @param name  String to find.
     * @param nameArray Array of strings.
     * @return int returns the place in the array or -1 if not in array 
     */
    public static int getPositionInArray(String name, String[] nameArray) {
    	checkNullArgs (new Object [] {name, nameArray});
    	int position = -1; 
    	if (findStringInArray(name, nameArray)){
            for (int i = 0; i < nameArray.length; i++) {
            	if (nameArray[i].equalsIgnoreCase(name)) {
            		position = i;
                    break; // end search at first item
            	}
            }    		
    	}
    	return position;
    }

    /**
     * Replace a string with a different string inside a larger string.
     * @param inStr String in which to search
     * @param searchStr Substring to find
     * @param replaceStr Replacement string
     * @return String String with replacement made
     */
    public static String strTrans(String inStr, String searchStr, String replaceStr) {
    	checkNullArgs (new Object [] {inStr, searchStr, replaceStr});       

        String outString = inStr;
        String tempStr1;
        String tempStr2;
        while (outString.indexOf(searchStr) >= 0) {
        	tempStr1 = outString.substring(0, outString.indexOf(searchStr));
        	tempStr2 = outString.substring(outString.indexOf(searchStr) + searchStr.length(), outString.length());
        	outString = tempStr1 + replaceStr + tempStr2;
        }
        return outString;
    }
    
    
    /**
     * Returns a string starting at the first character and ending at the endstring (inclusive)
     * If the endStr is not found in inStr then returns inStr unaltered 
     * @param inStr - String in which to search
     * @param endStr - last character to be returned
     * @return
     */
    public static String beginString (String inStr, String endStr){
    	if (inStr.indexOf(endStr) >= 0){
    		return inStr.substring(0, inStr.indexOf(endStr));
    	}else{
    		return inStr;
    	}
    }
    	
        /**
         * Returns a string starting at beginStr and going to the end (inclusive)
         * If the beginStr is not found in inStr then returns inStr unaltered 
         * @param inStr - String in which to search
         * @param endStr - last character to be returned
         * @return
         */
        public static String endString (String inStr, String beginStr){
        	if (inStr.indexOf(beginStr) >= 0){
        		return inStr.substring(inStr.indexOf(beginStr), inStr.length());
        	}else{
        		return inStr;
        	}
        }

    /**
     * Calls a strTrans of each string in an array.
     * @param inStr Array of strings in which to search
     * @param searchStr Substring to find 
     * @param replaceStr Replacement string
     * @return String[] Array of strings with replacements made
     */
    public static String[] strTrans(String inStr[], String searchStr, String replaceStr) {
    	checkNullArgs (new Object [] {inStr, searchStr, replaceStr});

        String[] outString = inStr;
        for (int i = 0; i < outString.length; i++) {
                outString[i] = strTrans(outString[i], searchStr, replaceStr);
            }
        return outString;
    }

    /**
     * Returns the number of times a string exists inside another string.
     * @param inStr  String to search in
     * @param searchStr String to search for
     * @return int Number of instances found
     */
    public static int subStrCount(String inStr, String searchStr) {
    	checkNullArgs (new Object [] {inStr, searchStr});
    	int i = 0;
		String tempStr1, tempStr2;

		while (inStr.indexOf(searchStr) >= 0) {
			tempStr1 = inStr.substring(0, inStr.indexOf(searchStr));
			tempStr2 = inStr.substring(inStr.indexOf(searchStr) + 1, inStr.length());
			inStr = tempStr1 + tempStr2;
			i++;
		}
		return i;
    }
    
    /**
     * Returns the number of times a string exists inside another string.
     * @param inStr  String to search in
     * @param searchStr String to search for
     * @return int Number of instances found
     */
    public static boolean subStrExists (String inStr, String searchStr){
    	if (inStr.indexOf(searchStr) != -1){
    		return true;
    	}else{
    		return false;
    	}
    }  

    /**
     * Removes all HTML tags from a given string.
     * @param inStr String to check.
     * @return String inStr after manipulation.
     */
    public static String stripHtml(String inStr) {
    	checkNullArgs (new Object [] {inStr});
    	String outString = inStr;

        String killStr;
        final int x = 0;
        int pos1;
        int pos2;
        while (subStrCount(outString, CLOSETAG) > 0) {
                pos1 = outString.indexOf(OPENTAG);
                pos2 = outString.indexOf(CLOSETAG);
                killStr = outString.substring(outString.indexOf(OPENTAG, pos1 + x),	outString.indexOf(CLOSETAG, pos2 + x) + 1);
                outString = strTrans(outString, killStr, EMPTYSTRING);
            }
            outString = strTrans(outString, "&lt;", OPENTAG); /* NOI18N */
            outString = strTrans(outString, "&gt;", CLOSETAG); /* NOI18N */
        
        return outString;

    } // end stripHtml
    
    /**
     * Removes all HTML tags from a given string, but leaves addresses formated like <B>"Blah Blah" <thisaddress@here.com></B> intact.
     * This method is unnecessary, email addresses don't use angle brackets they use &lt and &gt instead
     * changing to simply call the old version in case someone is calling this method
     * 
     * @param inStr  String to check.
     * @return String inStr after manipulation.
     */
    public static String stripHtmlSaveEmails(String inStr) {
        return stripHtml(inStr);

    } // end stripHtml
    
    
    /**
     * Removes the three periods (ellipses) after menu items.
     * Useful when comparing popup menu getContents and action bar contents
     * against actual values when one lists contains the 3 periods and
     * ther other may not.  If the input does not have ellipses then the
     * input string is simply returned and no action is taken.
     * Author: lnodwell
     * @since 2008-05-30
     * @param sInput String to process
     * @return String with ellipses removed
     */
    public static String removeEllipses (String sInput) {
    	return (sInput.endsWith("...")) ? (sInput.substring(0, sInput.length() - 3)) : sInput;
    }
     

    /**
     * Remove trailing <B>(#)</B> strings from parameter.  Used mainly for WMC folder names, which indicate unread with strings like this.
     * Also useful for WMC Address Book group names, which indicate number of members this way.
     * @param inStr String to examine.
     * @return String inStr without trailing number in parenthesis.
     */
    public static String stripCount(String inStr) {
    	checkNullArgs (new Object [] {inStr});
        String outString = inStr;

         if (outString.indexOf(OPENPAREN) > 0) {
             String killStr = outString.substring(outString.indexOf(OPENPAREN) - 1, outString.indexOf(CLOSEPAREN) + 1);
             outString = strTrans(outString, killStr, EMPTYSTRING).trim();
            }
        return outString;

    }
    
    /**
     * Returns trailing <B>(#)</B> strings from parameter.  Used mainly for WMC folder names, which indicate unread with strings like this.
     * Also useful for WMC Address Book group names, which indicate number of members this way.
     * @param inStr String to examine.
     * @return int - folder unread count
     */
    public static int getCount(String inStr) {
    	checkNullArgs (new Object [] {inStr});
        
         if (inStr.indexOf(OPENPAREN) > 0) {
             return Integer.parseInt(inStr.substring(inStr.indexOf(OPENPAREN) + 1, inStr.indexOf(CLOSEPAREN)));
         }else{
        	 return 0;
         }
    }
	/**
	 * Return the string between two indicator strings.
	 * @param inStr
	 * @param strOne
	 * @param strTwo
	 * @return String
	 */
	public static String strBetween(String inStr, String strOne, String strTwo) {
		checkNullArgs (new Object [] {inStr, strOne, strTwo});
		String subStr;
		inStr = inStr.substring(inStr.indexOf(strOne), inStr.length());
		subStr = inStr.substring(inStr.indexOf(strOne),	inStr.indexOf(strTwo) + strTwo.length());
		return subStr;

	} // end strBetween

    /**
     * Convert a comma delimited string to a string array.
     * @param inStr Comma-delimited string.
     * @return String[] Array of strings from parameter.
     */
    public static String[] stringToArray(String inStr) {
    	checkNullArgs (new Object [] {inStr});
    	String tempString = inStr;
        String[] outString = null;
        
        int stringCount = subStrCount(inStr, COMMA);
        outString = new String [stringCount+1];
            
        for (int i = 0; i < stringCount; i++) {
        	outString[i] = tempString.substring(0, tempString.indexOf (COMMA));
        	tempString = tempString.substring(tempString.indexOf(COMMA)+2, tempString.length());
        }

        //last string is just the remaining inStr
        outString[stringCount] = tempString;
        return outString;
    }
    

    /**
     * Converts a string array to a comma delimited string.
     * @param inArray Array of strings.
     * @return String Comma-delimited string of strings.
     */
    public static String arrayToString(String[] inArray) {
    	checkNullArgs (new Object [] {inArray});
    	String outString = null;
    	for (int i = 0; i < inArray.length; i++) {
    		if (i == 0) {
    			outString = inArray[i];
    		}else{
    			outString = outString + ", " + inArray[i]; /* NOI18N */
    		}
    	}
        return outString;
    }
    
    
    /**
     * Returns an array sorted ascending alphabetically - not case sensitive
     * @param inArray
     * @return sorted array
     */
    public static String[] sortStringArray (String[] inArray){
    	Arrays.sort (inArray, String.CASE_INSENSITIVE_ORDER);
    	return inArray;
    }
    
    /**
     * Capitalize first letter of provided string.
     * @param string String to capitalize.
     * @return Capitalized string
     */
    public static String capitalize(String string) {
    	checkNullArgs (new Object [] {string});
        return string.replace( string.charAt(0), string.toUpperCase().charAt(0) );
    }

    /**
     * Gets the unread count indicator from a given folder's name.
     * 
     * @param inStr Folder name string with unread count in parentheses.
     * @return String containing the integer from within the parentheses.
     */
    public static String getUnread(String inStr) {
        checkNullArgs (new Object [] {inStr});
        String getStr = EMPTYSTRING;
        if (inStr.indexOf(OPENPAREN) > 0) {
            getStr = inStr.substring(inStr.indexOf(OPENPAREN) + 1, inStr.indexOf(CLOSEPAREN)).trim();
        }
        return getStr;
    }
    
    /**
     * Wraps brackets around any special characters contained in inStr
     * including "(", ")", +
     * @param inStr
     * @return
     */
    public static String wrapSpecial (String inStr){
    	String outStr = inStr;
    	
    	outStr = strTrans(outStr, "(","{x}");
    	outStr = strTrans (outStr, "{x}", "{(}");
    	outStr = strTrans(outStr, ")","{x}");
    	outStr = strTrans (outStr, "{x}", "{)}");
    	

    	
    	return outStr;
    	
    }

    /**
     * Removes any embedded ampersand (&) characters and trims 
     * whitespace from string.
     * 
     * @param inStr String to clean.
     * @return String containing no ampersands or leading/trailing whitespace.
     */
    public static String cleanMenu(String inStr) {
        checkNullArgs (new Object [] {inStr});
        String temp = inStr;
        while (0 <= temp.indexOf("&")) {
            temp = temp.replaceAll("\\&", EMPTYSTRING);
        }
        while (0 <= temp.indexOf("\t")) {
            temp = temp.substring(0, temp.indexOf("\t"));
        }
        return temp.trim();
    }
    
    /**
     * Strips the index value from a string - useful with radio lists
     * 
     * @param inStr String to clean.
     * @return String containing no index numbers or pipes
     */
    public static String stripIndex(String inStr) {
        checkNullArgs (new Object [] {inStr});
               
        String temp = beginString (inStr, "|");
        
        temp = strTrans (temp, "|", "");

        return temp.trim();
    }
    
    /**
     * Unit testing area.  Hard hats required.
     * 
     * @param args
     */
    public static void main(String[] args) {
        //*
        String s;
        s = "wct917";
        // should display Wct617
        System.out.println("capitalize: [" + capitalize(s) + "]");
        
        s = "Inbox (88)";
        // should display Inbox
        System.out.println("stripCount: [" + stripCount(s) + "]");
        // should display 88
        System.out.println("getUnread: [" + getUnread(s) + "]");
        
        s = "<B>Bold</B> One and <FONT>FONT=Arial</FONT>";
        // should display B
        System.out.println("strBetween: [" + strBetween(s, "<", ">")+ "]");
        s = "<B>Bold</B> One and <FONT>FONT=Arial</FONT>";
        // should display Bold One and FONT=Arial 
        System.out.println("stripHtml: [" + stripHtml(s) + "]");
        
        s = "<B>Bold</B> One, \"user Wct917\" <userwct917@wct9.notesdev.ibm.com> and <FONT>Font=Arial</FONT>";
        // should display Bold One, "user Wct917" <userwct917@wct9.notesdev.ibm.com> and Font=Arial
        System.out.println("stripHtmlSaveEmails: [" + stripHtmlSaveEmails(s) + "]");

        s = "this is what this is what";
        // should display 4
        System.out.println("subStrCount: [" + subStrCount(s, "is") + "]");
        
        System.out.println("strTrans(array) is->was:");
        String[] sa = {s, "is", "the", "string", "that", "is"};
        String[] sout = strTrans(sa, "is", "was");
        // Results should be:
        // 0 [thwas was what thwas was what]
        // 1 [was]
        // 2 [the]
        // 3 [string]
        // 4 [that]
        // 5 [was]
         for (int i = 0; i < sout.length; i++) {
            System.out.println(i + " [" + sout[i] + "]");
        }
         
        // should display this was what this was what
        System.out.println("strTrans is->was: [" + strTrans(s, " is ", " was ") + "]");
        
        s = "this, is, or, maybe, was";
        System.out.println("stringToArray:");
        sa = stringToArray(s);
        // Results should be:
        // 0 [this]
        // 1 [is]
        // 2 [or]
        // 3 [maybe]
        // 4 [was]
        for (int i = 0; i < sa.length; i++) {
            System.out.println(i + " [" + sa[i] + "]");
        }
        // should display true
        System.out.println("findStringInArray: [" + findStringInArray("maybe", sa) + "]");
       
        // should display this, is, or, maybe, was
        System.out.println("arrayToString: [" + arrayToString(sa) + "]");
        
        // should display File
        System.out.println("clean: [" +  cleanMenu("&File ") + "]");
        // should display Window
        System.out.println("clean: [" +  cleanMenu(" Win&dow    ") + "]");
        // should display Close
        System.out.println("clean: [" +  cleanMenu("Close" + "\t" + "Esc") + "]");
        
        //*/
    }
    
    /**
     * generate string from template and hashmap
     * 
     * 
     * @param template: define each key in ${key}
     * @param table: key: ${key} in tempalte, value: the value to replace
     * @return
     */
	public static String substituteTemplate(String template, Map<String, String> table) {
		StringBuffer key = new StringBuffer();
		StringBuffer out = new StringBuffer();
		int ch;
		try {
			InputStream ins = new FileInputStream(new File(template));
			if (ins == null) {
				throw new RuntimeException("can not find template \"" + template + "\"");
			}
			while ((ch = ins.read()) != -1) {
				if (ch == '$') {
					ch = ins.read();
					if (ch == '{') {
						//clear name buffer
						key.delete(0, key.length());
						ch = ins.read();
						while (ch != '}' && ch != -1) {
							key.append((char)ch);
							ch = ins.read();
						}
						String value = table.get(key.toString());
						if ( value != null) {
							out.append(value);
						} 
						//continue to next loop
						continue;
					} else {
						out.append("$");
					}
				}
				out.append((char)ch);
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	
		return out.toString();
	}

}
// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package com.ibm.concord.automation.utils.tools;

/**
 * Description: Generates random strings and numbers
 * 
  * @author - James L Jones
 * 
 * @version 2.3
 * Last Modified: 08/05/04
 */
public class GenerateRandom {

	private static String[] charset =
		{
			"0",
			"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7",
			"8",
			"9",
			"a",
			"b",
			"c",
			"d",
			"e",
			"f",
			"g",
			"h",
			"i",
			"j",
			"k",
			"l",
			"m",
			"n",
			"o",
			"p",
			"q",
			"r",
			"s",
			"t",
			"u",
			"v",
			"w",
			"x",
			"y",
			"z",
			"A",
			"B",
			"C",
			"D",
			"E",
			"F",
			"G",
			"H",
			"I",
			"J",
			"K",
			"L",
			"M",
			"N",
			"O",
			"P",
			"Q",
			"R",
			"S",
			"T",
			"U",
			"V",
			"W",
			"X",
			"Y",
			"Z" };

	/**
	 * Generates a random alphanumeric string of the size specified excluding any characters in the exclude string
	 * @param length  			The length of the string that will be returned
	 * @param exclude			A case-sensitive String of characters to exclude eg("Jimjones")
	 * @return String			A random String of size length excluding any specified chars
	 * @author jamesjon
	 */
	public static String genAlphaNumericExcluding(int length, String exclude) {

		StringBuffer sb = new StringBuffer();
		boolean add = true;
		for (int n = 0; n < length; n++) {
			String next = charset[(int) (Math.random() * 62)];
			if (exclude.length() > 0) {

				for (int j = 0; j < exclude.length() && add; j++) {
					if (next.equals(exclude.substring(j, j + 1))) {
						//System.out.println(next + "=" + exclude.substring(j, j + 1) + " on element " + n);
						add = false;
						n--;
					} else
						add = true;

				}
				if (add)
					sb = sb.append(next);
				add = true;
			} else
				sb = sb.append(next);
		}
		return sb.toString();
	}

	/**
	 * Generates a random alphanumeric string of some length between the min and max lengths specified
	 * @param minimumLength		The minimum length of the string that will be returned
	 * @param maximumLength 	The maximum length of the string that will be returned
	 * @return String			A random String of length no less than min and no more than max
	 * @author jamesjon
	 */
	public static String genAlphaNumeric(int miniumLength, int maximumLength) {
		int rndSize = generateRandomInt(miniumLength, maximumLength);
		return genAlphaNumericExcluding(rndSize, "");
	}

	/**
	* Generates a random alpha string of some length between the min and max lengths specified
	* @param minimumLength		The minimum length of the string that will be returned
	* @param maximumLength 	The maximum length of the string that will be returned
	* @return String			A random String of length no less than min and no more than max
	* @author jamesjon
	*/
	public static String genAlpha(int miniumLength, int maximumLength) {
		int rndSize = generateRandomInt(miniumLength, maximumLength);
		return genAlphaNumericExcluding(rndSize, "1234567890");
	}

	/**
	* Generates a random alphanumeric string of some length between the min and max lengths specified excluding any characters in the exclude string
	* @param minimumLength		The minimum length of the string that will be returned
	* @param maximumLength 	The maximum length of the string that will be returned
	* @param exclude			A case-sensitive String of characters to exclude eg("Jimjones")
	* @return String			A random String of length no less than min and no more than max excluding any specified chars
	* @author jamesjon
	*/
	public static String genAlphaNumericExcluding(int miniumLength, int maximumLength, String exclude) {
		int rndSize = generateRandomInt(miniumLength, maximumLength);
		return genAlphaNumericExcluding(rndSize, exclude);
	}

	/**
	* Generates a random alpha string of some length between the min and max lengths specified excluding any characters in the exclude string
	* @param minimumLength		The minimum length of the string that will be returned
	* @param maximumLength 	The maximum length of the string that will be returned
	* @param exclude			A case-sensitive String of characters to exclude eg("Jimjones")
	* @return String			A random String of length no less than min and no more than max excluding any specified chars
	* @author jamesjon
	*/
	public static String genAlphaExcluding(int miniumLength, int maximumLength, String exclude) {
		int rndSize = generateRandomInt(miniumLength, maximumLength);
		return genAlphaNumericExcluding(rndSize, exclude.concat("1234567890"));
	}
	/**
	* Generates a random numeric string of some length between the min and max lengths specified excluding any characters in the exclude string
	* @param minimumLength		The minimum length of the string that will be returned
	* @param maximumLength 	The maximum length of the string that will be returned
	* @return String			A random String of length no less than min and no more than max excluding any specified chars
	* @author jamesjon
	*/
	public static String genNumeric(int miniumLength, int maximumLength) {
		int rndSize = generateRandomInt(miniumLength, maximumLength);
		return genAlphaNumericExcluding(rndSize, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
	/**
	* Generates a random numeric string of some length between the min and max lengths specified excluding any characters in the exclude string
	* @param minimumLength		The minimum length of the string that will be returned
	* @param maximumLength 	The maximum length of the string that will be returned
	* @param exclude			A case-sensitive String of characters to exclude eg("Jimjones")
	* @return String			A random String of length no less than min and no more than max excluding any specified chars
	* @author jamesjon
	*/
	public static String genNumericExcluding(int miniumLength, int maximumLength, String exclude) {
		int rndSize = generateRandomInt(miniumLength, maximumLength);
		return genAlphaNumericExcluding(rndSize, exclude.concat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
	}

	/**
	* Generates a random integer between 0 and maximumNumber
	* @param maximumNumber				The highest number that will be returned
	* @return int						A random number between 0 and maximumNumber	
	* @author jamesjon
	*/

	public static int generateRandomInt(int maximumNumber) {
		return (int) Math.round((Math.random()) * maximumNumber);
	}

	/**
	* Generates a random integer between minimumNumber and maximumNumber
	* @param minimumNumber				The lowest number that will be returned
	* @param maximumNumber				The highest number that will be returned
	* @return int						A random number between minimumNumber and maximumNumber	
	* @author jamesjon
	*/
	public static int generateRandomInt(int minimumNumber, int maximumNumber) {
		int num = generateRandomInt(maximumNumber);
		int count = 0;
		while (num < minimumNumber && count < 2) {
			num = generateRandomInt(minimumNumber, maximumNumber);
			count++;
		}
		return num;
	}
	/**
	* Generates a random integer as a String between 0 and maximumNumber
	* @param maximumNumber				The highest number that will be returned
	* @return String					A random number between minimumNumber and maximumNumber	
	* @author jamesjon
	*/
	public static String generateRandomIntAsString(int maximumNumber) {
		return new Integer(generateRandomInt(maximumNumber)).toString();
	}

	/**
	* Generates a random integer as a String between minimumNumber and maximumNumber
	* @param minimumNumber				The lowest number that will be returned
	* @param maximumNumber				The highest number that will be returned
	* @return String					A random number between minimumNumber and maximumNumber	
	* @author jamesjon
	*/
	public static String generateRandomIntAsString(int minimumNumber, int maximumNumber) {
		return new Integer(generateRandomInt(minimumNumber, maximumNumber)).toString();
	}

	public static void example() {
		String toExclude = "Jimjones";
		String numExclude = "1357";
		System.out.println("genAlpha(10, 20)\t\t\t\t\t\t" + genAlpha(10, 20));
		System.out.println("genAlphaExcluding(10, 20, \"" + toExclude + ")\t" + genAlphaExcluding(10, 20, toExclude) + "\n");

		System.out.println("genAlphaNumeric(10, 20)\t\t\t\t\t" + genAlphaNumeric(10, 20));
		System.out.println("genAlphaNumericExcluding(10, \"" + toExclude + ")\t" + genAlphaNumericExcluding(10, 20, toExclude) + "\n");

		System.out.println("genNumeric(10, 20)\t\t\t\t\t\t" + genNumeric(10, 20));
		System.out.println("genNumericExcluding(10, \"" + numExclude + ")\t\t\t" + genNumericExcluding(10, 20, numExclude) + "\n");

		System.out.println("generateRandomInt(20)\t\t\t\t\t" + generateRandomInt(20));
		System.out.println("generateRandomInt(100, 200)\t\t\t\t" + generateRandomInt(100, 200) + "\n");
	}

}

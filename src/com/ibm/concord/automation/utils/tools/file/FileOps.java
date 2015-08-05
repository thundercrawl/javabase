// IBM Confidential
//
// Source Materials
//
// (c) Copyright IBM Corp 2004
// The source code for this program is not published or otherwise
// divested of its trade secrets, irrespective of what has 
// been deposited with the U.S. Copyright Office.

package com.ibm.concord.automation.utils.tools.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

/**
 * Description: Performs file operations
 * 
 * @author Tony Venditti
 * 
 * @version 2.3 Last Modified: 04/06/04
 */
public class FileOps {

	// ******************************************************************************************************
	/**
	 * Reads specified file contents and returns file contents as a string
	 * <p>
	 * 
	 * @param filename
	 *            Path and filename of file to read
	 * @return String of specified file contents
	 * @author - James L Jones
	 */
	// *******************************************************************************************************
	public static String readFile(String source) {
		String file = "";
		try {
			FileInputStream fstream = new FileInputStream(source);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					fstream));
			String line;

			while ((line = in.readLine()) != null) {
				file = file + line + "\n";
			}
			in.close();
		} catch (IOException e) {
			System.err.println("Error in FileOps#readFile: " + e.getMessage());

			// PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY,
			// "Error in FileOps#readFile: " + e.getMessage());
		}
		return file;
	}

	// ******************************************************************************************************
	/**
	 * Reads specified file contents and returns file contents as a string
	 * <p>
	 * 
	 * @param filename
	 *            Path and filename of file to read
	 * @return String of specified file contents
	 * @author Tony Venditti
	 */
	// *******************************************************************************************************
	public static String getFileContents(String filename) {

		try {
			File file = new File(filename);
			FileReader in = new FileReader(file);
			char c[] = new char[(int) file.length()];
			in.read(c);
			String fileContentsString = new String(c);
			in.close();
			return fileContentsString;
		} catch (IOException e) {
			System.err.println("Error in FileOps#getFileContents: "
					+ e.getMessage());
			//
			// PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY,
			// "Error in FileOps#getFileContents: " + e.getMessage());
			return "";
		}
	}

	// ********************************************************************************************************
	/**
	 * Returns file content as a String Array
	 * 
	 * @param filename
	 *            Path and filename of file to read
	 * @return file content as a String Array
	 * @author Tony Venditti
	 */
	// *******************************************************************************************************
	public static String[] getFileContentsAsList(String filename) {
		int z = 0;
		int n = getNumberOfLinesInFile(filename);
		String lsLines[] = new String[n];

		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = in.readLine()) != null) {
				if (z >= n)
					break;
				// webLogInfo(z + " " + line, DEBUG);
				lsLines[z] = line;
				z++;
			}
		} catch (IOException e) {
			System.err.println("Error in FileOps#getFileContentsAsList: "
					+ e.getMessage());
			// PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY,
			// "Error in FileOps#getFileContentsAsList: " + e.getMessage());
		}
		return lsLines;
	}

	// ******************************************************************************************************
	/**
	 * Returns number of lines in a specified file
	 * <p>
	 * 
	 * @param filename
	 *            Path and filename of file to read
	 * @return number of lines in specified file
	 * @author Tony Venditti
	 */
	// ******************************************************************************************************
	public static int getNumberOfLinesInFile(String filename) {
		int i = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while (in.readLine() != null)
				i++;
		} catch (IOException e) {
			System.err.println("Error in FileOps#getNumberOfLinesInFile: "
					+ e.getMessage());
		}
		return i;
	}

	// ******************************************************************************************************
	/**
	 * Writes specified string content to file
	 * <p>
	 * 
	 * @param filename
	 *            path and filename of file to write string to
	 * @param sContents
	 *            String to write to file
	 * @author Tony Venditti
	 * @modified Evan Hon
	 * @date 2012.10.5
	 */
	// /*******************************************************************************************************/
	public static boolean writeFileContents(String filename, String sContents)
			throws IOException {
		// write specified string content to file
		FileWriter out = null;
		boolean result = false;
		try {
			out = new FileWriter(filename);
			out.write(sContents);
			result = true;
		} catch (IOException e) {
			System.err.println("Error in FileOps # writeFileContents: "
					+ e.getMessage());
			result = false;
		} finally {
			out.close();
		}
		return result;
	}

	/**
	 * 
	 * @param filename
	 * @param lsContents
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFileContents(String filename,
			Collection lsContents) throws IOException {
		// write specified string content to file
		FileWriter out = null;
		boolean result = false;
		try {
			out = new FileWriter(filename);
			Iterator i = lsContents.iterator();
			while (i.hasNext()) {
				out.write(i.next().toString() + "\n");
			}
			result = true;
		} catch (IOException e) {
			System.err.println("Error in FileOps#writeFileContents: "
					+ e.getMessage());
			result = false;
		} finally {
			out.close();
		}
		return result;
	}

	// ********************************************************************************************************
	/**
	 * Appends specified string content to a file
	 * <p>
	 * 
	 * @param filename
	 *            path and filename of file to append string to
	 * @param sContents
	 *            String to append to file
	 * @author Tony Venditti
	 */
	// /*******************************************************************************************************/
	public static void appendStringToFile(String filename, String sContents) {

		try {
			FileWriter out = new FileWriter(filename, true); // tells FileWriter
																// to append
			out.write(System.getProperty("line.separator") + sContents);
			out.close();
		} catch (IOException e) {
			System.err.println("Error in FileOps#appendStringToFile: "
					+ e.getMessage());
		}
	}

	/**
	 * 
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}

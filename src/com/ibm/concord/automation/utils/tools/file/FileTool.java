/*
 * Created on Mar 22, 2005
 *
 */
package com.ibm.concord.automation.utils.tools.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.ibm.concord.automation.utils.tools.data.DataGenerator;


//import com.ibm.concord.automation.tasks.common.Logger;

/**
 * Class contains methods of file operations
 * 
 * @author mquatrale
 * @since 2005-03-23
 */
public class FileTool {
	// Create the Log test object


	/**
	 * Returns file content as a String Array
	 * 
	 * @param filename
	 *            Path and filename of file to read
	 * @return file content as a String Array
	 * @author Tony Venditti Note LMM transfered this over here. this is used in
	 *         TestCaller
	 */
	public static String[] getFileContentsAsList(String filename) {
		// int z = 0;
		// int n = getNumberOfLinesInFile(filename);
		ArrayList<String> list = new ArrayList<String>();
		// String lsLines[] = new String[n];
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = in.readLine()) != null) {
				// if (z >= n)
				// break;
				list.add(line);
			}
			in.close();
		} catch (IOException e) {
		//	log.logError("error getting lines in file: " + e.getMessage());
		}

		String[] sReturn = new String[list.size()];
		return list.toArray(sReturn);
	}

	/**
	 * Copies src file to dst file. If the dst file does not exist, it is
	 * created.
	 * 
	 * @param fFromFileName
	 *            Source File
	 * @param fToFileName
	 *            Destination File
	 * @author mquatrale
	 * @since 2005-03-23
	 */
	public static void fileCopy(File fFromFileName, File fToFileName)
			throws IOException {
		InputStream in = new FileInputStream(fFromFileName);

		OutputStream out = new FileOutputStream(fToFileName);

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];

		int len;

		try {
			// Reads up to len bytes of data from this input stream into an
			// array of bytes.
			// Returns the total number of bytes read into the buffer "buf",
			// or -1 if there is no more data because the end of the file has
			// been reached.
			while ((len = in.read(buf)) > 0) {
				// Writes len bytes from the specified byte array
				// starting at offset off to this file output stream.
				// "buf" is the data, "0" is the start offset in the data,
				// "len" is number of bytes to write.
				out.write(buf, 0, len);
			}
		} finally {
			// close the io stream in any condition
			in.close();
			out.close();
		}

	} // end of FileCopy

	/**
	 * Checks to see if a File or directory exists
	 * 
	 * @param sPathToFileorDir
	 * 
	 * @return boolean true if exists
	 * @since 2005-12-29
	 * @author lmccollu
	 */
	public static boolean exists(String sPathToFileorDir) {
		File fTmp = new File(sPathToFileorDir);
		if (fTmp.exists()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * recursivly copy a dir
	 * 
	 * @param sFromDirPath
	 * @param sToDirPath
	 * @param bDeleteCopiedFiles
	 */
	public static boolean fileCopyDirRecursive(String sFromDirPath,
			String sToDirPath, boolean bDeleteCopiedFiles, boolean bSkipError) {
		return fileCopyDirRecursive(new File(sFromDirPath),
				new File(sToDirPath), bDeleteCopiedFiles, bSkipError);
	}

	/**
	 * recursivly copy a dir
	 * 
	 * @param sFromDirPath
	 * @param sToDirPath
	 * @param bDeleteCopiedFiles
	 */
	public static boolean fileCopyDirRecursive(File src, File dest,
			boolean bDeleteCopiedFiles, boolean bSkipError) {

		if (!src.exists()) {
			//log.logError(src + " is not exists");
			return false;
		}

		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdirs();
			}

			File files[] = src.listFiles();
			for (int i = 0; i < files.length; i++) {
				fileCopyDirRecursive(files[i], new File(dest + File.separator
						+ files[i].getName()), bDeleteCopiedFiles, bSkipError);
			}
		} else {
			try {
				fileCopy(src, dest);
			} catch (IOException e) {
				if (!bSkipError) {

					System.out
							.println("Exception catched at fileCopyDirRecursive");
		//			log.logException(e);
					return false;
				} else {
			//		log.logWarning(e.getMessage());
				}
			}
		}

		if (bDeleteCopiedFiles) {
			src.delete();
		}
		return true;

	}

	/**
	 * Copies src directory to dst directory. Copies all files from one dir to
	 * another Does not copy directories inside directories. Only files will be
	 * copied
	 * 
	 * @param sFromDirPath
	 *            Source Directory
	 * @param sToDirPath
	 *            Destination Directory
	 * @author mquatrale
	 * @since 2005-06-30
	 */
	public static void fileCopyDir(String sFromDirPath, String sToDirPath) {
		fileCopyDir(sFromDirPath, sToDirPath, false);
	}

	/**
	 * Creates a new directory given path name
	 * 
	 * @param sPath
	 * @return boolean true if created dir
	 * @since 2005-12-29
	 * @author lmccollu
	 */
	public static boolean mkDir(String sPath) {
		File fDir = new File(sPath);
		if (fDir.mkdir()) {
			//log.logDetail("Created new directory");
			return true;
		} else {
			//log.logDetail("Did not create new directory");
			return false;
		}
	}

	/**
	 * Copies src directory to dst directory. Copies all files from one dir to
	 * another Does not copy directories inside directories. Only files will be
	 * copied If you want to delete the files that were copied over
	 * bDeleteCopiedFiles = true
	 * 
	 * @param sFromDirPath
	 *            Source Directory
	 * @param sToDirPath
	 *            Destination Directory
	 * @param bDeleteCopiedFiles
	 *            If delete copied files or not
	 * @author mquatrale
	 * @since 2005-06-30
	 */
	public static void fileCopyDir(String sFromDirPath, String sToDirPath,
			boolean bDeleteCopiedFiles) {

		File fDir = new File(sFromDirPath);
		// File Array to hold list of temp test result files

		File[] fTempFilesToBeSaved = fDir.listFiles();

		// String sTempFileName = null;

		File fTmp = new File(sToDirPath);
		if (!fTmp.exists()) {
			if (fTmp.mkdirs()) {
				//log.logDetail("Created folder for storing files");
			} else {
				//log.logDetail("Failed to create save folder");

			}
		}
		// Copy Files to be saved to new directory
		try {
			for (int i = 0; i < fTempFilesToBeSaved.length; i++) {
				if (!fTempFilesToBeSaved[i].isDirectory()) {
					File fSaveName = new File(sToDirPath + File.separator
							+ fTempFilesToBeSaved[i].getName());
					FileTool.fileCopy(fTempFilesToBeSaved[i], fSaveName);
					// Deletes File after being copied if bDeleteCopiedFiles =
					// true
					if (bDeleteCopiedFiles) {
						fTempFilesToBeSaved[i].delete();
					}
				}
			}

		} catch (IOException e) {
			//log.logError("IO Error - File did not get copied");
		}

	}

	/**
	 * Delete a file
	 * 
	 * @param sFilePath
	 *            Given file path
	 * @author lmccollu
	 * @since 2005-12-29
	 */
	public static boolean deleteFile(String sFilePath) {
		//log.logDebug("Deleting file " + sFilePath + " from the file system");
		boolean bSuccess = false;
		File fFile = new File(sFilePath);
		try {
			if (!fFile.exists()) {
				//log.logWarning("File " + sFilePath + " does not exist");
			}
			fFile.delete();
			bSuccess = true;
		} catch (SecurityException e) {
			//log.logWarning("Attempting to delete file " + sFilePath
			//		+ " resulted in SecurityException.");
			bSuccess = false;
		}
		return bSuccess;
	}

	/**
	 * Deletes all the files in a given directory
	 * 
	 * @param sDirPath
	 *            Given directory
	 * @author mquatrale
	 * @since 2005-06-30
	 */
	public static void fileDeleteDir(String sDirPath) {
		File fDir = new File(sDirPath);

		// File Array to hold list of temp test result files
		File[] fTempFilesToBeDeleted = fDir.listFiles();
		if (fTempFilesToBeDeleted == null) {
			//log.logDetail("Directory not found or no files found to be deleted in "
			//				+ sDirPath);
			return;
		}

		// Delete each file
		for (int i = 0; i < fTempFilesToBeDeleted.length; i++) {
			if (fTempFilesToBeDeleted[i].isDirectory()) {
				fileDeleteDir(fTempFilesToBeDeleted[i].getAbsolutePath());
			}
			fTempFilesToBeDeleted[i].delete();

		}

	}

	/**
	 * Takes the passed file array "fFileArray", and sorts it based on the file
	 * creation times.
	 * 
	 * @param fFileArray
	 *            File array to be sorted.
	 * @return Returns an array of strings "sSortedFileArrayNames", that
	 *         contains the file names sorted by created times, which is really
	 *         creation time.
	 * @author lianglin
	 * @since 2010-10-10
	 */
	public synchronized static String[] getSortFile(File[] fFileArray) {
		try {
			int length = fFileArray.length;
			Map<Long, String> mapfile = new TreeMap<Long, String>();
			// Map<String,String> mapfile = new TreeMap<String,String>();
			for (int i = 0; i < length; i++) {
				mapfile.put(new Long(fFileArray[i].lastModified()),
						fFileArray[i].getName());
				// mapfile.put(getFileCreateDate(fFileArray[i])+i,
				// fFileArray[i].getName());
			}
			Collection<String> filecoll = mapfile.values();
			length = filecoll.size();
			if (length == 0)
				return null;
			String[] ret = new String[length];
			Iterator<String> ita = filecoll.iterator();
			int i = 0;
			while (ita.hasNext()) {
				Object obj = ita.next();
				ret[i++] = (String) obj;
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * get the file's created time under windows ()
	 * 
	 * @param _file
	 * @return
	 */
	public static String getFileCreateDate(File _file) {
		File file = _file;
		try {
			Process ls_proc = Runtime.getRuntime().exec(
					"cmd.exe /c dir \"" + file.getAbsolutePath() + "\" /tc");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					ls_proc.getInputStream()));
			for (int i = 0; i < 5; i++) {
				in.readLine();
			}
			String stuff = in.readLine();
			StringTokenizer st = new StringTokenizer(stuff);
			String dateC = st.nextToken();
			String time = st.nextToken();
			in.close();
			ls_proc.destroy();
			return dateC + " " + time;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Takes the passed file array "fFileArray", and sorts it based on the file
	 * creation times.
	 * 
	 * @param fFileArray
	 *            File array to be sorted.
	 * @return Returns an array of strings "sSortedFileArrayNames", that
	 *         contains the file names sorted by lastModified times, which is
	 *         really creation time.
	 * @author mquatrale
	 * @since 2005-03-23
	 */
	public static String[] sortFileArrayByTime(File[] fFileArray) {
		// File array to hold the sorted Files.
		File[] fSortedFileArray = new File[fFileArray.length];

		// Long int array to hold the sorted file modification times.
		long lSortedFileModTimes[] = new long[fFileArray.length];

		// String array to hold the sorted File Names that will be returned.
		String[] sSortedFileArrayNames = new String[fFileArray.length];

		// Loop through each of the files in the fFileArray, and get the mod
		// times.
		for (int i = 0; i < fFileArray.length; i++) {
			// Puts the modified times into the lSortedFileModTimes[i]array.
			lSortedFileModTimes[i] = fFileArray[i].lastModified();
		}

		// Now sort the mod times in this array.
		Arrays.sort(lSortedFileModTimes);

		// Compare the lastModified time of each entry in fFileArray vs the
		// lSortedFileModTimes array.
		// When we find a match, copy that file to the matching index in
		// fSortedFileArray.
		for (int i = 0; i < fFileArray.length; i++) {
			for (int y = 0; y < lSortedFileModTimes.length; y++) {
				if (fFileArray[i].lastModified() == lSortedFileModTimes[y]) {
					fSortedFileArray[y] = fFileArray[i];
				}
			}

		}

		// Now we have a sorted array.
		// Copy the file names of this array to the sSortedFileArrayNames array.
		for (int i = 0; i < fSortedFileArray.length; i++) {
			sSortedFileArrayNames[i] = fSortedFileArray[i].getName();
		}

		// Return the array of time sorted file names.
		return sSortedFileArrayNames;
	}

	/**
	 * Creates a unique Filename To make a valid file name you will need to
	 * insert a letter at the beginning of the file name. This is useful for
	 * saving files that you do not need to check in such as for a screen
	 * capture when an unexpected error is encountered.
	 * 
	 * @param none
	 * @return String of integers to be used as a file name
	 * @author mquatrale
	 * @since 2005-06-28
	 */
	public static String uniqueFileName() {
		return DataGenerator.uniqueFileName();
	}

	/**
	 * Verifies that a file exists.
	 * 
	 * @param sFileName
	 *            Name of the file to search for.
	 * @param sPath
	 *            Complete path to the file.
	 * @return If the file exists or not
	 * @author mquatrale
	 * @since 2005-04-02
	 */
	public static boolean findFile(String sFileName, String sPath) {
		Character chDelim = new Character(System.getProperty("file.separator")
				.charAt(0));

		String sFullFileName = sPath.replace('.', chDelim.charValue())
				+ chDelim + sFileName;

		if (new File(sFullFileName).exists()) {
			//log.logDebug("Found the file: " + sFullFileName);
			return true;
		} else {
			//log.logDebug("File not found: " + sFullFileName);
			return false;
		}

	}

	public static File getLastModified(File[] files) {
		// find latest nsd file
		long last = 0;
		File f = null;
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (last < files[i].lastModified()) {
					f = files[i];
					last = f.lastModified();
				}
			}
		}
		return f;
	}

	/**
	 * Get the project root path
	 * 
	 * @return
	 * @author lianglin
	 * @since 2010-08-09
	 */
	public static String getProjectPath() {
		String outputPath = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		return outputPath;
	}

	// ***Add By Yue Qin***//
	public static void writeFileContents(String filename, String sContents) {
		// write specified string content to file
		try {
			FileWriter out = new FileWriter(filename);
			out.write(sContents);
			out.close();
		} catch (IOException e) {
			System.err.println("Error in FileOps#writeFileContents: "
					+ e.getMessage());
		}
	}

	public static void writeFileContents(String filename,
			Collection<String> lsContents) {
		// write specified string content to file
		try {
			FileWriter out = new FileWriter(filename);
			Iterator<String> i = lsContents.iterator();
			while (i.hasNext()) {
				out.write(i.next().toString());
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Error in FileOps#writeFileContents: "
					+ e.getMessage());
		}
	}

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
	public static FileOutputStream getExistsFileOutputStream(String filePath)
	{
		File tmp = new File(filePath);
		if(tmp.exists())
		{
			try {
				return new FileOutputStream(tmp);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
}

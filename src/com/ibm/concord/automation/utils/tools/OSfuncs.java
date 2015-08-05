/**
 * 
 */
package com.ibm.concord.automation.utils.tools;

import java.net.InetAddress;

/**
 * @author liuweisd
 * 
 */
public class OSfuncs {
	
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			if (isWindowsOS()) {
				ip = InetAddress.getLocalHost();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}

	/**
	 * 
	 * 
	 * @return true
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}
}

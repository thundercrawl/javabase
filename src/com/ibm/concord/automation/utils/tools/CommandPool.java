package com.ibm.concord.automation.utils.tools;
/**
 * Record current command list
 * @author Wang Rui Zhi
 * @date 2012-5-2
 *
 */
public class CommandPool{
	public static String[] commands = null;
	public static int commandsNum = 0;
	public static int currentNum = 0;
	/**
	 * @return the commands
	 */
	public static String[] getCommands() {
		return commands;
	}
	/**
	 * @param commands the commands to set
	 */
	public static void setCommands(String[] commands) {
		CommandPool.commands = commands;
	}
	/**
	 * @return the commandsNum
	 */
	public static int getCommandsNum() {
		return commandsNum;
	}
	/**
	 * @param commandsNum the commandsNum to set
	 */
	public static void setCommandsNum(int commandsNum) {
		CommandPool.commandsNum = commandsNum;
	}
	/**
	 * @return the currentNum
	 */
	public static int getCurrentNum() {
		return currentNum;
	}
	/**
	 * @param currentNum the currentNum to set
	 */
	public static void setCurrentNum(int currentNum) {
		CommandPool.currentNum = currentNum;
	}
}
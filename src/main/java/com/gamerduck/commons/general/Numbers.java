package com.gamerduck.commons.general;

public class Numbers {
	/**
	 * Checks if a string is a number
	 *
	 * @param str The string to check if it is a number or not
	 * @return Whether or not the string is a number
	 */
	public static boolean isNumber(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 * Checks if a string is a integer
	 *
	 * @param str The string to check if it is a integer or not
	 * @return Whether or not the string is a integer 
	 */
	public static boolean isInteger(String str) {
		return isNumber(str);
	}

	/**
	 * Checks if a string is a double
	 *
	 * @param str The string to check if it is a double or not
	 * @return Whether or not the string is a double
	 */
	public static boolean isDouble(String str) {
		try {
			Double.valueOf(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 * Checks if a string is a long
	 *
	 * @param str The string to check if it is a long or not
	 * @return Whether or not the string is a long
	 */
	public static boolean isLong(String str) {
		try {
			Long.valueOf(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 * Checks if a string is a float
	 *
	 * @param str The string to check if it is a float or not
	 * @return Whether or not the string is a float
	 */
	public static boolean isFloat(String str) {
		try {
			Float.valueOf(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
}

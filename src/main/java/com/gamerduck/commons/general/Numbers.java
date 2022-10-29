package com.gamerduck.commons.general;

import static com.google.common.base.Strings.repeat;

public class Numbers {

	/**
	 * Converts a number to a roman numeral
	 *
	 * @param num The number to convert to roman numerals
	 * @return The corresponding roman numeral
	 */
	public static String toRoman(int num) {
		StringBuilder res = new StringBuilder();
		for (RomanNumerals numeral : RomanNumerals.values()) {
			int matches = num / numeral.amount;
			res.append(repeat(numeral.toString(), matches));
			num = num % numeral.amount;
		}
		return res.toString();
	}

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
enum RomanNumerals {
	M(1000),
	CM(900),
	D(500),
	CD(400),
	C(100),
	XC(90),
	L(50),
	XL(40),
	X(10),
	IX(9),
	V(5),
	IV(4),
	I(1),
	;
	public int amount;
	RomanNumerals(int amount) {
		this.amount = amount;
	}

}
package com.gamerduck.commons.general;

import com.google.common.collect.Lists;

import java.util.ArrayList;

import static com.google.common.base.Strings.repeat;

public class Numbers {

	public static ArrayList<Integer> numbers = Lists.newArrayList(
			1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
	);

	public static ArrayList<String> letters = Lists.newArrayList(
			"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
	);

	/**
	 * Converts a number to a roman numeral
	 *
	 * @param num The number to convert to roman numerals
	 * @return The corresponding roman numeral
	 */
	public static String toRoman(int num) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 13; i++) {
			int matches = num / numbers.get(i);
			res.append(repeat(letters.get(i), matches));
			num = num % numbers.get(i);
		}
		return res.toString();
	}

	public static void setRomanCharacters(String M, String CM, String D, String CD, String C, String XC, String L,
									 String XL, String X, String IX, String V, String IV, String I) {
		letters.set(0, M);
		letters.set(1, CM);
		letters.set(2, D);
		letters.set(3, CD);
		letters.set(4, C);
		letters.set(5, XC);
		letters.set(6, L);
		letters.set(7, XL);
		letters.set(8, X);
		letters.set(9, IX);
		letters.set(10, V);
		letters.set(11, IV);
		letters.set(0, I);
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
		} catch (NumberFormatException e) {
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
		} catch (NumberFormatException e) {
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
		} catch (NumberFormatException e) {
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
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
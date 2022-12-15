package com.gamerduck.commons.general;

import com.google.common.collect.Lists;

import java.util.ArrayList;

import static com.google.common.base.Strings.repeat;

public class Numbers {

	public final static ArrayList<Integer> numbers = Lists.newArrayListWithCapacity(12);
	public final static ArrayList<String> letters = Lists.newArrayListWithCapacity(12);

	static {
		letters.set(0, "M");
		numbers.set(0, 1000);
		letters.set(1, "CM");
		numbers.set(1, 900);
		letters.set(2, "D");
		numbers.set(2, 500);
		letters.set(3, "CD");
		numbers.set(3, 400);
		letters.set(4, "C");
		numbers.set(4, 100);
		letters.set(5, "XC");
		numbers.set(5, 90);
		letters.set(6, "L");
		numbers.set(6, 50);
		letters.set(7, "XL");
		numbers.set(7, 40);
		letters.set(8, "X");
		numbers.set(8, 10);
		letters.set(9, "IX");
		numbers.set(9, 9);
		letters.set(10, "V");
		numbers.set(10, 5);
		letters.set(11, "IV");
		numbers.set(11, 4);
		letters.set(12, "I");
		numbers.set(12, 1);
	}

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
		letters.set(12, I);
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
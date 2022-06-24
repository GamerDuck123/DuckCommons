package com.gamerduck.commons.general;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.md_5.bungee.api.ChatColor;

public class Strings {
	
	/**
	 * Capitalizes the first letter of a string
	 *
	 * @param str The string to capitalize
	 * @return The string with the first letter capitalized
	 */
	public static String capitalizeFirst(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * Capitalizes the first letter of all words in the string
	 *
	 * @param str The string to capitalize
	 * @return The string with the first letter of each word capitalized
	 */
	public static String capitalizeEach(String s) {
		String[] split = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String st : split) builder.append(st.substring(0, 1).toUpperCase() + st.substring(1));
		return builder.toString();
	}

	/**
	 * Capitalizes the first letter of all words in the string
	 *
	 * @param str The string to capitalize
	 * @return The string with the first letter of each word capitalized
	 */
	public static String capitalizeEach(String... s) {
		StringBuilder builder = new StringBuilder();
		for (String st : s) builder.append(st.substring(0, 1).toUpperCase() + st.substring(1));
		return builder.toString();
	}

	/**
	 * Uncapitalizes the first letter of a string
	 *
	 * @param str The string to uncapitalize
	 * @return The string with the first letter uncapitalized
	 */
	public static String uncapitalizeFirst(String s) {
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	/**
	 * Uncapitalizes the first letter of all words in the string
	 *
	 * @param str The string to uncapitalize
	 * @return The string with the first letter of each word uncapitalized
	 */
	public static String uncapitalizeEach(String s) {
		String[] split = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String st : split) builder.append(st.substring(0, 1).toLowerCase() + st.substring(1));
		return builder.toString();
	}

	/**
	 * Uncapitalizes the first letter of all words in the string
	 *
	 * @param str The string to uncapitalize
	 * @return The string with the first letter of each word uncapitalized
	 */
	public static String uncapitalizeEach(String... s) {
		StringBuilder builder = new StringBuilder();
		for (String st : s) builder.append(st.substring(0, 1).toLowerCase() + st.substring(1));
		return builder.toString();
	}

	/**
	 * Colors a string using hex codes and regular color codes
	 *
	 * @param textToTranslate The string to color
	 * @return The string, colored using hex and regular color codes
	 */
	private final static Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
	public static String colorHex(String textToTranslate) {
		Matcher matcher = HEX_PATTERN.matcher(textToTranslate);
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()) matcher.appendReplacement(buffer, ChatColor.of("#" + matcher.group(1)).toString());
		return color(matcher.appendTail(buffer).toString());
	} 

	/**
	 * Colors a list of strings using hex codes and regular color codes
	 *
	 * @param list The list of strings to color
	 * @return The list of strings, colored using hex and regular color codes
	 */
	public static List<String> colorHex(List<String> list) {
		return list.stream()
				.peek(Strings::colorHex)
				.collect(Collectors.toList());
	}    

	/**
	 * Colors an array of strings using hex codes and regular color codes
	 *
	 * @param strings The strings to color
	 * @return The strings, colored using hex and regular color codes
	 */
	public static List<String> colorHex(String... strings) {
		return colorHex(Arrays.asList(strings));
	}

	/**
	 * Colors a string using regular color codes
	 *
	 * @param textToTranslate The string to color
	 * @return The string, colored using regular color codes
	 */
	public static String color(String textToTranslate) {
		return ChatColor.translateAlternateColorCodes('&', textToTranslate);
	}

	/**
	 * Colors a list of strings using regular color codes
	 *
	 * @param list The list of strings to color
	 * @return The list of strings, colored using regular color codes
	 */
	public static List<String> color(List<String> list) {
		return list.stream()
				.peek(Strings::color)
				.collect(Collectors.toList());
	}

	/**
	 * Colors an array of strings using regular color codes
	 *
	 * @param strings The strings to color
	 * @return The strings, colored using regular color codes
	 */
	public static List<String> color(String... strings) {
		return color(Arrays.asList(strings));
	}

	/**
	 * Removes the color from a string
	 *
	 * @param input The string to remove the color from
	 * @return The string with the color removed
	 */
	private final static Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('&') + "[0-9A-FK-OR]");
	public static String stripColor(String input) {
		input = ChatColor.stripColor(input);
		return input == null ? null : STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
	}

	/**
	 * Combines all of the strings to one big string
	 *
	 * @param strings The strings to combine
	 * @return One big string combined with all of the strings
	 */
	public static String combine(String... strings) {
		StringBuilder builder = new StringBuilder();
		for (String st : strings) builder.append(st + " ");
		return builder.toString();
	}

	/**
	 * Removes all of the whitespace from a string
	 *
	 * @param str The string to trim 
	 * @return The string with all of the whitespace removed
	 */
	public static String trim(String str) {
		return str.trim();
	}

	public static String truncate(String str, int maxWidth) {
		return truncate(str, maxWidth, 0);
	}

	public static String truncate(String str, int maxWidth, int offset) {
		return str.substring(offset, maxWidth);
	}

	/**
	 * Checks if a string is empty 
	 *
	 * @param str The string to check
	 * @return A boolean, whether or not the string is empty
	 */
	public static boolean isEmpty(String str) {
		return str.equals("") || str == null || str.isEmpty() ? true : false;
	}

	/**
	 * Checks if a char is upper case
	 *
	 * @param c The char to check
	 * @return A boolean, whether or not the char is upper case
	 */
	public static boolean isUpperCase(char c) {
		return Character.isUpperCase(c);
	}

	/**
	 * Checks if a char is lower case
	 *
	 * @param c The char to check
	 * @return A boolean, whether or not the char is lower case
	 */
	public static boolean isLowerCase(char c) {
		return Character.isLowerCase(c);
	}

	/**
	 * Checks if a string starts with an upper case letter
	 *
	 * @param str The string to check
	 * @return A boolean, whether or not the string starts with an upper case letter
	 */
	public static boolean startsWithUpperCase(String str) {
		return isUpperCase(str.substring(0, 1).toCharArray()[0]);
	}

	/**
	 * Checks if a string starts with a lower case letter
	 *
	 * @param str The string to check
	 * @return A boolean, whether or not the string starts with a lower case letter
	 */
	public static boolean startsWithLowerCase(String str) {
		return isLowerCase(str.substring(0, 1).toCharArray()[0]);
	}

	/**
	 * Checks if a string has both lower and upper case letters
	 *
	 * @param str The string to check
	 * @return A boolean, whether or not the string has both
	 */
	public static boolean isMixedCase(String str) {
		boolean containsUpper = false;
		boolean containsLower = false;
		for (char ch : str.toCharArray()) {
			if (isUpperCase(ch)) containsUpper = true;
			if (isLowerCase(ch)) containsLower = true;
		}
		return containsUpper && containsLower ? true : false; 
	}

}
package com.gamerduck.commons.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.md_5.bungee.api.ChatColor;

public class Strings {
	public static String capitalizeFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String capitalizeEach(String s) {
		String[] split = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String st : split) builder.append(st.substring(0, 1).toUpperCase() + st.substring(1));
		return builder.toString();
	}

	public static String capitalizeEach(String... s) {
		StringBuilder builder = new StringBuilder();
		for (String st : s) builder.append(st.substring(0, 1).toUpperCase() + st.substring(1));
		return builder.toString();
	}
	public static String uncapitalizeFirst(String s) {
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	public static String uncapitalizeEach(String s) {
		String[] split = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String st : split) builder.append(st.substring(0, 1).toLowerCase() + st.substring(1));
		return builder.toString();
	}

	public static String uncapitalizeEach(String... s) {
		StringBuilder builder = new StringBuilder();
		for (String st : s) builder.append(st.substring(0, 1).toLowerCase() + st.substring(1));
		return builder.toString();
	}

	private final static Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
	public static String colorHex(String textToTranslate) {
		Matcher matcher = HEX_PATTERN.matcher(textToTranslate);
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()) matcher.appendReplacement(buffer, ChatColor.of("#" + matcher.group(1)).toString());
		return color(matcher.appendTail(buffer).toString());
	} 

	public static List<String> colorHex(List<String> s) {
		return s.stream()
				.peek(Strings::colorHex)
				.collect(Collectors.toList());
	}    

	public static List<String> colorHex(String... s) {
		return colorHex(Arrays.asList(s));
	}

	public static String color(String textToTranslate) {
		return ChatColor.translateAlternateColorCodes('&', textToTranslate);
	}

	public static List<String> color(List<String> s) {
		return s.stream()
				.peek(Strings::color)
				.collect(Collectors.toList());
	}

	public static List<String> color(String... s) {
		return color(Arrays.asList(s));
	}
	
	private final static Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('&') + "[0-9A-FK-OR]");
    public static String stripColor(String input) {
    	input = ChatColor.stripColor(input);
        return input == null ? null : STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

	public static String combine(String... strings) {
		StringBuilder builder = new StringBuilder();
		for (String st : strings) builder.append(st);
		return builder.toString();
	}
	
	public static String trim(String s) {
		return s.trim();
	}
	
	public static String truncate(String str, int maxWidth) {
		return truncate(str, maxWidth, 0);
	}
	
	public static String truncate(String str, int maxWidth, int offset) {
		return str.substring(offset, maxWidth);
	}
	
	public static boolean isEmpty(String str) {
		return str.equals("") || str == null ? true : false;
	}
	
	public static boolean isUpperCase(char c) {
		return Character.isUpperCase(c);
	}
	public static boolean isLowerCase(char c) {
		return Character.isLowerCase(c);
	}
	public static boolean startsWithUpperCase(String str) {
		return isUpperCase(str.substring(0, 1).toCharArray()[0]);
	}
	public static boolean startsWithLowerCase(String str) {
		return isLowerCase(str.substring(0, 1).toCharArray()[0]);
	}
	
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

package com.gamerduck.commons.general;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.md_5.bungee.api.ChatColor;

public class Strings {
	/**
	 * Centers the text
	 *
	 * @param str The string to center
	 * @return The string, but centered
	 */
	private final static int CENTER_PX = 154;
	public static String centerString(String message){
		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;

		for(char c : message.toCharArray()){
			if(c == '§'){
				previousCode = true;
				continue;
			}else if(previousCode == true){
				previousCode = false;
				if(c == 'l' || c == 'L'){
					isBold = true;
					continue;
				}else isBold = false;
			}else{
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				messagePxSize++;
			}
		}

		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = CENTER_PX - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while(compensated < toCompensate){
			sb.append(" ");
			compensated += spaceLength;
		}

		return sb.toString() + message;
	}
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
enum DefaultFontInfo{

	A('A', 5),
	a('a', 5),
	B('B', 5),
	b('b', 5),
	C('C', 5),
	c('c', 5),
	D('D', 5),
	d('d', 5),
	E('E', 5),
	e('e', 5),
	F('F', 5),
	f('f', 4),
	G('G', 5),
	g('g', 5),
	H('H', 5),
	h('h', 5),
	I('I', 3),
	i('i', 1),
	J('J', 5),
	j('j', 5),
	K('K', 5),
	k('k', 4),
	L('L', 5),
	l('l', 1),
	M('M', 5),
	m('m', 5),
	N('N', 5),
	n('n', 5),
	O('O', 5),
	o('o', 5),
	P('P', 5),
	p('p', 5),
	Q('Q', 5),
	q('q', 5),
	R('R', 5),
	r('r', 5),
	S('S', 5),
	s('s', 5),
	T('T', 5),
	t('t', 4),
	U('U', 5),
	u('u', 5),
	V('V', 5),
	v('v', 5),
	W('W', 5),
	w('w', 5),
	X('X', 5),
	x('x', 5),
	Y('Y', 5),
	y('y', 5),
	Z('Z', 5),
	z('z', 5),
	NUM_1('1', 5),
	NUM_2('2', 5),
	NUM_3('3', 5),
	NUM_4('4', 5),
	NUM_5('5', 5),
	NUM_6('6', 5),
	NUM_7('7', 5),
	NUM_8('8', 5),
	NUM_9('9', 5),
	NUM_0('0', 5),
	EXCLAMATION_POINT('!', 1),
	AT_SYMBOL('@', 6),
	NUM_SIGN('#', 5),
	DOLLAR_SIGN('$', 5),
	PERCENT('%', 5),
	UP_ARROW('^', 5),
	AMPERSAND('&', 5),
	ASTERISK('*', 5),
	LEFT_PARENTHESIS('(', 4),
	RIGHT_PERENTHESIS(')', 4),
	MINUS('-', 5),
	UNDERSCORE('_', 5),
	PLUS_SIGN('+', 5),
	EQUALS_SIGN('=', 5),
	LEFT_CURL_BRACE('{', 4),
	RIGHT_CURL_BRACE('}', 4),
	LEFT_BRACKET('[', 3),
	RIGHT_BRACKET(']', 3),
	COLON(':', 1),
	SEMI_COLON(';', 1),
	DOUBLE_QUOTE('"', 3),
	SINGLE_QUOTE('\'', 1),
	LEFT_ARROW('<', 4),
	RIGHT_ARROW('>', 4),
	QUESTION_MARK('?', 5),
	SLASH('/', 5),
	BACK_SLASH('\\', 5),
	LINE('|', 1),
	TILDE('~', 5),
	TICK('`', 2),
	PERIOD('.', 1),
	COMMA(',', 1),
	SPACE(' ', 3),
	DEFAULT('a', 4);

	private char character;
	private int length;

	DefaultFontInfo(char character, int length) {
		this.character = character;
		this.length = length;
	}

	public char getCharacter(){
		return this.character;
	}

	public int getLength(){
		return this.length;
	}

	public int getBoldLength(){
		if(this == DefaultFontInfo.SPACE) return this.getLength();
		return this.length + 1;
	}

	public static DefaultFontInfo getDefaultFontInfo(char c){
		for(DefaultFontInfo dFI : DefaultFontInfo.values()){
			if(dFI.getCharacter() == c) return dFI;
		}
		return DefaultFontInfo.DEFAULT;
	}
}
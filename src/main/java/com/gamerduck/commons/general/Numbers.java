package com.gamerduck.commons.general;

public class Numbers {
	public static boolean isNumber(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	
	public static boolean isInteger(String str) {
		return isNumber(str);
	}
	
	public static boolean isDouble(String str) {
		try {
			Double.valueOf(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	
	public static boolean isLong(String str) {
		try {
			Long.valueOf(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	public static boolean isFloat(String str) {
		try {
			Float.valueOf(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
}

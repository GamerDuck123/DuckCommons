package com.gamerduck.commons.general;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static com.google.common.base.Strings.repeat;

public class Numbers {

    public final static ArrayList<Integer> numbers;
    public final static ArrayList<String> letters;

    static {
        numbers = Lists.newArrayListWithCapacity(12);
        letters = Lists.newArrayListWithCapacity(12);
        letters.add(0, "M");
        numbers.add(0, 1000);
        letters.add(1, "CM");
        numbers.add(1, 900);
        letters.add(2, "D");
        numbers.add(2, 500);
        letters.add(3, "CD");
        numbers.add(3, 400);
        letters.add(4, "C");
        numbers.add(4, 100);
        letters.add(5, "XC");
        numbers.add(5, 90);
        letters.add(6, "L");
        numbers.add(6, 50);
        letters.add(7, "XL");
        numbers.add(7, 40);
        letters.add(8, "X");
        numbers.add(8, 10);
        letters.add(9, "IX");
        numbers.add(9, 9);
        letters.add(10, "V");
        numbers.add(10, 5);
        letters.add(11, "IV");
        numbers.add(11, 4);
        letters.add(12, "I");
        numbers.add(12, 1);
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

    /**
     * Rounds a double to the corresponding places, with the corresponding RoundingMode
     *
     * @param number The number to be rounded
     * @param place  The number of decimal places to be rounded to (i.e 2 = 0.00)
     * @param mode   The rounding mode for which the rounding will be done with (HALF_UP is 0.5 goes up, anything below goes down)
     * @return The rounded double
     */
    public static double round(double number, int place, RoundingMode mode) {
        return BigDecimal.valueOf(number).setScale(place, mode).doubleValue();
    }

    /**
     * Rounds a double to the corresponding places, and defaults to HALF_UP for RoundingMode
     *
     * @param number The number to be rounded
     * @param place  The number of decimal places to be rounded to (i.e 2 = 0.00)
     * @return The rounded double
     */
    public static double round(double number, int place) {
        return round(number, place, RoundingMode.HALF_UP);
    }

    /**
     * Rounds a double to 2 decimal places, and defaults to HALF_UP for RoundingMode
     *
     * @param number The number to be rounded
     * @return The rounded double
     */
    public static double round(double number) {
        return round(number, 2, RoundingMode.HALF_UP);
    }

    /**
     * Rounds a float to the corresponding places, with the corresponding RoundingMode
     *
     * @param number The number to be rounded
     * @param place  The number of decimal places to be rounded to (i.e 2 = 0.00)
     * @param mode   The rounding mode for which the rounding will be done with (HALF_UP is 0.5 goes up, anything below goes down)
     * @return The rounded float
     */
    public static float round(float number, int place, RoundingMode mode) {
        return BigDecimal.valueOf(number).setScale(place, mode).floatValue();
    }

    /**
     * Rounds a float to the corresponding places, and defaults to HALF_UP for RoundingMode
     *
     * @param number The number to be rounded
     * @param place  The number of decimal places to be rounded to (i.e 2 = 0.00)
     * @return The rounded float
     */
    public static float round(float number, int place) {
        return round(number, place, RoundingMode.HALF_UP);
    }

    /**
     * Rounds a float to 2 decimal places, and defaults to HALF_UP for RoundingMode
     *
     * @param number The number to be rounded
     * @return The rounded float
     */
    public static float round(float number) {
        return round(number, 2, RoundingMode.HALF_UP);
    }

    /**
     * Rounds a long to the corresponding places, with the corresponding RoundingMode
     *
     * @param number The number to be rounded
     * @param place  The number of decimal places to be rounded to (i.e 2 = 0.00)
     * @param mode   The rounding mode for which the rounding will be done with (HALF_UP is 0.5 goes up, anything below goes down)
     * @return The rounded long
     */
    public static long round(long number, int place, RoundingMode mode) {
        return BigDecimal.valueOf(number).setScale(place, mode).longValue();
    }

    /**
     * Rounds a long to the corresponding places, and defaults to HALF_UP for RoundingMode
     *
     * @param number The number to be rounded
     * @param place  The number of decimal places to be rounded to (i.e 2 = 0.00)
     * @return The rounded long
     */
    public static long round(long number, int place) {
        return round(number, place, RoundingMode.HALF_UP);
    }

    /**
     * Rounds a long to 2 decimal places, and defaults to HALF_UP for RoundingMode
     *
     * @param number The number to be rounded
     * @return The rounded long
     */
    public static long round(long number) {
        return round(number, 2, RoundingMode.HALF_UP);
    }
}
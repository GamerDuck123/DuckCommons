package com.gamerduck.commons.general;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class Components {

    public static final MiniMessage mm = miniMessage();

    /**
     * Converts all colorcodes formatted &a into MiniMessage colorcodes
     *
     * @param str The string to convert
     * @return The converted string
     */
    private static final Pattern OLD_PATTERN = Pattern.compile("&([A-Na-n\\d])");

    /**
     * Converts all hex colorcodes formatted &#FFFFFF into MiniMessage colorcodes
     *
     * @param str The string to convert
     * @return The converted string
     */
    private final static Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    private final static Pattern ALL_PATTERN = Pattern.compile("(.*?)");

    /**
     * Translates regular strings into a components using MiniMessage
     *
     * @param strs The strings to convert
     * @return The strings converted into components
     */
    public static List<Component> translate(List<String> strs) {
        return strs.stream().map(str -> mm.deserialize(str)).collect(Collectors.toList());
    }

    /**
     * Translates regular strings into a components using MiniMessage
     *
     * @param strs The strings to convert
     * @return The strings converted into components
     */
    public static List<Component> translate(String... strs) {
        return Arrays.stream(strs).map(str -> mm.deserialize(str)).collect(Collectors.toList());
    }

    /**
     * Translates a regular string into a component using MiniMessage
     *
     * @param str The string to convert
     * @return The string converted into a component
     */
    public static Component translate(String str) {
        return mm.deserialize(str);
    }

    public static String convertFromOldColors(String str) {
        Matcher matcher = OLD_PATTERN.matcher(str);
        StringBuilder buffer = new StringBuilder();
        while (matcher.find()) matcher.appendReplacement(buffer, ColorTranslator.of("&" + matcher.group(1)).mm);
        return matcher.appendTail(buffer).toString();
    }

    public static String convertFromOldHexColors(String str) {
        Matcher matcher = HEX_PATTERN.matcher(str);
        StringBuilder buffer = new StringBuilder();
        while (matcher.find()) matcher.appendReplacement(buffer, "<" + matcher.group(1) + ">");
        return matcher.appendTail(buffer).toString();
    }

    /**
     * Capitalizes the first letter of a component
     *
     * @param comp The component to capitalize
     * @return The component with the first letter capitalized
     */
    public static Component capitalizeFirst(Component comp) {
        return comp.replaceText(b ->
                b.once().match(ALL_PATTERN).replacement((result, builder) -> mm.deserialize(Strings.capitalizeFirst(result.group(1)))));
    }

    /**
     * Uncapitalizes the first letter of a component
     *
     * @param comp The component to uncapitalize
     * @return The component with the first letter uncapitalized
     */
    public static Component uncapitalizeFirst(Component comp) {
        return comp.replaceText(b ->
                b.once().match(ALL_PATTERN).replacement((result, builder) -> mm.deserialize(Strings.uncapitalizeFirst(result.group(1)))));
    }


    /**
     * Capitalizes the first letter of all words in the component
     *
     * @param comp The component to capitalize
     * @return The component with the first letter of each word capitalized
     */
    public static Component capitalizeEach(Component comp) {
        return comp.replaceText(b ->
                b.match(ALL_PATTERN).replacement((result, builder) -> mm.deserialize(Strings.capitalizeEach(result.group(1)))));
    }


    /**
     * Uncapitalizes the first letter of all words in the component
     *
     * @param comp The component to uncapitalize
     * @return The component with the first letter of each word uncapitalized
     */
    public static Component uncapitalizeEach(Component comp) {
        return comp.replaceText(b ->
                b.match(ALL_PATTERN).replacement((result, builder) -> mm.deserialize(Strings.uncapitalizeFirst(result.group(1)))));
    }

    /**
     * Combines all of the components to one big component
     *
     * @param comps The components to combine
     * @return One big component combined with all of the components
     */
    public static Component combine(Component... comps) {
        Component comp = Component.empty();
        Arrays.asList(comps).stream().peek(comp::append);
        return comp;
    }

    /**
     * Combines all of the components to one big component
     *
     * @param comps The components to combine
     * @return One big component combined with all of the components
     */
    public static Component combine(List<Component> comps) {
        Component comp = Component.empty();
        comps.stream().peek(comp::append);
        return comp;
    }

}

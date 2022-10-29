package com.gamerduck.commons.general;

public enum ColorTranslator {
    black("&0", "<black>"),
    dark_blue("&1", "<dark_blue>"),
    dark_green("&2", "<dark_green>"),
    dark_aqua("&3", "<dark_aqua>"),
    dark_red("&4", "<dark_red>"),
    dark_purple("&5", "<dark_purple>"),
    gold("&6", "<gold>"),
    gray("&7", "<gray>"),
    dark_gray("&8", "<dark_gray>"),
    blue("&9", "<blue>"),
    green("&a", "<green>"),
    aqua("&b", "<aqua>"),
    red("&c", "<red>"),
    light_purple("&d", "<light_purple>"),
    yellow("&e", "<yellow>"),
    white("&f", "<white>"),
    bold("&l", "");

    public String code;
    public String mm;

    ColorTranslator(String code, String mm) {
        this.code = code;
        this.mm = mm;
    }

    public static ColorTranslator of(String s) {
        ColorTranslator returnColor = white;
        for (ColorTranslator color : ColorTranslator.values()) {
            if (s.equalsIgnoreCase(color.code)) {
                returnColor = color;
                break;
            }
        }
        return returnColor;
    }
}

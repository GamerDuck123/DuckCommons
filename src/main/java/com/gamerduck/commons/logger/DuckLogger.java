package com.gamerduck.commons.logger;

import com.gamerduck.commons.general.Strings;
import org.bukkit.Bukkit;

import java.util.logging.Level;

/**
 * This class provides a quick and easy logger using Bukkit's logger
 *
 * @author GamerDuck123
 */
public class DuckLogger {
    public static void info(String str) {
        Bukkit.getLogger().info(str);
    }

    public static void error(String str) {
        Bukkit.getLogger().severe(str);
    }

    public static void warn(String str) {
        Bukkit.getLogger().warning(str);
    }

    public static void sendMessage(String str) {
        Bukkit.getLogger().log(Level.INFO, Strings.color(str));
    }
}

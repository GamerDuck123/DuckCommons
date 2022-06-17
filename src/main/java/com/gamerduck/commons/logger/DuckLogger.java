package com.gamerduck.commons.logger;

import java.util.logging.Level;

import org.bukkit.Bukkit;

import com.gamerduck.commons.general.Strings;

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
	
	public static void sendMesasge(String str) {
		Bukkit.getLogger().log(Level.INFO, Strings.color(str));
	}
}

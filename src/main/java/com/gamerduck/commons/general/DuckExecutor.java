package com.gamerduck.commons.general;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class DuckExecutor {

    public static JavaPlugin plugin = null;
    private static BukkitScheduler scheduler = Bukkit.getScheduler();

    public static void setup(JavaPlugin pl) {
        plugin = pl;
    }

    public static void runAsync(Runnable run) {
        scheduler.runTaskAsynchronously(plugin, run);
    }

    public static void runAsyncLater(Runnable run, int delay) {
        scheduler.runTaskLaterAsynchronously(plugin, run, delay);
    }

    public static void runAsyncTimer(Runnable run, int delay, int period) {
        scheduler.runTaskTimerAsynchronously(plugin, run, delay, period);
    }

    public static void run(Runnable run) {
        scheduler.runTask(plugin, run);
    }

    public static void runTaskLater(Runnable run, int delay) {
        scheduler.runTaskLater(plugin, run, delay);
    }

    public static void runTaskTimer(Runnable run, int delay, int period) {
        scheduler.runTaskTimer(plugin, run, delay, period);
    }


}
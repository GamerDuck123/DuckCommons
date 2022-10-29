package com.gamerduck.commons.events.extra.handlers;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class DuckEventHandler implements Listener {

    public void enable(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}

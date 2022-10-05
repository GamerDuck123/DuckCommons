package com.gamerduck.commons.items;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

import static org.bukkit.persistence.PersistentDataType.STRING;

public class DuckItemListener {

    protected static HashMap<String, Consumer<PlayerInteractEvent>> itemList = Maps.newHashMap();
    protected static JavaPlugin plugin = null;
    protected static NamespacedKey key = null;

    public static void setup(JavaPlugin pl) {
        plugin = pl;
        key = new NamespacedKey(plugin, "duck_items");

        plugin.getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onClick(PlayerInteractEvent e) {
                if (e.getItem().getItemMeta().getPersistentDataContainer().has(key, STRING)) {
                    if (itemList.containsKey(e.getItem().getItemMeta().getPersistentDataContainer().get(key, STRING))) {
                        itemList.get(e.getItem().getItemMeta().getPersistentDataContainer().get(key, STRING)).accept(e);
                    }
                }
            }
        }, plugin);
    }

}

package com.gamerduck.commons.events.extra.enhancers;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Material.AIR;

public class EnhancePlayerArmorChangeEvent implements Listener {

    public static void enable(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new EnhancePlayerArmorChangeEvent(), plugin);
    }

    @EventHandler
    public void dispenserHandler(BlockDispenseArmorEvent e) {
        if (e.getTargetEntity() instanceof Player p) {
            boolean shouldFire = false;
            PlayerArmorChangeEvent.SlotType slotType = null;
            if (e.getItem().getType().toString().contains("HELMET")
                    && (p.getInventory().getHelmet() == null || p.getInventory().getHelmet().getType() == AIR)) {
                shouldFire = true;
                slotType = PlayerArmorChangeEvent.SlotType.HEAD;
            }
            if (e.getItem().getType().toString().contains("CHESTPLATE")
                    && (p.getInventory().getChestplate() == null || p.getInventory().getChestplate().getType() == AIR)) {
                shouldFire = true;
                slotType = PlayerArmorChangeEvent.SlotType.CHEST;
            }
            if (e.getItem().getType().toString().contains("LEGGINGS")
                    && (p.getInventory().getLeggings() == null || p.getInventory().getLeggings().getType() == AIR)) {
                shouldFire = true;
                slotType = PlayerArmorChangeEvent.SlotType.LEGS;
            }
            if (e.getItem().getType().toString().contains("BOOTS")
                    && (p.getInventory().getBoots() == null || p.getInventory().getBoots().getType() == AIR)) {
                shouldFire = true;
                slotType = PlayerArmorChangeEvent.SlotType.FEET;
            }
            if (shouldFire) Bukkit.getPluginManager().callEvent(new PlayerArmorChangeEvent(p, slotType, new ItemStack(AIR), e.getItem()));
        }
    }
}

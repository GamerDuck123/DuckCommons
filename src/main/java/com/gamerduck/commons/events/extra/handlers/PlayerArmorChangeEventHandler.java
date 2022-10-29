package com.gamerduck.commons.events.extra.handlers;

import com.gamerduck.commons.events.extra.PlayerArmorChangeEvent;
import com.gamerduck.commons.events.extra.PlayerHandChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent.SlotType;

import static org.bukkit.Material.AIR;

public class PlayerArmorChangeEventHandler implements Listener {

    public static void enable(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new PlayerArmorChangeEventHandler(), plugin);
    }

    @EventHandler
    public void armorChangeHandler(com.destroystokyo.paper.event.player.PlayerArmorChangeEvent e) {
        Bukkit.getPluginManager().callEvent(new PlayerArmorChangeEvent(e.getPlayer(), e.getNewItem(), e.getOldItem(), e.getSlotType()));
    }

    @EventHandler
    public void dispenserHandler(BlockDispenseArmorEvent e) {
        if (e.getTargetEntity() instanceof Player p) {
            boolean shouldFire = false;
            SlotType slotType = null;
            if (e.getItem().getType().toString().contains("HELMET")
                    && (p.getInventory().getHelmet() == null || p.getInventory().getHelmet().getType() == AIR)) {
                shouldFire = true;
                slotType = SlotType.HEAD;
            }
            if (e.getItem().getType().toString().contains("CHESTPLATE")
                    && (p.getInventory().getChestplate() == null || p.getInventory().getChestplate().getType() == AIR)) {
                shouldFire = true;
                slotType = SlotType.CHEST;
            }
            if (e.getItem().getType().toString().contains("LEGGINGS")
                    && (p.getInventory().getLeggings() == null || p.getInventory().getLeggings().getType() == AIR)) {
                shouldFire = true;
                slotType = SlotType.LEGS;
            }
            if (e.getItem().getType().toString().contains("BOOTS")
                    && (p.getInventory().getBoots() == null || p.getInventory().getBoots().getType() == AIR)) {
                shouldFire = true;
                slotType = SlotType.FEET;
            }
            if (shouldFire) Bukkit.getPluginManager().callEvent(new PlayerArmorChangeEvent(p, e.getItem(), new ItemStack(AIR), slotType));
        }
    }

}

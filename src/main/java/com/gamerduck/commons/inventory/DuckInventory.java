package com.gamerduck.commons.inventory;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class DuckInventory {
	final NamespacedKey key;
	final HashMap<UUID, DuckButton> buttons;
	final Plugin plugin;
	Inventory inv;
	public DuckInventory(Plugin plugin, int size, String name) {
		this.buttons = new HashMap<UUID, DuckButton>();
		this.plugin = plugin;
		this.key = new NamespacedKey(plugin, "button");
		inv = Bukkit.createInventory(null, size, name);
	}

	public void addItem(ItemStack item) {
		inv.addItem(item);
	}
	public void setItem(int slot, ItemStack item) {
		inv.setItem(slot, item);
	}
	public void addButton(ItemStack item, Consumer<ItemStack> onClick) {
		UUID randomUUID = UUID.randomUUID();
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, randomUUID.toString());
		item.setItemMeta(meta);
		buttons.put(randomUUID, new DuckButton(item, onClick));
		inv.addItem(item);
	}
	public void setButton(int slot, ItemStack item, Consumer<ItemStack> onClick) {
		UUID randomUUID = UUID.randomUUID();
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, randomUUID.toString());
		item.setItemMeta(meta);
		buttons.put(randomUUID, new DuckButton(item, onClick));
		inv.setItem(slot, item);
	}
	
	public void open(Player player) {
		player.openInventory(inv);
		Listener listen = new Listener() {
			public void unregister() {HandlerList.unregisterAll(this);}
			@EventHandler
			public void onClick(InventoryClickEvent e) {
				if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
				if (e.getWhoClicked().getUniqueId() == player.getUniqueId()) {
					e.setCancelled(true);
					if (e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
						buttons.get(
								UUID.fromString(e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING)))
								.onClick().accept(e.getCurrentItem());
					}
				}
			}
			
			@EventHandler
			public void onClose(InventoryCloseEvent e) {
				if (e.getPlayer().getUniqueId() != player.getUniqueId()) return;
				unregister();
			}
		};
		plugin.getServer().getPluginManager().registerEvents(listen, plugin);
	}
}
record DuckButton(ItemStack item, Consumer<ItemStack> onClick) {
	
}

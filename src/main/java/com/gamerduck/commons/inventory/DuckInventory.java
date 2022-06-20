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
	boolean cancelled;
	public DuckInventory(Plugin plugin, int size, String name) {
		this.buttons = new HashMap<UUID, DuckButton>();
		this.plugin = plugin;
		this.key = new NamespacedKey(plugin, "button");
		inv = Bukkit.createInventory(null, size, name);
		this.cancelled = true;
	}

	public DuckInventory shouldClickBeCancelled(boolean bool) {
		cancelled = bool;
		return this;
	}
	
	public DuckInventory fillRow(int row, ItemStack item) {
		if (inv.getSize() < ((row * 9) - 1)) return this;
		else {
			int lastInRow = ((row * 9) - 1);
			int firstInRow = lastInRow - 9;
			for (int i = lastInRow; i > firstInRow; i--) {
				setItem(i, item);
			}
		}
		return this;
	}
	
	public DuckInventory fillColumn(int column, ItemStack item) {
		int bottomRow = inv.getSize() / 9;
		for (int i = 1; i <= bottomRow ; i++) {
			setItem(column - 1, item);
			column += 9;
		}
		return this;
	}
	
	public DuckInventory fillBorder(ItemStack item) {
		fillRow(1, item);
		fillColumn(1, item);
		fillRow(inv.getSize() / 9, item);
		fillColumn(9, item);
		return this;
	}
	
	
	public DuckInventory addItem(ItemStack item) {
		inv.addItem(item);
		return this;
	}
	
	public DuckInventory setItem(int slot, ItemStack item) {
		inv.setItem(slot, item);
		return this;
	}
	
	public DuckInventory addButton(ItemStack item, Consumer<ItemStack> onClick) {
		UUID randomUUID = UUID.randomUUID();
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, randomUUID.toString());
		item.setItemMeta(meta);
		buttons.put(randomUUID, new DuckButton(item, onClick));
		inv.addItem(item);
		return this;
	}
	
	public DuckInventory setButton(int slot, ItemStack item, Consumer<ItemStack> onClick) {
		UUID randomUUID = UUID.randomUUID();
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, randomUUID.toString());
		item.setItemMeta(meta);
		buttons.put(randomUUID, new DuckButton(item, onClick));
		inv.setItem(slot, item);
		return this;
	}
	
	public DuckInventory fillRowWithButtons(int row, ItemStack item, Consumer<ItemStack> onClick) {
		if (inv.getSize() < ((row * 9) - 1)) return this;
		else {
			int lastInRow = ((row * 9) - 1);
			int firstInRow = lastInRow - 9;
			for (int i = lastInRow; i > firstInRow; i--) {
				setButton(i, item, onClick);
			}
		}
		return this;
	}
	
	public DuckInventory fillColumnWithButtons(int column, ItemStack item, Consumer<ItemStack> onClick) {
		int bottomRow = inv.getSize() / 9;
		for (int i = 1; i <= bottomRow ; i++) {
			setButton(column - 1, item, onClick);
			column += 9;
		}
		return this;
	}
	
	public DuckInventory fillBorderWithButtons(ItemStack item, Consumer<ItemStack> onClick) {
		fillRowWithButtons(1, item, onClick);
		fillColumnWithButtons(1, item, onClick);
		fillRowWithButtons(inv.getSize() / 9, item, onClick);
		fillColumnWithButtons(9, item, onClick);
		return this;
	}
	
	public DuckInventory open(Player player) {
		player.openInventory(inv);
		Listener listen = new Listener() {
			public void unregister() {HandlerList.unregisterAll(this);}
			@EventHandler
			public void onClick(InventoryClickEvent e) {
				if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
				if (e.getWhoClicked().getUniqueId() == player.getUniqueId()) {
					e.setCancelled(cancelled);
					if (e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
						buttons.get(
								UUID.fromString(e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING)))
								.onClick.accept(e.getCurrentItem());
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
		return this;
	}
}
class DuckButton {
	public Consumer<ItemStack> onClick;
	public ItemStack item;
	DuckButton(ItemStack item, Consumer<ItemStack> onClick) {
		this.item = item;
		this.onClick = onClick;
	}
}
package com.gamerduck.commons.inventory;

import com.google.common.collect.Maps;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import static org.bukkit.Material.AIR;
import static org.bukkit.persistence.PersistentDataType.STRING;

public class DuckInventory {
	final NamespacedKey key;
	final HashMap<UUID, DuckButton> buttons = Maps.newHashMap();
	private final HashMap<UUID, Player> opened = Maps.newHashMap();
	final Plugin plugin;
	Inventory inventory;
	boolean cancelled;
	private Listener listen;
	private final MiniMessage mm = MiniMessage.miniMessage();
	public DuckInventory(Plugin plugin, int size, String name) {
		this.plugin = plugin;
		this.key = new NamespacedKey(plugin, "button");
		this.inventory = Bukkit.createInventory(null, size, mm.deserialize(name));
		this.cancelled = true;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public DuckInventory shouldClickBeCancelled(boolean bool) {
		cancelled = bool;
		return this;
	}
	
	public void updateAll() throws PlayerDoesNotHaveInventoryOpenException {
		for (Player p : opened.values()) update(p);
	}
	
	public void update(Player p) throws PlayerDoesNotHaveInventoryOpenException {
		Optional<Player> pl = opened.values().stream().filter(temp -> temp.getUniqueId() == p.getUniqueId()).findFirst();
		if (pl.isEmpty() || pl == null) throw new PlayerDoesNotHaveInventoryOpenException();
		else pl.get().updateInventory();
	}
	
	public DuckInventory removeItem(int slot) {
		ItemStack item = inventory.getItem(slot);
		if (item.getItemMeta().getPersistentDataContainer().has(key, STRING)) buttons.remove(UUID.fromString(item.getItemMeta().getPersistentDataContainer().get(key, STRING)));
		inventory.remove(item);
		return this;
	}
	
	public DuckInventory fillRow(int row, ItemStack item) {
		if (inventory.getSize() < ((row * 9) - 1)) return this;
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
		int bottomRow = inventory.getSize() / 9;
		for (int i = 1; i <= bottomRow ; i++) {
			setItem(column - 1, item);
			column += 9;
		}
		return this;
	}
	
	public DuckInventory fillBorder(ItemStack item) {
		fillRow(1, item);
		fillColumn(1, item);
		fillRow(inventory.getSize() / 9, item);
		fillColumn(9, item);
		return this;
	}

	
	public DuckInventory fill(ItemStack item) {
		for (int i = 0; i < inventory.getSize(); i++) {
			setItem(i, item);
		}
		return this;
	}

	public DuckInventory fillAir(ItemStack item) {
		for (int i = 0; i < inventory.getSize(); i++) {
			if (inventory.getItem(i) == null
				|| inventory.getItem(i).getType() == AIR) setItem(i, item);
			else continue;
		}
		return this;
	}
	
	public DuckInventory addItem(ItemStack item) {
		inventory.addItem(item);
		return this;
	}
	
	public DuckInventory setItem(int slot, ItemStack item) {
		inventory.setItem(slot, item);
		return this;
	}
	
	public DuckInventory addButton(ItemStack item, Consumer<InventoryClickEvent> onClick) {
		UUID randomUUID = UUID.randomUUID();
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, STRING, randomUUID.toString());
		item.setItemMeta(meta);
		buttons.put(randomUUID, new DuckButton(item, onClick));
		inventory.addItem(item);
		return this;
	}
	
	public DuckInventory setButton(int slot, ItemStack item, Consumer<InventoryClickEvent> onClick) {
		UUID randomUUID = UUID.randomUUID();
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, STRING, randomUUID.toString());
		item.setItemMeta(meta);
		buttons.put(randomUUID, new DuckButton(item, onClick));
		inventory.setItem(slot, item);
		return this;
	}
	
	public DuckInventory fillRowWithButtons(int row, ItemStack item, Consumer<InventoryClickEvent> onClick) {
		if (inventory.getSize() < ((row * 9) - 1)) return this;
		else {
			int lastInRow = ((row * 9) - 1);
			int firstInRow = lastInRow - 9;
			for (int i = lastInRow; i > firstInRow; i--) {
				setButton(i, item, onClick);
			}
		}
		return this;
	}
	
	public DuckInventory fillColumnWithButtons(int column, ItemStack item, Consumer<InventoryClickEvent> onClick) {
		int bottomRow = inventory.getSize() / 9;
		for (int i = 1; i <= bottomRow ; i++) {
			setButton(column - 1, item, onClick);
			column += 9;
		}
		return this;
	}
	
	public DuckInventory fillBorderWithButtons(ItemStack item, Consumer<InventoryClickEvent> onClick) {
		fillRowWithButtons(1, item, onClick);
		fillColumnWithButtons(1, item, onClick);
		fillRowWithButtons(inventory.getSize() / 9, item, onClick);
		fillColumnWithButtons(9, item, onClick);
		return this;
	}

	public DuckInventory fillAirWithButtons(ItemStack item, Consumer<InventoryClickEvent> onClick) {
		for (int i = 0; i < inventory.getSize(); i++) {
			if (inventory.getItem(i) == null || inventory.getItem(i).getType() == AIR) setButton(i, item, onClick);
			else continue;
		}
		return this;
	}

	public DuckInventory fillWithButtons(ItemStack item, Consumer<InventoryClickEvent> onClick) {
		for (int i = 0; i < inventory.getSize(); i++) {
			setButton(i, item, onClick);
		}
		return this;
	}

	
	public DuckInventory open(Player player) {
		player.openInventory(inventory);
		startListener(player);
		return this;
	}
	
	private void startListener(Player player) {
		if (opened.size() != 0) return;
		opened.put(player.getUniqueId(), player);
		listen = new Listener() {
			public void unregister() {HandlerList.unregisterAll(this);}
			@EventHandler
			public void onClick(InventoryClickEvent e) {
				if (e.getCurrentItem() == null || e.getCurrentItem().getType() == AIR) return;
				if (opened.containsKey(e.getWhoClicked().getUniqueId())) {
					e.setCancelled(cancelled);
					if (e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(key, STRING)) {
						buttons.get(
								UUID.fromString(e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(key, STRING)))
								.onClick().accept(e);
					}
				}
			}
			
			@EventHandler
			public void onClose(InventoryCloseEvent e) {
				if (!opened.containsKey(e.getPlayer().getUniqueId())) return;
				opened.remove(e.getPlayer().getUniqueId());
				if (opened.size() == 0) unregister();
			}
			
			@EventHandler
			public void onClose(PlayerQuitEvent e) {
				if (!opened.containsKey(e.getPlayer().getUniqueId())) return;
				opened.remove(e.getPlayer().getUniqueId());
				if (opened.size() == 0) unregister();
			}
			@EventHandler
			public void onClose(PlayerKickEvent e) {
				if (!opened.containsKey(e.getPlayer().getUniqueId())) return;
				opened.remove(e.getPlayer().getUniqueId());
				if (opened.size() == 0) unregister();
			}
		};
		plugin.getServer().getPluginManager().registerEvents(listen, plugin);
	}
}
record DuckButton(ItemStack item, Consumer<InventoryClickEvent> onClick) {}

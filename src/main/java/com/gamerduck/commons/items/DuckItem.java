package com.gamerduck.commons.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

/**
 * DuckItem is meant to be used in place of ItemStack
 * Class will work the same as ItemStack, just adds a few shortcuts to make life easier
 * 
 * @author GamerDuck123
 *
 */
public class DuckItem extends ItemStack {

	private ItemMeta meta;
	private List<String> lore;

	public DuckItem(Material mat, int amount) {
		super(mat, amount);
		meta = getItemMeta();
		lore = new ArrayList<String>();
	}
	public DuckItem() {
		super(Material.STONE, 1);
		meta = getItemMeta();
		lore = new ArrayList<String>();
	}
	/**
	 * Set the display name of the item
	 * 
	 * @param name
	 * @return the same class with the display name changed
	 */
	public DuckItem withDisplayName(String name) {
		meta.setDisplayName(name);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Set the material of the item
	 * (defaulted to stone if not set)
	 * 
	 * @param material
	 * @return the same class with the material changed
	 */
	public DuckItem withMaterial(Material material) {
		setType(material);
		return this;
	}

	/**
	 * Set the amount of the item
	 * (defaulted to 1 if not set)
	 * 
	 * @param amount
	 * @return the same class with the amount changed
	 */
	public DuckItem withAmount(Integer amount) {
		setAmount(amount);
		return this;
	}

	/**
	 * Set the lore of the item
	 * 
	 * @param lore
	 * @return the same class with the lore changed
	 */
	public DuckItem withLore(List<String> lore) {
		this.lore = lore;
		meta.setLore(this.lore);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Add to the current lore of the item
	 * If there was no previously set lore, it'll start with a new list
	 * 
	 * @param string
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(String string) {
		lore.add(string); 
		meta.setLore(lore);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Add to the current lore of the item
	 * If there was no previously set lore, it'll start with a new list
	 * 
	 * @param strings
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(String... strings) {
		lore.addAll(Arrays.asList(strings)); 
		meta.setLore(lore);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Set the custom model data of the item
	 * 
	 * @param data
	 * @return the same class with the custom model data changed
	 */
	public DuckItem withCustomModelData(Integer data) {
		meta.setCustomModelData(data);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Set the persistent data container (String) of the item
	 * 
	 * @param key
	 * @param object
	 * @return the same class with the persistent data container changed
	 */
	public DuckItem withPersistentDataContainer(NamespacedKey key, String object) {
		meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, object);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Set the persistent data container (Integer) of the item
	 * 
	 * @param key
	 * @param object
	 * @return the same class with the persistent data container changed
	 */
	public DuckItem withPersistentDataContainer(NamespacedKey key, Integer object) {
		meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, object);
		setItemMeta(meta);
		return this;
	}


	/**
	 * Set the persistent data container (Integer Array) of the item
	 * 
	 * @param key
	 * @param object
	 * @return the same class with the persistent data container changed
	 */
	public DuckItem withPersistentDataContainer(NamespacedKey key, int[] object) {
		meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER_ARRAY, object);
		setItemMeta(meta);
		return this;
	}


	/**
	 * Set the persistent data container (Double) of the item
	 * 
	 * @param key
	 * @param object
	 * @return the same class with the persistent data container changed
	 */
	public DuckItem withPersistentDataContainer(NamespacedKey key, Double object) {
		meta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, object);
		setItemMeta(meta);
		return this;
	}


	/**
	 * Set the persistent data container (Float) of the item
	 * 
	 * @param key
	 * @param object
	 * @return the same class with the persistent data container changed
	 */
	public DuckItem withPersistentDataContainer(NamespacedKey key, Float object) {
		meta.getPersistentDataContainer().set(key, PersistentDataType.FLOAT, object);
		setItemMeta(meta);
		return this;
	}


	/**
	 * Set the persistent data container (Long) of the item
	 * 
	 * @param key
	 * @param object
	 * @return the same class with the persistent data container changed
	 */
	public DuckItem withPersistentDataContainer(NamespacedKey key, Long object) {
		meta.getPersistentDataContainer().set(key, PersistentDataType.LONG, object);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Set the persistent data container (Long Array) of the item
	 * 
	 * @param key
	 * @param object
	 * @return the same class with the persistent data container changed
	 */
	public DuckItem withPersistentDataContainer(NamespacedKey key, long[] object) {
		meta.getPersistentDataContainer().set(key, PersistentDataType.LONG_ARRAY, object);
		setItemMeta(meta);
		return this;
	}


	/**
	 * Set the persistent data container (Short) of the item
	 * 
	 * @param key
	 * @param object
	 * @return the same class with the persistent data container changed
	 */
	public DuckItem withPersistentDataContainer(NamespacedKey key, Short object) {
		meta.getPersistentDataContainer().set(key, PersistentDataType.SHORT, object);
		setItemMeta(meta);
		return this;
	}


	/**
	 * Add an enchantment to the item
	 * 
	 * @param enchantment
	 * @param level
	 * @return the same class with the enchants changed
	 */
	public DuckItem withEnchant(Enchantment enchantment, int level) {
		addUnsafeEnchantment(enchantment, level);
		return this;
	}

	/**
	 * Remove an enchantment to the item
	 * 
	 * @param enchantment
	 * @param level
	 * @return the same class with the enchants changed
	 */
	public DuckItem withoutEnchant(Enchantment enchantment) {
		removeEnchantment(enchantment);
		return this;
	}

	/**
	 * Add an (or multiple) item flag(s) to the item 
	 * 
	 * @param flag
	 * @return the same class with the item flags changed
	 */
	public DuckItem withFlags(ItemFlag ...flag) {
		meta.addItemFlags(flag);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Remove an (or multiple) item flag(s) to the item 
	 * 
	 * @param flag
	 * @return the same class with the item flags changed
	 */
	public DuckItem withoutFlags(ItemFlag ...flag) {
		meta.removeItemFlags(flag);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Change the skull meta of the item
	 * 
	 * @deprecated Deprecated as names are a terrible way to get players
	 * @param name
	 * @return the same class with the skull meta changed
	 */
	@Deprecated
	public DuckItem skullOwner(String name) {
		if (!(meta instanceof SkullMeta skullMeta)) return this;
		if (getType() != Material.PLAYER_HEAD) return this;
		skullMeta.setOwnerProfile(Bukkit.getOfflinePlayer(name).getPlayerProfile());
		setItemMeta(skullMeta);
		return this;
	}

	/**
	 * Returns the item
	 * 
	 * @deprecated Deprecated as the item auto updates as things are added
	 * @return the class
	 */
	@Deprecated
	public DuckItem build() {
		return get();
	}

	/**
	 * Returns the item
	 * 
	 * @deprecated Deprecated as the item auto updates as things are added
	 * @return the class
	 */
	@Deprecated
	public DuckItem get() {
		meta.setLore(lore);
		setItemMeta(meta);
		return this;
	}

}

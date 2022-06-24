package com.gamerduck.commons.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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

	private DuckItem(ItemStack item) {
		super(item);
		meta = getItemMeta();
		lore = new ArrayList<String>();
	}
	
	protected DuckItem(Material mat, int amount) {
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
	 * @param name the new displayname of the itemstack
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
	 * @param material the new material of the itemstack
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
	 * @param amount the new amount of the itemstack
	 * @return the same class with the amount changed
	 */
	public DuckItem withAmount(Integer amount) {
		setAmount(amount);
		return this;
	}

	/**
	 * Set the lore of the item
	 * 
	 * @param lore the new lore
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
	 * @param string the string to be added to the lore
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
	 * @param strings the strings to be added to the lore
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(String... strings) {
		lore.addAll(Arrays.asList(strings)); 
		meta.setLore(lore);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Add to the current lore of the item
	 * If there was no previously set lore, it'll start with a new list
	 * 
	 * @param strings the string list to be added to the lore
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(List<String> strings) {
		lore.addAll(strings); 
		meta.setLore(lore);
		setItemMeta(meta);
		return this;
	}
	
	/**
	 * Set the custom model data of the item
	 * 
	 * @param data the custom model data
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
	 * @param key the namespacekey to be added to persistent data
	 * @param object the object for persistent data
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
	 * @param key the namespacekey to be added to persistent data
	 * @param object the object for persistent data
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
	 * @param key the namespacekey to be added to persistent data
	 * @param object the object for persistent data
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
	 * @param key the namespacekey to be added to persistent data
	 * @param object the object for persistent data
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
	 * @param key the namespacekey to be added to persistent data
	 * @param object the object for persistent data
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
	 * @param key the namespacekey to be added to persistent data
	 * @param object the object for persistent data
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
	 * @param key the namespacekey to be added to persistent data
	 * @param object the object for persistent data
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
	 * @param key the namespacekey to be added to persistent data
	 * @param object the object for persistent data
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
	 * @param enchantment the enchantment to be added
	 * @param level the level of the enchant to be added
	 * @return the same class with the enchants changed
	 */
	public DuckItem withEnchant(Enchantment enchantment, int level) {
		addUnsafeEnchantment(enchantment, level);
		return this;
	}

	/**
	 * Remove an enchantment to the item
	 * 
	 * @param enchantment the enchantment to add to the item
	 * @return the same class with the enchants changed
	 */
	public DuckItem withoutEnchant(Enchantment enchantment) {
		removeEnchantment(enchantment);
		return this;
	}

	/**
	 * Add an (or multiple) item flag(s) to the item 
	 * 
	 * @param flag the item flag(s)
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
	 * @param flag the item flag(s)
	 * @return the same class with the item flags changed
	 */
	public DuckItem withoutFlags(ItemFlag ...flag) {
		meta.removeItemFlags(flag);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Add an attribute
	 * 
	 * @param attr The attribute to add
	 * @param attrmodify The attribute's modifier
	 * @return the same class with the attribute added
	 */
	public DuckItem withAttribute(Attribute attr, AttributeModifier attrmodify) {
		meta.addAttributeModifier(attr, attrmodify);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Remove an attribute
	 * 
	 * @param attr The attribute to remove
	 * @param attrmodify The attribute's modifier
	 * @return the same class with the attribute removed
	 */
	public DuckItem withoutAttribute(Attribute attr, AttributeModifier attrmodify) {
		meta.removeAttributeModifier(attr, attrmodify);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Remove an attribute
	 * 
	 * @param attr The attribute to remove
	 * @return the same class with the attribute removed
	 */
	public DuckItem withoutAttribute(Attribute attr) {
		meta.removeAttributeModifier(attr);
		setItemMeta(meta);
		return this;
	}

	/**
	 * Turn a normal ItemStack into a DuckItem
	 *
	 * @param item   The ItemStack to turn into a DuckItem.
	 * @return The new DuckItem class
	 */
	public static DuckItem fromItemStack(ItemStack item) {
		return new DuckItem(item);
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

package com.gamerduck.commons.items;

import com.gamerduck.commons.general.ColorTranslator;
import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.gamerduck.commons.items.DuckItemListener.*;
import static org.bukkit.persistence.PersistentDataType.*;

/**
 * DuckItem is meant to be used in place of ItemStack
 * Class will work the same as ItemStack, just adds a few shortcuts to make life easier
 * 
 * @author GamerDuck123
 *
 */
public class DuckItem extends ItemStack {

	private final MiniMessage mm = MiniMessage.miniMessage();

	private DuckItem(ItemStack item) {
		super(item);
	}
	
	protected DuckItem(Material mat, int amount) {
		super(mat, amount);
	}
	
	public DuckItem() {
		super(Material.STONE, 1);
	}
	/**
	 * Set the display name of the item
	 * 
	 * @param name the new displayname of the itemstack
	 * @return the same class with the display name changed
	 */
	public DuckItem withDisplayName(Component name) {
		editMeta(meta -> meta.displayName(name));
		return this;
	}
	/**
	 * Set the display name of the item
	 *
	 * @param name the new displayname of the itemstack
	 * @return the same class with the display name changed
	 */
	public DuckItem withDisplayName(String name) {
		return withDisplayName(mm.deserialize(name));
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
	public DuckItem withLore(List<Component> lore) {
		editMeta(meta -> meta.lore(lore));
		return this;
	}

	/**
	 * Add to the current lore of the item
	 * If there was no previously set lore, it'll start with a new list
	 * 
	 * @param comp the string to be added to the lore
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(Component comp) {
		editMeta(meta -> {
			if (meta.lore() == null) meta.lore(new ArrayList<>());
			meta.lore().add(comp);
		});
		return this;
	}

	/**
	 * Add to the current lore of the item
	 * If there was no previously set lore, it'll start with a new list
	 * 
	 * @param comp the strings to be added to the lore
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(Component... comp) {
		editMeta(meta -> {
			if (meta.lore() == null) meta.lore(new ArrayList<>());
			meta.lore().addAll(Arrays.asList(comp));
		});
		return this;
	}

	/**
	 * Add to the current lore of the item
	 * If there was no previously set lore, it'll start with a new list
	 * 
	 * @param comps the string list to be added to the lore
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(List<Component> comps) {
		editMeta(meta -> {
			if (meta.lore() == null) meta.lore(new ArrayList<>());
			meta.lore().addAll(comps);
		});
		return this;
	}

	/**
	 * Add to the current lore of the item
	 * If there was no previously set lore, it'll start with a new list
	 *
	 * @param str the string to be added to the lore
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(String str) {
		return addToLore(mm.deserialize(str));
	}

	/**
	 * Add to the current lore of the item
	 * If there was no previously set lore, it'll start with a new list
	 *
	 * @param strs the strings to be added to the lore
	 * @return the same class with the lore changed
	 */
	public DuckItem addToLore(String... strs) {
		List<Component> comps = Lists.newArrayList();
		Arrays.stream(strs).forEach(s -> comps.add(mm.deserialize(s)));
		return addToLore(comps);
	}
	
	/**
	 * Set the custom model data of the item
	 * 
	 * @param data the custom model data
	 * @return the same class with the custom model data changed
	 */
	public DuckItem withCustomModelData(Integer data) {
		editMeta(meta -> meta.setCustomModelData(data));
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
		editMeta(meta -> meta.getPersistentDataContainer().set(key, STRING, object));
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
		editMeta(meta -> meta.getPersistentDataContainer().set(key, INTEGER, object));
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
		editMeta(meta -> meta.getPersistentDataContainer().set(key, INTEGER_ARRAY, object));
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
		editMeta(meta -> meta.getPersistentDataContainer().set(key, DOUBLE, object));
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
		editMeta(meta -> meta.getPersistentDataContainer().set(key, FLOAT, object));
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
		editMeta(meta -> meta.getPersistentDataContainer().set(key, LONG, object));
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
		editMeta(meta -> meta.getPersistentDataContainer().set(key, LONG_ARRAY, object));
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
		editMeta(meta -> meta.getPersistentDataContainer().set(key, SHORT, object));
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
	public DuckItem withFlags(ItemFlag... flag) {
		editMeta(meta -> meta.addItemFlags(flag));
		return this;
	}

	/**
	 * Remove an (or multiple) item flag(s) to the item 
	 * 
	 * @param flag the item flag(s)
	 * @return the same class with the item flags changed
	 */
	public DuckItem withoutFlags(ItemFlag ...flag) {
		this.editMeta(meta -> meta.removeItemFlags(flag));
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
		this.editMeta(meta -> meta.addAttributeModifier(attr, attrmodify));
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
		this.editMeta(meta -> meta.removeAttributeModifier(attr, attrmodify));
		return this;
	}

	/**
	 * Remove an attribute
	 * 
	 * @param attr The attribute to remove
	 * @return the same class with the attribute removed
	 */
	public DuckItem withoutAttribute(Attribute attr) {
		this.editMeta(meta -> meta.removeAttributeModifier(attr));
		return this;
	}


	/**
	 * Fires whenever the PlayerInteractEvent fires for this item
	 *
	 * @param identifier The UNIQUE Identifier for the event to listen for.
	 * @param eventConsumer A Consumer that contains the information you want to use with the PlayerInteractEvent
	 * @return The new DuckItem class
	 */
	public DuckItem onClick(String identifier, Consumer<PlayerInteractEvent> eventConsumer) {
		if (plugin == null) return this;
		if (key == null) return this;
		withPersistentDataContainer(key, identifier);
		itemList.put(identifier, eventConsumer);
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
		return this;
	}

}
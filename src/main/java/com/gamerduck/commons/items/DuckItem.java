package com.gamerduck.commons.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.translation.Translatable;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.gamerduck.commons.general.Components.translate;
import static com.gamerduck.commons.items.DuckItemListener.*;
import static org.bukkit.persistence.PersistentDataType.*;

/**
 * DuckItem is meant to be used in place of ItemStack
 * Class will work the same as ItemStack, just adds a few shortcuts to make life easier
 *
 * @author GamerDuck123
 */
public class DuckItem extends ItemStack implements Cloneable,
        ConfigurationSerializable, HoverEventSource<HoverEvent.ShowItem>, Translatable {

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
     * Turn a normal ItemStack into a DuckItem
     *
     * @param item The ItemStack to turn into a DuckItem.
     * @return The new DuckItem class
     */
    public static DuckItem itemStack(ItemStack item) {
        return new DuckItem(item);
    }

    /**
     * Turn a base64 string into a DuckItem
     *
     * @param base64 The base64 string
     * @return The DuckItem from a base64 string
     */
    public static DuckItem base64(String base64) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(base64));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            DuckItem items = (DuckItem) dataInput.readObject();
            dataInput.close();
            return items;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the display name of the item
     *
     * @return the displayname of the item
     */
    public Component displayName() {
        return this.getItemMeta().displayName();
    }

    /**
     * Set the display name of the item
     *
     * @param name the new displayname of the itemstack
     * @return the same class with the display name changed
     */
    public DuckItem displayName(Component name) {
        editMeta(meta -> meta.displayName(name));
        return this;
    }

    /**
     * Set the display name of the item
     *
     * @param name the new displayname of the itemstack
     * @return the same class with the display name changed
     */
    public DuckItem displayName(String name) {
        return displayName(translate(name));
    }

    /**
     * Get the material of the item
     * (defaulted to stone if not set)
     *
     * @return the material type of this item
     */
    public Material material() {
        return this.getType();
    }

    /**
     * Set the material of the item
     * (defaulted to stone if not set)
     *
     * @param material the new material of the itemstack
     * @return the same class with the material changed
     */
    public DuckItem material(Material material) {
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
    public DuckItem amount(Integer amount) {
        setAmount(amount);
        return this;
    }

    /**
     * Get the lore of the item
     *
     * @return list of components for the lore
     */
    public List<Component> lore() {
        return this.getItemMeta().lore();
    }

    /**
     * Set the lore of the item
     *
     * @param lore the new lore
     * @return the same class with the lore changed
     */
    public DuckItem setlore(List<Component> lore) {
        if (lore == null) return this;
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
        if (comp == null) return this;
        editMeta(meta -> {
            List<Component> comps = meta.lore() == null ? new ArrayList<>() : meta.lore();
            comps.add(comp);
            meta.lore(comps);
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
        if (comp == null) return this;
        editMeta(meta -> {
            List<Component> comps = meta.lore() == null ? new ArrayList<>() : meta.lore();
            comps.addAll(Arrays.asList(comp));
            meta.lore(comps);
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
        if (comps == null) return this;
        editMeta(meta -> {
            List<Component> list = meta.lore() == null ? new ArrayList<>() : meta.lore();
            list.addAll(comps);
            meta.lore(list);
        });
        return this;
    }

    /**
     * @param str the string to be added to the lore
     * @return the same class with the lore changed
     * @Deprecated Use addToLore(Component comp);
     * Add to the current lore of the item
     * If there was no previously set lore, it'll start with a new list
     */
    @Deprecated(forRemoval = true, since = "Components were added")
    public DuckItem addToLore(String str) {
        return addToLore(translate(str));
    }

    /**
     * @param strs the strings to be added to the lore
     * @return the same class with the lore changed
     * @Deprecated Use addToLore(Component... comps);
     * Add to the current lore of the item
     * If there was no previously set lore, it'll start with a new list
     */
    @Deprecated(forRemoval = true, since = "Components were added")
    public DuckItem addToLore(String... strs) {
        return addToLore(Arrays.stream(strs).map(s -> translate(s)).collect(Collectors.toList()));
    }

    /**
     * Get the custom model data
     *
     * @return the custom model data as an integer
     */
    public Integer customModelData() {
        return this.getItemMeta().getCustomModelData();
    }

    /**
     * Set the custom model data of the item
     *
     * @param data the custom model data
     * @return the same class with the custom model data changed
     */
    public DuckItem customModelData(Integer data) {
        editMeta(meta -> meta.setCustomModelData(data));
        return this;
    }

    /**
     * Get the persistent data container of the item
     *
     * @return the corresponding persistent data container
     */
    public PersistentDataContainer persistentDataContainer() {
        return this.getItemMeta().getPersistentDataContainer();
    }

    /**
     * Get the persistent data container of the item with the key and type
     *
     * @return the corresponding persistent data
     */
    public Object persistentDataContainer(NamespacedKey key, PersistentDataType type) {
        return this.getItemMeta().getPersistentDataContainer().get(key, type);
    }

    /**
     * Set the persistent data container (String) of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, String object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, STRING, object));
        return this;
    }

    /**
     * Set the persistent data container (Integer) of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, Integer object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, INTEGER, object));
        return this;
    }

    /**
     * Set the persistent data container (Integer Array) of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, int[] object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, INTEGER_ARRAY, object));
        return this;
    }

    /**
     * Set the persistent data container (Double) of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, Double object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, DOUBLE, object));
        return this;
    }

    /**
     * Set the persistent data container (Float) of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, Float object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, FLOAT, object));
        return this;
    }

    /**
     * Set the persistent data container (Long) of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, Long object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, LONG, object));
        return this;
    }

    /**
     * Set the persistent data container (Long Array) of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, long[] object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, LONG_ARRAY, object));
        return this;
    }

    /**
     * Set the persistent data container (Short) of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, Short object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, SHORT, object));
        return this;
    }

    /**
     * Set the persistent data container of the item
     *
     * @param key    the namespacekey to be added to persistent data
     * @param type   the type of persistent data
     * @param object the object for persistent data
     * @return the same class with the persistent data container changed
     */
    public DuckItem persistentDataContainer(NamespacedKey key, PersistentDataType type, Object object) {
        editMeta(meta -> meta.getPersistentDataContainer().set(key, type, object));
        return this;
    }

    /**
     * Get the level of an enchantment on this item
     *
     * @return the corresponding level of the enchantment
     */
    public Integer enchant(Enchantment enchantment) {
        return this.getEnchantmentLevel(enchantment);
    }

    /**
     * Add an enchantment to the item
     *
     * @param enchantment the enchantment to be added
     * @param level       the level of the enchant to be added
     * @return the same class with the enchants changed
     */
    public DuckItem enchant(Enchantment enchantment, int level) {
        if (enchantment == null) return this;
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
        if (enchantment == null) return this;
        removeEnchantment(enchantment);
        return this;
    }

    /**
     * Get a set of ItemFlags for this item
     *
     * @return the set of itemflags for this item
     */
    public Set<ItemFlag> flags() {
        return this.getItemMeta().getItemFlags();
    }

    /**
     * Add an (or multiple) item flag(s) to the item
     *
     * @param flag the item flag(s)
     * @return the same class with the item flags changed
     */
    public DuckItem flags(ItemFlag... flag) {
        editMeta(meta -> meta.addItemFlags(flag));
        return this;
    }

    /**
     * Remove an (or multiple) item flag(s) to the item
     *
     * @param flag the item flag(s)
     * @return the same class with the item flags changed
     */
    public DuckItem withoutFlags(ItemFlag... flag) {
        this.editMeta(meta -> meta.removeItemFlags(flag));
        return this;
    }

    /**
     * Get the attribute modifiers for an attribute
     *
     * @param attr The attribute to check
     * @return the collection of attribute modifiers for this attribute
     */
    public Collection<AttributeModifier> attribute(Attribute attr) {
        return this.getItemMeta().getAttributeModifiers(attr);
    }

    /**
     * Add an attribute
     *
     * @param attr       The attribute to add
     * @param attrmodify The attribute's modifier
     * @return the same class with the attribute added
     */
    public DuckItem attribute(Attribute attr, AttributeModifier attrmodify) {
        this.editMeta(meta -> meta.addAttributeModifier(attr, attrmodify));
        return this;
    }

    /**
     * Remove an attribute
     *
     * @param attr       The attribute to remove
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
     * @param identifier    The UNIQUE Identifier for the event to listen for.
     * @param eventConsumer A Consumer that contains the information you want to use with the PlayerInteractEvent
     * @return The new DuckItem class
     */
    public DuckItem onClick(String identifier, Consumer<PlayerInteractEvent> eventConsumer) {
        if (plugin == null) return this;
        if (key == null) return this;
        persistentDataContainer(key, identifier);
        itemList.put(identifier, eventConsumer);
        return this;
    }

    /**
     * Turn a DuckItem into a base64 string
     *
     * @return The DuckItem as a base64 string
     */
    public String base64() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(this);
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

}
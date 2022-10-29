package com.gamerduck.commons.events.extra;

import com.gamerduck.commons.events.DuckPlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Material.AIR;

public class PlayerHandChangeEvent extends DuckPlayerEvent {

    public static void enable(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new PlayerHandChangeEventHandler(), plugin);
    }

    public Integer newSlot;
    public ItemStack newItem;
    public Integer oldSlot;
    public ItemStack oldItem;

    public PlayerHandChangeEvent(Player who, ItemStack newItem, Integer newSlot, ItemStack oldItem, Integer oldSlot) {
        super(who);
        this.newSlot = newSlot;
        this.newItem = newItem;
        this.oldSlot = oldSlot;
        this.oldItem = oldItem;
    }

    public Integer getNewSlot() {
        return newSlot;
    }

    public ItemStack getNewItem() {
        return newItem;
    }

    public Integer getOldSlot() {
        return oldSlot;
    }

    public ItemStack getOldItem() {
        return oldItem;
    }

    private static HandlerList HANDLERS = new HandlerList();
    public static HandlerList getHandlerList() {return HANDLERS;}
    public HandlerList getHandlers() {return HANDLERS;}


    private boolean cancelled = false;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
class PlayerHandChangeEventHandler implements Listener {


    @EventHandler
    public void handChangeHandler(PlayerItemHeldEvent e) {
        if (e.isCancelled()) return;
        Bukkit.getPluginManager().callEvent(new PlayerHandChangeEvent(e.getPlayer(), e.getPlayer().getInventory().getItem(e.getNewSlot()), e.getNewSlot(), e.getPlayer().getInventory().getItem(e.getPreviousSlot()), e.getPreviousSlot()));
    }

    @EventHandler
    public void inventoryMoveHandler(InventoryClickEvent e) {
        if (e.isCancelled()) return;
        Player p = (Player) e.getWhoClicked();
        if (e.getClick() == ClickType.NUMBER_KEY)
            Bukkit.getPluginManager().callEvent(
                    new PlayerHandChangeEvent(p, e.getCurrentItem(), e.getHotbarButton(), p.getInventory().getItemInMainHand(), e.getSlot()));
        else if (e.getSlot() == e.getWhoClicked().getInventory().getHeldItemSlot()) Bukkit.getPluginManager().callEvent(
                new PlayerHandChangeEvent(p, e.getCursor(), e.getSlot(), e.getCurrentItem(), e.getSlot()));
    }

    @EventHandler
    public void itemDropHandler(PlayerDropItemEvent e) {
        if (e.isCancelled()) return;
        Bukkit.getPluginManager().callEvent(
                new PlayerHandChangeEvent(e.getPlayer(), new ItemStack(AIR), e.getPlayer().getInventory().getHeldItemSlot(), e.getItemDrop().getItemStack(), e.getPlayer().getInventory().getHeldItemSlot()));
    }

    @EventHandler
    public void handSwapHandler(PlayerSwapHandItemsEvent e) {
        if (e.isCancelled()) return;
        Bukkit.getPluginManager().callEvent(
                new PlayerHandChangeEvent(e.getPlayer(), e.getMainHandItem(), e.getPlayer().getInventory().getHeldItemSlot(), e.getOffHandItem(), e.getPlayer().getInventory().getHeldItemSlot()));
    }

}
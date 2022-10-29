package com.gamerduck.commons.events.extra;

import com.gamerduck.commons.events.DuckPlayerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent.SlotType;

public class PlayerArmorChangeEvent extends DuckPlayerEvent {

    public ItemStack newItem;
    public SlotType slotType;
    public ItemStack oldItem;

    public PlayerArmorChangeEvent(Player who, ItemStack newItem, ItemStack oldItem, SlotType slotType) {
        super(who);
        this.slotType = slotType;
        this.newItem = newItem;
        this.oldItem = oldItem;
    }


    public ItemStack getNewItem() {
        return newItem;
    }

    public SlotType getSlotType() {
        return slotType;
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

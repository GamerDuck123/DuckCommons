package com.gamerduck.commons.events.extra;

import com.gamerduck.commons.events.DuckPlayerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerHandChangeEvent extends DuckPlayerEvent {

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
}

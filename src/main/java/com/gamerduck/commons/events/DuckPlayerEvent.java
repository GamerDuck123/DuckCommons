package com.gamerduck.commons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import lombok.Getter;

/**
 * This class replaces Event, and is aimed towards player based events
 * 
 * @author GamerDuck123
 *
 */
public abstract class DuckPlayerEvent extends DuckEvent implements Cancellable {
	@Getter Player player;
	public DuckPlayerEvent(Player player) {
		this.player = player;
		HANDLERS = new HandlerList();
	}

	private static HandlerList HANDLERS;
    public static HandlerList getHandlerList() {return HANDLERS;}
    public HandlerList getHandlers() {return HANDLERS;}

}

package com.gamerduck.commons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * This class replaces Event, and is aimed towards player based events
 * 
 * @author GamerDuck123
 *
 */
public abstract class DuckPlayerEvent extends DuckEvent implements Cancellable {
	Player player;
	public DuckPlayerEvent(Player player) {
		this.player = player;
		HANDLERS = new HandlerList();
	}

	public Player getPlayer() {
		return player;
	}

	private static HandlerList HANDLERS;
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

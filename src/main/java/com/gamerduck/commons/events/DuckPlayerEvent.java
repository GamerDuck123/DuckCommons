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
	}

	public Player getPlayer() {
		return player;
	}

}

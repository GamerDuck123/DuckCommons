package com.gamerduck.commons.events;

import org.bukkit.Server;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
/**
 * This class replaces Event, and is aimed towards server based events
 * 
 * @author GamerDuck123
 *
 */
public abstract class DuckServerEvent extends DuckEvent implements Cancellable {
	Server server;
	public DuckServerEvent(Server server) {
		this.server = server;
		HANDLERS = new HandlerList();
	}

	public Server getServer() {
		return server;
	}

	private static HandlerList HANDLERS;
    public static HandlerList getHandlerList() {return HANDLERS;}
    public HandlerList getHandlers() {return HANDLERS;}

}

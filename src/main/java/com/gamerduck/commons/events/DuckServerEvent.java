package com.gamerduck.commons.events;

import org.bukkit.Server;
import org.bukkit.event.Cancellable;

/**
 * This class replaces Event, and is aimed towards server based events
 *
 * @author GamerDuck123
 */
public abstract class DuckServerEvent extends DuckEvent implements Cancellable {
    private final Server server;

    public DuckServerEvent(Server server) {
        super(false);
        this.server = server;
    }

    public DuckServerEvent(Server server, boolean isAsync) {
        super(isAsync);
        this.server = server;
    }

    public Server server() {
        return server;
    }


}

package com.gamerduck.commons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * This class replaces Event, and is aimed towards player based events
 *
 * @author GamerDuck123
 */
public abstract class DuckPlayerEvent extends DuckEvent implements Cancellable {
    private final Player player;

    public DuckPlayerEvent(Player player) {
        super(false);
        this.player = player;
    }

    public DuckPlayerEvent(Player player, boolean isAsync) {
        super(isAsync);
        this.player = player;
    }

    public Player player() {
        return player;
    }

}

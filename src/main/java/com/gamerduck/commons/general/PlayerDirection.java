package com.gamerduck.commons.general;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public enum PlayerDirection {
    NORTH, SOUTH, EAST, WEST, UP, DOWN;
    public static PlayerDirection getFacingDirection(Player player) {
        Vector dir = player.getLocation().getDirection().normalize();
        return Math.abs(dir.getY()) > 0.5D ? ((dir.getY() > 0.0D) ? UP : DOWN) :
                (Math.abs(dir.getX()) > 0.5D) ? ((dir.getX() > 0.0D) ? EAST : WEST) : ((dir.getZ() > 0.0D) ? SOUTH : NORTH);
    }
}

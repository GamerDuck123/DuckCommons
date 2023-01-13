package com.gamerduck.commons.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;


/**
 * DO NOT USE, THIS IS JUST AN INTERFACE FOR THE HANDLER
 * USE DuckCommand.class OR DuckCommandWithArgs.class
 *
 * @author GamerDuck123
 */
public interface IDuckCommand extends CommandExecutor, TabExecutor {
    public void register(String fallbackprefix);
}

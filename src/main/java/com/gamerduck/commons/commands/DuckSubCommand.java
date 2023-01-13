package com.gamerduck.commons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class DuckSubCommand {

    public abstract String permission();
    public abstract String permissionMessage();

    public abstract boolean run(CommandSender sender, Command cmd, String label, String[] args);

    public abstract List<String> tab(CommandSender sender, Command cmd, String label, String[] args);

}
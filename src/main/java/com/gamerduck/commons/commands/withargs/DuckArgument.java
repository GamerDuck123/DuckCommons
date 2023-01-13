package com.gamerduck.commons.commands.withargs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class DuckArgument {

    public abstract String permission();

    public abstract boolean run(CommandSender sender, Command cmd, String label, String[] args);

    public abstract List<String> tab(CommandSender sender, Command cmd, String label, String[] args);

}

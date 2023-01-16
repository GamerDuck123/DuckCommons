package com.gamerduck.commons.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class DuckSubCommand {

    public String permission() {return null;}

    public Component permissionMessage() {return Component.empty();}

    public abstract boolean run(CommandSender sender, Command cmd, String label, String[] args);

    public List<String> tab(CommandSender sender, Command cmd, String label, String[] args) {return null;}

}

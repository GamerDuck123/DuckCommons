package com.gamerduck.commons.commands;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
/**
 * AbstractDuckCommand is meant to be used in place of CommandExecutor and TabExecutor
 * This class acts a way for DuckCommandHandler to figure out what to register
 * 
 * @author GamerDuck123
 *
 */
public abstract class AbstractDuckCommand implements CommandExecutor, TabExecutor {

    protected static CommandMap cmap;
    
    public void register(String command, String usage, String description, String permissionMessage, List<String> aliases, String fallbackprefix) {
        ReflectCommand cmd = new ReflectCommand(command);
        if (aliases != null) cmd.setAliases(aliases);
        if (description != null) cmd.setDescription(description);
        if (usage != null) cmd.setUsage(usage);
        if (permissionMessage != null) cmd.setPermissionMessage(permissionMessage);
        getCommandMap().register(fallbackprefix, cmd);
        cmd.setExecutor(this);
    }
    
    final CommandMap getCommandMap() {
        if (cmap == null) {
            try {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            } catch (Exception e) { e.printStackTrace(); }
        } else if (cmap != null) { return cmap; }
        return getCommandMap();
    }
    
private final class ReflectCommand extends Command {
    private AbstractDuckCommand exe = null;
    protected ReflectCommand(String command) { super(command); }
    public void setExecutor(AbstractDuckCommand exe) { this.exe = exe; }
    @Override public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (exe != null) { return exe.onCommand(sender, this, commandLabel, args); }
        return false;
    }
       
    @Override public List<String> tabComplete(CommandSender sender, String alais, String[] args) {
        if (exe != null) { return exe.onTabComplete(sender, this, alais, args); }
        return null;
      }
    }
    
    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);
    
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }
}

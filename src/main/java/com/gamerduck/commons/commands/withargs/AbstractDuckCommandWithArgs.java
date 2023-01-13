package com.gamerduck.commons.commands.withargs;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.permissionMessage;

/**
 * AbstractDuckCommand is meant to be used in place of CommandExecutor and TabExecutor
 * This class acts a way for DuckCommandHandler to figure out what to register
 *
 * @author GamerDuck123
 */
public abstract class AbstractDuckCommandWithArgs implements CommandExecutor, TabExecutor {

    protected static CommandMap cmap;
    public abstract String command();

    public abstract Map<String, DuckArgument> arguments();

    public abstract List<String> aliases();

    public abstract String description();

    public abstract String usage();

    public abstract String permissions();

    public abstract String permissionMessage();

    public void register(String fallbackprefix) {
        ReflectCommand cmd = new ReflectCommand(command(), arguments());
        if (aliases() != null) cmd.setAliases(aliases());
        if (description() != null) cmd.setDescription(description());
        if (usage() != null) cmd.setUsage(usage());
        if (permissionMessage() != null) cmd.setPermissionMessage(permissionMessage());
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (cmap != null) {
            return cmap;
        }
        return getCommandMap();
    }

    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }

    private final class ReflectCommand extends Command {
        private AbstractDuckCommandWithArgs exe = null;
        private Map<String, DuckArgument> arguments;

        protected ReflectCommand(String command, Map<String, DuckArgument> arguments) {
            super(command);
            this.arguments = arguments;
        }

        public void setExecutor(AbstractDuckCommandWithArgs exe) {
            this.exe = exe;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                if (args != null && args.length > 0 && args[0] != null && !args[0].isEmpty()) {
                    if (!arguments.containsKey(args[0])) {
                        sender.sendMessage(usage());
                        return false;
                    } else {
                        DuckArgument argument = arguments.get(args[0]);
                        if (!sender.hasPermission(argument.permission())) {
                            sender.sendMessage(permissionMessage());
                            return false;
                        } else return arguments.get(args[0]).run(sender, this, commandLabel, args);
                    }
                } else {
                    if (!sender.hasPermission(permissions())) {
                        sender.sendMessage(permissionMessage());
                        return false;
                    } else return exe.onCommand(sender, this, commandLabel, args);
                }
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
//                if (args != null && args.length > 0 && args[0] != null && !args[0].isEmpty()) arguments.get(args[0]).run(sender, this, commandLabel, args);
//                else return exe.onTabComplete(sender, this, commandLabel, args);
                if (args != null && args.length > 0 && args[0] != null && !args[0].isEmpty()) {
                    if (!arguments.containsKey(args[0])) {
                        return null;
                    } else return arguments.get(args[0]).tab(sender, this, commandLabel, args);
                } else {
                    if (!sender.hasPermission(permissions())) {
                        return null;
                    } else return exe.onTabComplete(sender, this, commandLabel, args);
                }
            }
            return null;
        }
    }
}
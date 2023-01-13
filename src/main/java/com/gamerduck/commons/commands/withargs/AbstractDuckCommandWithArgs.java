package com.gamerduck.commons.commands.withargs;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * AbstractDuckCommand is meant to be used in place of CommandExecutor and TabExecutor
 * This class acts a way for DuckCommandHandler to figure out what to register
 *
 * @author GamerDuck123
 */
public abstract class AbstractDuckCommandWithArgs implements CommandExecutor, TabExecutor {

    protected static CommandMap cmap;

    public void register(String command, Class<? extends DuckArgument>[] arguments, String usage, String description, String permissionMessage, List<String> aliases, String fallbackprefix) {
        HashMap<String, DuckArgument> tempMap = Maps.newHashMap();
        Arrays.stream(arguments).forEach(arg -> {
            DuckArgument newArg = null;
            try {
                newArg = arg.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                     | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
            tempMap.put(newArg.argument(), newArg);
        });
        ReflectCommand cmd = new ReflectCommand(command, tempMap);
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
        HashMap<String, DuckArgument> arguments;

        protected ReflectCommand(String command, HashMap<String, DuckArgument> arguments) {
            super(command);
            this.arguments = arguments;
        }

        public void setExecutor(AbstractDuckCommandWithArgs exe) {
            this.exe = exe;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                if (args != null) arguments.get(args[0]).run(sender, this, commandLabel, args);
                else return exe.onCommand(sender, this, commandLabel, args);
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                if (args != null) arguments.get(args[0]).run(sender, this, commandLabel, args);
                else return exe.onTabComplete(sender, this, commandLabel, args);
            }
            return null;
        }
    }
}

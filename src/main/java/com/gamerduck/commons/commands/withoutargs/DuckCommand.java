package com.gamerduck.commons.commands.withoutargs;

import com.gamerduck.commons.commands.IDuckCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.lang.reflect.Field;
import java.util.List;

/**
 * AbstractDuckCommand is meant to be used in place of CommandExecutor and TabExecutor
 * This class acts a way for DuckCommandHandler to figure out what to register
 *
 * @author GamerDuck123
 */
public abstract class DuckCommand implements IDuckCommand {
    protected static CommandMap cmap;

    public abstract String command();

    public abstract List<String> aliases();

    public abstract String description();

    public abstract String usage();

    public abstract String permissions();

    public abstract String permissionMessage();

    public void register(String fallbackprefix) {
        ReflectCommand cmd = new ReflectCommand(command());
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
        private DuckCommand exe = null;

        protected ReflectCommand(String command) {
            super(command);
        }

        public void setExecutor(DuckCommand exe) {
            this.exe = exe;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                if (!sender.hasPermission(permissions())) {
                    sender.sendMessage(permissionMessage());
                    return false;
                } else return exe.onCommand(sender, this, commandLabel, args);
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                if (!sender.hasPermission(permissions())) {
                    return null;
                } else return exe.onTabComplete(sender, this, commandLabel, args);
            }
            return null;
        }
    }
}

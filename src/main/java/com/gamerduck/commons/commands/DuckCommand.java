package com.gamerduck.commons.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

/**
 * AbstractDuckCommand is meant to be used in place of CommandExecutor and TabExecutor
 * This class acts a way for DuckCommandHandler to figure out what to register
 *
 * @author GamerDuck123
 */
public abstract class DuckCommand implements CommandExecutor, TabExecutor {

    protected static CommandMap cmap;

    public abstract String command();

    public Map<String, DuckSubCommand> arguments() {
        return null;
    }

    public List<String> aliases() {
        return null;
    }

    public String description() {
        return "A command";
    }

    public Component usage() {
        return miniMessage().deserialize("/" + command());
    }

    public String permission() {
        return null;
    }

    public Component permissionMessage() {return Component.empty();}

    public String fallbackPrefix() {return "";}

    public DuckCommand() {
        ReflectCommand cmd = new ReflectCommand(command(), arguments());
        if (aliases() != null) cmd.setAliases(aliases());
        if (description() != null) cmd.setDescription(description());
        if (usage() != null) cmd.setUsage(usage());
        if (permissionMessage() != null) cmd.permissionMessage(permissionMessage());
        if (permission() != null) cmd.setPermission(permission());
        Bukkit.getServer().getCommandMap().register(fallbackPrefix(), cmd);
        cmd.setExecutor(this);
    }

//    public void register(String fallbackPrefix) {
//        ReflectCommand cmd = new ReflectCommand(command(), arguments());
//        if (aliases() != null) cmd.setAliases(aliases());
//        if (description() != null) cmd.setDescription(description());
//        if (usage() != null) cmd.setUsage(usage());
//        if (permissionMessage() != null) cmd.permissionMessage(permissionMessage());
//        if (permission() != null) cmd.setPermission(permission());
//        Bukkit.getServer().getCommandMap().register(fallbackPrefix, cmd);
//        cmd.setExecutor(this);
//    }

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
        private Map<String, DuckSubCommand> arguments;

        private Component usage;

        protected ReflectCommand(String command, Map<String, DuckSubCommand> arguments) {
            super(command);
            this.arguments = arguments;
        }

        public void setUsage(Component component) {
            this.usage = component;
        }


        public void setExecutor(DuckCommand exe) {
            this.exe = exe;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                if (arguments != null && args != null && args.length > 0 && args[0] != null && !args[0].isEmpty()) {
                    if (!arguments.containsKey(args[0])) {
                        sender.sendMessage(usage);
                        return false;
                    } else {
                        DuckSubCommand argument = arguments.get(args[0]);
                        if (!sender.hasPermission(argument.permission())) {
                            sender.sendMessage(argument.permissionMessage());
                            return false;
                        } else return argument.run(sender, this, commandLabel, args);
                    }
                } else return testPermission(sender) ? exe.onCommand(sender, this, commandLabel, args) : false;
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                if (arguments != null && args != null && args.length > 1 && args[0] != null && !args[0].isEmpty()) {
                    if (!arguments.containsKey(args[0])) {
                        return exe.onTabComplete(sender, this, commandLabel, args);
                    } else return sender.hasPermission(arguments.get(args[0]).permission()) ?
                                arguments.get(args[0]).tab(sender, this, commandLabel, args) : null;
                } else return testPermissionSilent(sender) ?
                            exe.onTabComplete(sender, this, commandLabel, args) : null;
            }
            return null;
        }
    }
}

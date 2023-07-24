package com.gamerduck.commons.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.util.List;
import java.util.Map;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

/**
 * AbstractDuckCommand is meant to be used in place of CommandExecutor and TabExecutor
 * This class acts a way for DuckCommandHandler to figure out what to register
 *
 * @author GamerDuck123
 */
public abstract class DuckCommand {

    protected static CommandMap cmap;

    /**
     * String for the command
     *
     * @return
     */
    public abstract String command();


    /**
     * Map for all of the separate class arguments
     *
     * @return
     */
    public Map<String, DuckSubCommand> arguments() {
        return null;
    }

    /**
     * A list of the aliases for this command
     *
     * @return
     */
    public List<String> aliases() {
        return null;
    }

    /**
     * The description of the command, helpful for auto generated /help commands
     *
     * @return The description
     */
    public String description() {
        return "A command";
    }

    /**
     * Kinda useless outside of the class as it makes the player see the command as non-existent
     *
     * @return The message if the command is imporperly used
     */
    public Component usage() {
        return miniMessage().deserialize("/" + command());
    }

    /**
     * The permission for this command
     * if the player doesn't have this permission the command won't show for them.
     *
     * @return The permission string
     */
    public String permission() {
        return null;
    }

    /**
     * Kinda useless as it makes the player see the command as non-existent
     *
     * @return The permission message for the command
     */
    public Component permissionMessage() {return Component.empty();}

    /**
     * If this is set then the command can be used as /fallbackPrefix:command
     * otherwise the only option will be /command
     *
     * @return
     */
    public String fallbackPrefix() {return "";}

    public DuckCommand() {
        ReflectCommand cmd = new ReflectCommand(command(), arguments());
        if (aliases() != null) cmd.setAliases(aliases());
        if (description() != null) cmd.setDescription(description());
        if (usage() != null) cmd.setUsage(usage());
        if (permissionMessage() != null) cmd.permissionMessage(permissionMessage());
        if (permission() != null) cmd.setPermission(permission());
        cmd.setExecutor(this);
        Bukkit.getServer().getCommandMap().register(fallbackPrefix(), cmd);
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

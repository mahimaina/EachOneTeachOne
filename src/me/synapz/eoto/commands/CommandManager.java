package me.synapz.eoto.commands;

import static org.bukkit.ChatColor.*;

import me.synapz.eoto.commands.executors.*;
import me.synapz.eoto.commands.lesson.*;
import me.synapz.eoto.commands.lesson.Set;
import me.synapz.eoto.utils.Messenger;
import me.synapz.eoto.utils.Utils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class CommandManager implements CommandExecutor {


    private static Map<String, EOTOCommand> commands = new HashMap<>();
    private static CommandManager manager;

    private CommandManager() {}

    public static CommandManager getManager() {
        if (manager == null)
            manager = new CommandManager();

        return manager;
    }

    public void init() {
        addCommands(new Help(), new Fly(), new NoFly(), new Exp(), new Feed(), new Freeze(), new Gamemode(), new Heal(), new Kill(),
                new Msg(), new Tp(), new Unfreeze(), new End(), new Ext(), new Set(), new Spawn());
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
        if (args.length == 0) {
            Utils.showHelp(sender);
            return true;
        }

        EOTOCommand command = commands.get(args[0]);

        if (command == null) {
            Messenger.error(sender, "Unknown command.", "Type /eoto help for a list of commands!");
            return true;
        }

        try {
            if (commandChecks(command, sender, args.length)) {
                command.onCommand(sender, args);
            }
        }catch (Exception e) {
            sender.sendMessage(RED + "An internal error has occurred. Check console for an error log.");
            e.printStackTrace();
        }

        return true;
    }

    private boolean commandChecks(EOTOCommand command, CommandSender sender, int argCount) {
        if (!(sender instanceof Player) && isCorrectArgs(sender, command, argCount)) {
            return true;
        }
        if (sender instanceof Player && isCorrectArgs(sender, command, argCount) && hasPerm(sender, command)) {
            return true;
        }
        return false;
    }

    /**
     * Checks to make sure a CommandSender has access to the command
     * @param sender Sender of command
     * @param command Command to check for permission to
     * @return if the CommandSender has permission to that argument and permission
     */
    private boolean hasPerm(CommandSender sender, EOTOCommand command) {
        if (sender.hasPermission(command.getPermission())) {
            return true;
        } else {
            sender.sendMessage(DARK_RED + "You don't have access to that command!");
            return false;
        }
    }

    /**
     * Checks to make sure the arguments for the current command are correct
     * @param sender CommandSender to send error message to
     * @param command Command to be checked
     * @param argCount The current argument count
     * @return If the correct arguments were supplied
     */
    private boolean isCorrectArgs(CommandSender sender, EOTOCommand command, int argCount) {
        // TODO: Remove
        for (int value : command.handledArgs()) {
            if (value == argCount)
                return true;
        }

        // not the correct usage, return false and send error message
        sender.sendMessage(RED + "Please review your argument count.");
        sender.sendMessage(RED + command.getCorrectUsage());
        return false;
    }

    /**
     * Adds commands to list to be recognized by the plugin
     * @param cmds Commands to be added
     */
    private void addCommands(EOTOCommand... cmds) {
        for (EOTOCommand cmd : cmds) {
            commands.put(cmd.getName(), cmd);
        }
    }

    /**
     * Gets all the added commands
     * @return All commands manually added
     */
    public Map<String, EOTOCommand> getAllCommands() {
        return commands;
    }
}
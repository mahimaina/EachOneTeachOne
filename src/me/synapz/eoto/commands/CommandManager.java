package me.synapz.eoto.commands;

import static org.bukkit.ChatColor.*;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor {

    List<EOTOCommand> commands = new ArrayList<>();
    private static CommandManager manager = new CommandManager();

    private CommandManager() {}

    public static CommandManager getManager() {
        return manager;
    }

    public void init() {
        // addCommands(null);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
        boolean isEOTOCommand = false;
        EOTOCommand command = null;

        for (EOTOCommand aec : commands) {
            if (cmd.getName().equalsIgnoreCase(aec.getName())) {
                command = aec;
                isEOTOCommand = true;
            }
        }

        if (!isEOTOCommand) {
            return true;
        }

        if (sender instanceof Player) {
            try {
                if (commandChecks(command, sender, args.length)) {
                    command.onCommand((Player) sender, args);
                }
            }catch (Exception e) {
                sender.sendMessage(RED + "An unknown error occurred. Check console for a error log.");
                e.printStackTrace();
            }
        } else {
            sender.sendMessage(RED + "Only players are permitted to use this command.");
        }

        return true;
    }

    private boolean commandChecks(EOTOCommand command, CommandSender sender, int argCount) {
        if (!(sender instanceof Player) && isCorrectArgs(sender, command, argCount)) {
            return true;
        }
        if (sender instanceof Player && isCorrectArgs(sender, command, argCount) && hasPerm(sender, argCount, command)) {
            return true;
        }
        return false;
    }

    /**
     * Checks to make sure a CommandSender has access to the command
     * @param sender Sender of command
     * @param argument Current argument
     * @param command Command to check for permission to
     * @return if the CommandSender has permission to that argument and permission
     */
    // TODO: Use HashMap arg to permission
    private boolean hasPerm(CommandSender sender, int argument, EOTOCommand command) {
        String permission = "";
        for (String perm : command.getPermissions()) {

            String[] permList = perm.split(" ");

            if (Integer.parseInt(permList[1]) == argument) {
                permission = permList[0];
            }
        }

        if (sender.hasPermission(permission)) {
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
        if (Arrays.asList(command.handledArgs()).contains(argCount)) {
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
            commands.add(cmd);
        }
    }

    /**
     * Gets all the added commands
     * @return All commands manually added
     */
    public List<EOTOCommand> getAllCommands() {
        return commands;
    }
}
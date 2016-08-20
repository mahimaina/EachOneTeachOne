package me.synapz.eoto.utils;

import me.synapz.eoto.commands.EOTOCommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

/**
 * Class responsible for easily sending messages to CommandSender (console, command blocks, players)
 * Formats messages to keep a uniform message output throughout the whole plugin
 */
public class Messenger {

    public static String PLAYER_ONLY_COMMAND = "You must be a player to execute this command!";

    public static void error(CommandSender sender, String... msg) {
        for (String str : msg)
            info(sender, RED + str);
    }

    public static void success(CommandSender sender, String... msg) {
        for (String str : msg)
            info(sender, GREEN + str);
    }

    public static void info(CommandSender sender, String...msg) {
        for (String str : msg) {
            sender.sendMessage(DARK_GRAY + "[" + GOLD + "" + BOLD + "EOTO" + DARK_GRAY + "] " + GOLD + str);
        }
    }

    public static void msg(CommandSender sender, String...msg) {
        for (String str : msg)
            sender.sendMessage(str);
    }

    // Checks to see if a player has a permission, returns true if they do false if they don't
    public static boolean permissionValidator(Player player, String permission) {
        if (player.hasPermission(permission)) {
            return true;
        } else {
            error(player, "You don't have permission for that command!");
            return false;
        }
    }

    // Checks to see if a player has a permission to break/create a sign
    public static boolean signPermissionValidator(Player player, String permission) {
        if (player.hasPermission(permission)) {
            return true;
        } else {
            error(player, "You don't have permission to use that sign!");
            return false;
        }
    }

    // Sends a message if there is some type of wrong usage
    public static void wrongUsage(EOTOCommand command, Player player, Usage usage) {
        if (usage.equals(Usage.TO_MANY_ARGS)) {
            error(player, "Too many arguments!", command.getCorrectUsage());
        } else {
            error(player, "Not enough arguments!", command.getCorrectUsage());
        }
    }

    // Get's the help associated with the command type
    public static String getHelpTitle() {
        String title = "EachOneTeachOne";

        return GRAY + "" + STRIKETHROUGH + Utils.makeSpaces(15) + RESET + DARK_GRAY + BOLD + STRIKETHROUGH + "[-" + RESET + ChatColor.GOLD + " " + BOLD + title + " " + DARK_GRAY + BOLD + STRIKETHROUGH + "-]" + GRAY + STRIKETHROUGH + Utils.makeSpaces(15);
    }

    public static String createPrefix(String suffix){
        return GOLD + "" + BOLD + suffix + GRAY;
    }

    public enum Usage {
        TO_MANY_ARGS,
        NOT_ENOUGH_ARGS
    }
}

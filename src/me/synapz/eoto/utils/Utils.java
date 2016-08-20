package me.synapz.eoto.utils;

import me.synapz.eoto.commands.CommandManager;
import me.synapz.eoto.commands.EOTOCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.ChatColor.*;

public class Utils {

    public static String makeSpaces(int spaces) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < spaces; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    public static boolean isOnline(UUID uuid) {
        return Bukkit.getPlayer(uuid) != null;
    }

    public static List<UUID> filterOffline(List<UUID> targets) {
        return new ArrayList<UUID>() {{
           for (UUID uuid : targets) {
               if (Bukkit.getPlayer(uuid) != null) {
                   add(uuid);
               }
           }
        }};
    }

    public static void showHelp(CommandSender sender) {
        sender.sendMessage(Messenger.getHelpTitle());

        for (EOTOCommand command : CommandManager.getManager().getAllCommands().values()) {

            sender.sendMessage(GOLD + "/eoto " + command.getName() + " " + command.getArguments() + WHITE + " - " + GRAY + command.getDescription());

        }
    }

    public static int[] allArguments() {
        int[] ints = new int[199];
        for (int i = 1; i < 200; i++) {
            ints[i] = i;
        }
        return ints;
    }

    public static String messagerBuilder(String[] args) {
        String msg = "";
        for (int i = 1; i < args.length; i++) {
            // if i-1 == args, its the last run so we need to remove the " " at the end
            msg = i+1 == args.length ? msg + args[i] : msg + args[i] + " ";
        }
        return msg;
    }
}

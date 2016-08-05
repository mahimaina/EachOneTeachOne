package me.synapz.eoto.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class EOTOCommand {

    public abstract void onCommand(Player player, String[] args);

    public abstract String getName();

    public abstract String[] getPermissions();

    public abstract int[] handledArgs();

    public abstract String[] getArguments();

    public String getCorrectUsage() {
        String arguments = "";
        for (String arg : getArguments()) {
            arguments += arg + " ";
        }
        return ChatColor.RED + "Usage: /" + this.getName() + " " + arguments;
    }
}

package me.synapz.eoto.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class EOTOCommand {

    public abstract void onCommand(CommandSender sender, String[] args);

    public abstract String getName();

    public abstract String getPermission();

    public abstract int[] handledArgs();

    public abstract String getArguments();

    public abstract String getDescription();

    public String getCorrectUsage() {
        return ChatColor.RED + "Usage: /eoto " + this.getName() + " " + this.getArguments();
    }
}
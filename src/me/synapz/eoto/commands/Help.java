package me.synapz.eoto.commands;

import me.synapz.eoto.utils.Utils;
import org.bukkit.command.CommandSender;

public class Help extends EOTOCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Utils.showHelp(sender);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getPermission() {
        return "eoto.help";
    }

    @Override
    public int[] handledArgs() {
        return new int[] { 1 };
    }

    @Override
    public String getArguments() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Show EOTO help menu";
    }
}

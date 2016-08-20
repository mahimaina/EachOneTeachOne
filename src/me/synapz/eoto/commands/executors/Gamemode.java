package me.synapz.eoto.commands.executors;

import me.synapz.eoto.utils.CommandUtil;
import me.synapz.eoto.commands.EOTOTargetCommand;
import me.synapz.eoto.utils.Messenger;
import org.bukkit.GameMode;

public class Gamemode extends EOTOTargetCommand {

    // eoto gamemode <player/all> <gamemode>

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        String strGamemode = args[2].toUpperCase();
        GameMode gamemode;

        try {
            gamemode = GameMode.valueOf(strGamemode);
        } catch (java.lang.IllegalArgumentException exc) {
            Messenger.error(sender, "Invalid gamemode.");
            return;
        }

        CommandUtil.gamemode(sender, targets, gamemode);
    }

    @Override
    public String getName() {
        return "gamemode";
    }

    @Override
    public String getPermission() {
        return "eoto.gamemode";
    }

    @Override
    public int[] handledArgs() {
        return new int[] { 3 };
    }

    @Override
    public String getArguments() {
        return "<player/all> <creative/survival/adventure/spectator>";
    }

    @Override
    public String getDescription() {
        return "Set gamemode of selection";
    }
}

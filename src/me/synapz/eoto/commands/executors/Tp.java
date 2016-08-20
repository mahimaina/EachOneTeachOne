package me.synapz.eoto.commands.executors;

import me.synapz.eoto.utils.CommandUtil;
import me.synapz.eoto.commands.EOTOTargetCommand;
import me.synapz.eoto.utils.Messenger;
import org.bukkit.entity.Player;

public class Tp extends EOTOTargetCommand {

    // eoto tp <player/all>

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        if (!(sender instanceof Player)) {
            Messenger.error(sender, Messenger.PLAYER_ONLY_COMMAND);
            return;
        }

        CommandUtil.tp((Player) sender, targets, ((Player) sender).getLocation());
    }

    @Override
    public String getName() {
        return "tp";
    }

    @Override
    public String getPermission() {
        return "eoto.tp";
    }

    @Override
    public int[] handledArgs() {
        return new int[] { 2 };
    }

    @Override
    public String getArguments() {
        return "<player/all>";
    }

    @Override
    public String getDescription() {
        return "Teleport selection to you";
    }
}

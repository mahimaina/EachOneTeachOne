package me.synapz.eoto.commands.executors;

import me.synapz.eoto.utils.CommandUtil;
import me.synapz.eoto.commands.EOTOTargetCommand;

public class Unfreeze extends EOTOTargetCommand {

    // eoto unfeeze <player/all>

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        CommandUtil.freeze(sender, targets, false);
    }

    @Override
    public String getName() {
        return "unfreeze";
    }

    @Override
    public String getPermission() {
        return "eoto.unfreeze";
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
        return "Unfreeze selection";
    }
}

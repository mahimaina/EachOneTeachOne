package me.synapz.eoto.commands.executors;

import me.synapz.eoto.utils.CommandUtil;
import me.synapz.eoto.commands.EOTOTargetCommand;

public class Freeze extends EOTOTargetCommand {

    // eoto feeze <player/all>

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        CommandUtil.freeze(sender, targets, true);
    }

    @Override
    public String getName() {
        return "freeze";
    }

    @Override
    public String getPermission() {
        return "eoto.freeze";
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
        return "Freeze selection";
    }
}

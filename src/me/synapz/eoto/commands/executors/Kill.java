package me.synapz.eoto.commands.executors;

import me.synapz.eoto.utils.CommandUtil;
import me.synapz.eoto.commands.EOTOTargetCommand;

public class Kill extends EOTOTargetCommand {

    // /eoto kill <player/all>

    @Override
    public void stuffToDoToTargets() {
        CommandUtil.kill(sender, targets);
    }

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public String getName() {
        return "kill";
    }

    @Override
    public String getPermission() {
        return "eoto.kill";
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
        return "Kill selection";
    }
}
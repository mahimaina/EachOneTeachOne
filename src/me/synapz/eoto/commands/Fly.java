package me.synapz.eoto.commands;

import me.synapz.eoto.CommandUtil;

public class Fly extends EOTOTargetCommand {

    // eoto fly <player/all>

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        CommandUtil.fly(sender, targets, true);
    }

    @Override
    public String getName() {
        return "fly";
    }

    @Override
    public String getPermission() {
        return "eoto.fly";
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
        return "Enable fly for selection";
    }
}

package me.synapz.eoto.commands;

import me.synapz.eoto.CommandUtil;

public class NoFly extends EOTOTargetCommand {

    // eoto nofly <player/all>

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        CommandUtil.fly(sender, targets, false);
    }

    @Override
    public String getName() {
        return "nofly";
    }

    @Override
    public String getPermission() {
        return "eoto.nofly";
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
        return "Disable fly for selection";
    }
}

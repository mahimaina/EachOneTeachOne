package me.synapz.eoto.commands.executors;

import me.synapz.eoto.utils.CommandUtil;
import me.synapz.eoto.commands.EOTOTargetCommand;

public class Heal extends EOTOTargetCommand {

    // eoto heal <player/all>

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        CommandUtil.heal(sender, targets);
    }

    @Override
    public String getName() {
        return "heal";
    }

    @Override
    public String getPermission() {
        return "eoto.heal";
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
        return "Heal selection";
    }
}

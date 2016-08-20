package me.synapz.eoto.commands.executors;

import me.synapz.eoto.utils.CommandUtil;
import me.synapz.eoto.commands.EOTOTargetCommand;
import me.synapz.eoto.utils.Utils;

public class Msg extends EOTOTargetCommand {

    // eoto msg <player/all> [message]

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        String message = Utils.messagerBuilder(args);

        CommandUtil.msg(sender, targets, message);
    }

    @Override
    public String getName() {
        return "msg";
    }

    @Override
    public String getPermission() {
        return "eoto.msg";
    }

    @Override
    public int[] handledArgs() {
        return Utils.allArguments();
    }

    @Override
    public String getArguments() {
        return "<player/all> <message>";
    }

    @Override
    public String getDescription() {
        return "Message selection";
    }
}


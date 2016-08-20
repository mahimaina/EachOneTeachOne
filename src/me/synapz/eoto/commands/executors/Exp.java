package me.synapz.eoto.commands.executors;

import me.synapz.eoto.utils.CommandUtil;
import me.synapz.eoto.commands.EOTOTargetCommand;
import me.synapz.eoto.utils.Messenger;

public class Exp extends EOTOTargetCommand {

    // eoto exp <player/all> <exp>

    @Override
    public int getTargetArg() {
        return 1;
    }

    @Override
    public void stuffToDoToTargets() {
        String strExp = args[2];
        int exp = 0;

        try {
            exp = Integer.parseInt(strExp);
        } catch (NumberFormatException exc) {
            Messenger.error(sender, "Please provide a valid number.");
            return;
        }

        CommandUtil.exp(sender, targets, exp);
    }

    @Override
    public String getName() {
        return "exp";
    }

    @Override
    public String getPermission() {
        return "eoto.exp";
    }

    @Override
    public int[] handledArgs() {
        return new int[] { 3 };
    }

    @Override
    public String getArguments() {
        return "<player/all> <new exp>";
    }

    @Override
    public String getDescription() {
        return "Set selection's exp level";
    }
}

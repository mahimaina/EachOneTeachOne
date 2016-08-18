package me.synapz.eoto.commands;

import me.synapz.eoto.Teacher;
import me.synapz.eoto.utils.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class EOTOTargetCommand extends EOTOCommand {

    protected List<UUID> targets = new ArrayList<>();
    protected Player sender;
    protected String[] args;

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        this.args = args;
        String strTarget = args[getTargetArg()];

        if (strTarget.equalsIgnoreCase("all")) {
            if (sender instanceof Player) {
                Player playerSender = (Player) sender;

                Teacher teacher = Teacher.teachers.get(playerSender.getUniqueId());

                if (teacher == null) {
                    Messenger.error(playerSender, "You are not a teacher!");
                    return;
                }

                targets = teacher.getStudents();
            } else {
                Messenger.error(sender, Messenger.PLAYER_ONLY_COMMAND);
            }
        } else {
            Player target = Bukkit.getPlayer(strTarget);

            if (target == null) {
                Messenger.error(sender, "Player " + strTarget + " is currently not online.");
            } else {
                targets.add(target.getUniqueId());
            }
        }

        if (targets == null || targets.isEmpty()) {
            return;
        } else {
            stuffToDoToTargets();
        }
    }

    public abstract int getTargetArg();

    public abstract void stuffToDoToTargets();
}

package me.synapz.eoto.commands.lesson;

import me.synapz.eoto.Lesson;
import me.synapz.eoto.Teacher;
import me.synapz.eoto.commands.EOTOCommand;
import me.synapz.eoto.utils.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ext extends EOTOCommand {

    // /eoto ext [min]

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            Messenger.error(sender, Messenger.PLAYER_ONLY_COMMAND);
            return;
        }

        Player player = (Player) sender;
        Teacher teacher = Teacher.teachers.get(player.getUniqueId());

        if (teacher != null) {

            int length;

            try {
                length = Integer.parseInt(args[1]);
            } catch (NumberFormatException exc) {
                Messenger.error(player, Messenger.NOT_A_NUMBER);
                return;
            }

            if (teacher.getCurrentLesson() == null) {
                Messenger.error(sender, "You do not have a lesson started to set its spawn");
                return;
            } else {
                teacher.getCurrentLesson().extendLesson(length);
                Messenger.success(sender, "You have extended the current lesson time by " + ChatColor.GRAY + length + ChatColor.GREEN + " to " + ChatColor.GRAY + teacher.getCurrentLesson().getTimeLeft() + ChatColor.GREEN + " minutes");
            }

        } else {
            Messenger.error(player, Messenger.NOT_A_TEACHER);
        }
    }

    @Override
    public String getName() {
        return "ext";
    }

    @Override
    public String getPermission() {
        return "eoto.extend";
    }

    @Override
    public int[] handledArgs() {
        return new int[] { 2 };
    }

    @Override
    public String getArguments() {
        return "[minutes]";
    }

    @Override
    public String getDescription() {
        return "Extends a current lesson by supplied minutes";
    }
}
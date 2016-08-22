package me.synapz.eoto.commands.lesson;

import me.synapz.eoto.Lesson;
import me.synapz.eoto.Teacher;
import me.synapz.eoto.commands.EOTOCommand;
import me.synapz.eoto.utils.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Set extends EOTOCommand {

    // /eoto set [min]

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

            new Lesson(teacher, length, player.getLocation()).startLesson();
            Messenger.success(player, "You have started a " + ChatColor.GRAY + length + ChatColor.GREEN + " minute lesson with " + ChatColor.GRAY + teacher.getStudents().size() + ChatColor.GREEN + " students.");
        } else {
            Messenger.error(player, Messenger.NOT_A_TEACHER);
        }
    }

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getPermission() {
        return "eoto.set";
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
        return "Create a new lesson for supplied minutes";
    }
}

package me.synapz.eoto.commands.lesson;

import me.synapz.eoto.Teacher;
import me.synapz.eoto.commands.EOTOCommand;
import me.synapz.eoto.utils.Messenger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn extends EOTOCommand {

    // /eoto spawn

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            Messenger.error(sender, Messenger.PLAYER_ONLY_COMMAND);
            return;
        }

        Player player = (Player) sender;
        Teacher teacher = Teacher.teachers.get(player.getUniqueId());

        if (teacher != null) {
            if (teacher.getCurrentLesson() == null) {
                Messenger.error(sender, "You do not have a lesson started to set its spawn");
                return;
            } else {
                teacher.getCurrentLesson().setLessonSpawn(player.getLocation());
                Messenger.success(sender, "You have set the lesson spawn to your location!");
            }

        } else {
            Messenger.error(player, Messenger.NOT_A_TEACHER);
        }
    }

    @Override
    public String getName() {
        return "spawn";
    }

    @Override
    public String getPermission() {
        return "eoto.spawn";
    }

    @Override
    public int[] handledArgs() {
        return new int[] { 1 };
    }

    @Override
    public String getArguments() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Set the lesson spawn to your location.";
    }
}
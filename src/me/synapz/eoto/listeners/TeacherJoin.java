package me.synapz.eoto.listeners;

import me.synapz.eoto.EOTO;
import me.synapz.eoto.Teacher;
import me.synapz.eoto.files.StudentsFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class TeacherJoin implements Listener {

    @EventHandler
    public void onTeacherJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        if (EOTO.getStudentsFile().isTeacher(uuid)) {
            new Teacher(event.getPlayer());
        }
    }

    @EventHandler
    public void onTeacherLeave(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        if (EOTO.getStudentsFile().isTeacher(uuid)) {
            Teacher.teachers.remove(uuid);
            StudentsFile.offlineTeachers.add(uuid);
        }
    }
}

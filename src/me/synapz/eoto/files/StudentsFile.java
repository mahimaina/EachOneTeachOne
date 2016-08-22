package me.synapz.eoto.files;

import me.synapz.eoto.Teacher;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentsFile extends EOTOFile {

    public static List<UUID> offlineTeachers = new ArrayList<>();

    public StudentsFile(Plugin plugin) {
        super(plugin, "students.yml");

        loadStudentFiles();
    }

    public boolean isTeacher(UUID uuid) {
        return fileConfig.isSet("Students." + uuid);
    }

    private void loadStudentFiles() {
        for (String teacherUUID : fileConfig.getConfigurationSection("Students").getKeys(false)) {
            if (teacherUUID.equals("teacher-uuid"))
                continue;

            UUID teacherToLoad = UUID.fromString(teacherUUID);
            Player teacher = Bukkit.getPlayer(teacherToLoad);

            if (teacherToLoad != null && teacher != null) {
                new Teacher(teacher);
            } else {
                offlineTeachers.add(teacherToLoad);
            }
        }
    }

    @Override
    public void onFirstCreate() {
        fileConfig.set("Students.teacher-uuid", new ArrayList<String>() {{ add("students-uuid"); }});
    }
}

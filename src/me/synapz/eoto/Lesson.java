package me.synapz.eoto;

import com.sun.istack.internal.Nullable;
import me.synapz.eoto.countdowns.LessonCountdown;
import me.synapz.eoto.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Lesson {

    private Map<UUID, Location> locations = new HashMap<>();

    private Location spawn;
    private final Teacher teacher;
    private int length;

    public Lesson(Teacher teacher, int length, @Nullable Location spawn) {
        this.teacher = teacher;
        this.length = length;
        this.spawn = spawn;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void decrementTimeLeft() {
        length -= 1;
    }

    public int getTimeLeft() {
        return length;
    }

    public void startLesson() {
        boolean hasSpawn = spawn != null;

        for (UUID uuid : teacher.getStudents()) {
            // player is not online, go to next iteration
            if (!Utils.isOnline(uuid))
                continue;

            Player toTeleport = Bukkit.getPlayer(uuid);

            // stores old location
            locations.put(uuid, toTeleport.getLocation());

            // if there is a spawn, teleport them to it, otherwise teleport them to the teacher's location
            if (hasSpawn)
                toTeleport.teleport(spawn);
            else
                toTeleport.teleport(teacher.getPlayer().getLocation());
        }

        new LessonCountdown(length, this);
    }

    public void endLesson() {
        // TODO: When Messenger is done message the teacher

        // teleport all players back to their last location
        for (UUID uuid : locations.keySet()) {
            Bukkit.getPlayer(uuid).teleport(locations.get(uuid));
        }
    }

    public void extendLesson(int extendTime) {
        // TODO: Message teacher
        length += extendTime;
    }

    /**
     * Sets the spawn to the location
     * @param location Location to be set
     */
    public void setLessonSpawn(Location location) {
        this.spawn = location;

        for (UUID student : Utils.filterOffline(teacher.getStudents())) {
            Player player = Bukkit.getPlayer(student);

            if (student != null) {
                player.teleport(player);
            }
        }
    }
}

package me.synapz.eoto;

import com.sun.istack.internal.Nullable;
import me.synapz.eoto.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Lesson {

    private Location spawn;
    private final Teacher teacher;
    private final int length;

    public Lesson(Teacher teacher, int length, @Nullable Location spawn) {
        this.teacher = teacher;
        this.length = length;
        this.spawn = spawn;
    }

    public void startLesson() {
        boolean hasSpawn = spawn != null;

        // TODO: Also put player information into PlayerData so nothing is lost for them
        for (UUID uuid : teacher.getStudents()) {
            // player is not online, go to next iteration
            if (!Utils.isOnline(uuid))
                continue;

            Player toTeleport = Bukkit.getPlayer(uuid);

            // if there is a spawn, teleport them to it, otherwise teleport them to the teacher's location
            if (hasSpawn)
                toTeleport.teleport(spawn);
            else
                toTeleport.teleport(teacher.getPlayer().getLocation());
        }
    }

    public void endLesson() {
        // TODO: When Messenger is done message the teacher

        for (UUID uuid : teacher.getStudents()) {
            
        }
    }

    public void extendLesson() {}

    /**
     * Sets the spawn to the location
     * @param location Location to be set
     */
    public void setLessonSpawn(Location location) {
        this.spawn = location;
    }

}

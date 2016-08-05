package me.synapz.eoto;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * Wrapper class to wrap a Player who is a Teacher (found inside students.yml)
 */
public class Teacher {

    private final Player teacher;
    private final List<UUID> students;

    private Lesson lesson;

    /**
     * Create a teacher from a player object
     * @param teacher Player to be wrapped from Teacher
     */
    public Teacher(Player teacher) {
        this.teacher = teacher;
        students = new ArrayList<>(); // TODO: get from students.yml
        lesson = null;

        giveItems();
    }

    /**
     * Gets the Player hooked up to the Teacher
     * @return The Teacher's Player value
     */
    public Player getPlayer() {
        return teacher;
    }

    /**
     * All the students who are being taught grabbed from students.yml
     * @return Current student list
     */
    public List<UUID> getStudents() {
        return students;
    }

    /**
     * Starts a lesson by suppling a new Lesson object
     * @param lesson Lesson to be started
     */
    public void startLesson(Lesson lesson) {
        this.lesson = lesson;
        lesson.startLesson();
    }

    /**
     * Can be null and gets the lesson created by startLesson
     * @return Current lesson
     */
    public Lesson getCurrentLesson() {
        return lesson;
    }

    /**
     * Gives admin items to Teacher
     */
    private void giveItems() {
        // TODO: Make items & give
    }
}
package me.synapz.eoto.countdowns;

import me.synapz.eoto.Lesson;
import me.synapz.eoto.reflection.ActionBar;

public class LessonCountdown extends EOTOCountdown {

    private final Lesson lesson;

    public LessonCountdown(int counter, Lesson lesson) {
        super(counter);

        this.lesson = lesson;
    }

    public void onFinish() {
        lesson.endLesson();
    }

    // Called every iteration of run()
    public void onIteration() {
        // TODO: Convert to seconds/minutes
        ActionBar.sendActionBar(this.lesson.getTeacher().getPlayer(), "Lesson ending in " + counter + " seconds");
    }

    public boolean stop() {
        return lesson.getTimeLeft() <= 0;
    }

    public boolean intervalCheck() {
        return true;
    }

    public void cancel() {
        super.cancel();
    }
}
package me.synapz.eoto.countdowns;

import me.synapz.eoto.EOTO;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class EOTOCountdown extends BukkitRunnable {

    protected double decrement = 1;
    protected int end = 0;
    protected double counter;

    public EOTOCountdown(double counter) {
        this.counter = counter;

        this.runTaskTimer(JavaPlugin.getProvidingPlugin(EOTO.class), 0, 20);
    }

    @Override
    public void run() {
        if (stop()) {
            cancel();
            return;
        }

        if (counter <= end) {
            onFinish();
            cancel();
        } else {
            if (intervalCheck())
                onIteration();
        }
        counter = counter - decrement;
    }

    // Called once the counter reaches 0
    public abstract void onFinish();

    // Called every iteration of run()
    public abstract void onIteration();

    // Checks that must be full-filled in order to run, if this is not met, then it will cancel
    public abstract boolean stop();

    // Some countdowns have an interval to do things (ex: every 15 seconds print hi). This checks if there is an interval (set return true for no interval)
    public abstract boolean intervalCheck();

    public void cancel() {
        super.cancel();
        counter = -1D;
    }

    public double getCounter() {
        return counter;
    }
}
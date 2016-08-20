package me.synapz.eoto;

import me.synapz.eoto.commands.CommandManager;
import me.synapz.eoto.files.StudentsFile;
import me.synapz.eoto.listeners.FreezeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EOTO extends JavaPlugin {

    private static StudentsFile studentsFile;

    @Override
    public void onEnable() {
        // load all commands
        CommandManager.getManager().init();

        registerEvents();
        registerCommands();

        studentsFile = new StudentsFile(this);
    }

    @Override
    public void onDisable() {

    }

    /**
     * Registers all events
     */
    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new FreezeListener(), this);
    }

    /**
     * Registers all commands
     * Must be called after CommandManager.getManager().init() is called
     */
    private void registerCommands() {
        getCommand("eoto").setExecutor(CommandManager.getManager());
    }

    public static StudentsFile getStudentsFile() {
        return studentsFile;
    }
}
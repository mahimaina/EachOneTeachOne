package me.synapz.eoto;

import me.synapz.eoto.commands.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EOTO extends JavaPlugin {

    @Override
    public void onEnable() {
        // load all commands
        CommandManager.getManager().init();

        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    /**
     * Registers all events
     */
    private void registerEvents() {

    }

    /**
     * Registers all commands
     * Must be called after CommandManager.getManager().init() is called
     */
    private void registerCommands() {
        getCommand("eoto").setExecutor(CommandManager.getManager());
    }
}
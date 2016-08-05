package me.synapz.eoto;

import me.synapz.eoto.commands.CommandManager;
import me.synapz.eoto.commands.EOTOCommand;
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
        for (EOTOCommand cmd : CommandManager.getManager().getAllCommands()) {
            // sets the current eoto iteration executor to the main command manager class to handle it
            getCommand(cmd.getName()).setExecutor(CommandManager.getManager());
        }
    }
}
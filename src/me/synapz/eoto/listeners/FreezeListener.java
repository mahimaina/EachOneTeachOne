package me.synapz.eoto.listeners;

import me.synapz.eoto.utils.CommandUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener {

    @EventHandler
    public void onMoveWhileFrozen(PlayerMoveEvent event) {
        if (CommandUtil.frozen.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}

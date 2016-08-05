package me.synapz.eoto.utils;

import org.bukkit.Bukkit;

import java.util.UUID;

public class Utils {

    public static boolean isOnline(UUID uuid) {
        return Bukkit.getPlayer(uuid) != null;
    }
}

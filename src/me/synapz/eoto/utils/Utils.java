package me.synapz.eoto.utils;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {

    public static boolean isOnline(UUID uuid) {
        return Bukkit.getPlayer(uuid) != null;
    }

    public static List<UUID> filterOffline(List<UUID> targets) {
        return new ArrayList<UUID>() {{
           for (UUID uuid : targets) {
               if (Bukkit.getPlayer(uuid) != null) {
                   add(uuid);
               }
           }
        }};
    }
}

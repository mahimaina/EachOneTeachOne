package me.synapz.eoto;

import me.synapz.eoto.utils.ExperienceManager;
import me.synapz.eoto.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandUtil {

    private static List<UUID> frozen = new ArrayList<>();

    // TODO: Send messages to CommandSender and targets after Messenger is done

    public static void freeze(CommandSender sender, List<UUID> targets, boolean freeze) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            if (freeze) {
                if (!frozen.contains(uuid))
                    frozen.add(uuid);
            } else {
                frozen.remove(uuid);
            }
        }
    }

    public static void kill(CommandSender sender, List<UUID> targets) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Bukkit.getPlayer(uuid).setHealth(0);
        }
    }

    public static void tp(CommandSender sender, Player target, Location location) {
        target.teleport(location);
    }

    public static void msg(CommandSender sender, Player target, String message) {

    }

    public static void heal(CommandSender sender, List<UUID> targets) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Player target = Bukkit.getPlayer(uuid);
            target.setHealth(target.getMaxHealth());
        }
    }

    public static void feed(CommandSender sender, List<UUID> targets) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Bukkit.getPlayer(uuid).setFoodLevel(20);
        }
    }

    public static void exp(CommandSender sender, List<UUID> targets, int num) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            ExperienceManager manager = new ExperienceManager(Bukkit.getPlayer(uuid));
            manager.setExp(num);
        }

    }

    public static void gamemode(CommandSender sender, List<UUID> targets, GameMode gamemode) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Bukkit.getPlayer(uuid).setGameMode(gamemode);
        }
    }

    public static void fly(CommandSender sender, List<UUID> targets, boolean fly) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Player target = Bukkit.getPlayer(uuid);

            if (fly) {
                target.setAllowFlight(true);
                target.setFlying(true);
            } else {
                target.setFlying(false);
            }

        }
    }

}

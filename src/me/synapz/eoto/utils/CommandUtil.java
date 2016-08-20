package me.synapz.eoto.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.ChatColor.*;

public class CommandUtil {

    public static List<UUID> frozen = new ArrayList<>();

    public static void freeze(CommandSender sender, List<UUID> targets, boolean freeze) {
        for (UUID uuid : targets) {
            if (freeze) {
                if (!frozen.contains(uuid))
                    frozen.add(uuid);
            } else {
                frozen.remove(uuid);
            }
        }

        Messenger.success(sender, "You have " + (freeze ? "frozen " : "unfroze ") + GRAY + targets.size() + GREEN + pluralize(targets));
    }

    public static void kill(CommandSender sender, List<UUID> targets) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Bukkit.getPlayer(uuid).setHealth(0);
        }

        Messenger.success(sender, "You have killed " + GRAY + targets.size() + GREEN + pluralize(targets));
    }

    public static void tp(Player sender, List<UUID> targets, Location location) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Player target = Bukkit.getPlayer(uuid);
            target.teleport(location);
        }

        Messenger.success(sender, "You have teleported " +  GRAY + targets.size() + GREEN + pluralize(targets));
    }

    public static void msg(CommandSender sender, List<UUID> targets, String message) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Player target = Bukkit.getPlayer(uuid);
            Messenger.info(target, "[target-> me] msg");
        }

        Messenger.info(sender, "[Me -> target] msg");
    }

    public static void heal(CommandSender sender, List<UUID> targets) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Player target = Bukkit.getPlayer(uuid);
            target.setHealth(target.getMaxHealth());
        }

        Messenger.success(sender, "Healed " + GRAY + targets.size() + GREEN + pluralize(targets));
    }

    public static void feed(CommandSender sender, List<UUID> targets) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Bukkit.getPlayer(uuid).setFoodLevel(20);
        }

        Messenger.success(sender, "Fed " + GRAY + targets.size() + GREEN + pluralize(targets));
    }

    public static void exp(CommandSender sender, List<UUID> targets, int num) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            ExperienceManager manager = new ExperienceManager(Bukkit.getPlayer(uuid));
            manager.setExp(num);
        }

        Messenger.success(sender, "Set exp to " + num + " for " + GRAY + targets.size() + GREEN + pluralize(targets));
    }

    public static void gamemode(CommandSender sender, List<UUID> targets, GameMode gamemode) {
        targets = Utils.filterOffline(targets);

        for (UUID uuid : targets) {
            Bukkit.getPlayer(uuid).setGameMode(gamemode);
        }

        Messenger.success(sender, "Set gameode " + gamemode.name().toLowerCase() + " for " + GRAY + targets.size() + GREEN + pluralize(targets));
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

        Messenger.success(sender, (fly ? "Enabled" : "Disabled ") + " fly for " + GRAY + targets.size() + GREEN + pluralize(targets));
    }

    private static String pluralize(List<UUID> targets) {
        return " player" + (targets.size() == 1 ? "." : "s.");
    }
}

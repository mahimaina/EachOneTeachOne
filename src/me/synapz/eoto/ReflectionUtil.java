package me.synapz.eoto;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    /**
     * Get an nms class using reflection.
     * @param nmsClassString Name of the nms class you want to retrieve.
     * @return The nms class.
     * @throws ClassNotFoundException
     */
    public static Class<?> getNMSClass(String nmsClassString) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = "net.minecraft.server." + version + nmsClassString;
        Class<?> nmsClass = Class.forName(name);
        return nmsClass;
    }

    /**
     * Get a method using reflection.
     * @param clazz The providing class of the method.
     * @param name The name of the method.
     * @param params Any parameters in the method.
     * @return
     * @throws NoSuchMethodException
     */
    public static Method getMethod(Class<?> clazz, String name, Class<?>... params) throws NoSuchMethodException {
        Method method = clazz.getMethod(name, params);
        return method;
    }

    /**
     * Get a {@link Player} connection.
     * @param player The player whose connection you want to retrieve.
     * @return The player connection for sending packets.
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object getConnection(Player player) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        Method getHandle = player.getClass().getMethod("getHandle");
        Object nmsPlayer = getHandle.invoke(player);
        Field conField = nmsPlayer.getClass().getField("playerConnection");
        Object con = conField.get(nmsPlayer);
        return con;
    }
}
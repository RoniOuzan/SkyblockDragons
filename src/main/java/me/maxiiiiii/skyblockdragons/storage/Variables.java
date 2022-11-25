package me.maxiiiiii.skyblockdragons.storage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.CustomConfig;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;

public class Variables {
    private static final CustomConfig variables = new CustomConfig("Variables");
    private static final Map<UUID, CustomConfig> playerVariables = new HashMap<>();
    private static final String PLAYER_FILE = "Players";

    public static void save() {
        EntitySD.saveLocations();
        for (PlayerSD player : SkyblockDragons.getPlayers()) {
            player.save();
        }

        variables.save();
        for (CustomConfig customConfig : playerVariables.values()) {
            customConfig.save();
        }
    }

    public static void load() {
        variables.load();
    }

    public static void set(UUID uuid, String name, int data, Object value) {
        CustomConfig config = get(uuid);
        config.set(name + "." + data, value);
    }

    public static void set(UUID uuid, String name, Object value) {
        CustomConfig config = get(uuid);
        config.set(name, value);
    }

    public static void set(String name, int data, Object value) {
        variables.set(name + "." + data, value);
    }

    public static void set(String name, Object value) {
        variables.set(name, value);
    }

    public static void delete(UUID uuid, String name, int data) {
        CustomConfig config = get(uuid);
        config.set(name + "." + data, null);
    }

    public static void delete(UUID uuid, String name) {
        CustomConfig config = get(uuid);
        config.set(name, null);
    }

    public static void delete(String name, int data) {
        variables.set(name + "." + data, null);
    }

    public static void delete(String name) {
        variables.set(name, null);
    }

    public static Object get(UUID uuid, String name, int data, Object defaultValue) {
        if (!playerVariables.containsKey(uuid)) {
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());
        }

        return playerVariables.get(uuid).get(name + "." + data, defaultValue);
    }

    public static Object get(UUID uuid, String name, int data) {
        return get(uuid, name, data, null);
    }

    public static Object get(UUID uuid, String name, Object defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).get(name, defaultValue);
    }

    public static Object get(UUID uuid, String name) {
        return get(uuid, name, null);
    }

    public static Object get(String name, int data, Object defaultValue) {
        return variables.get(name + "." + data, defaultValue);
    }

    public static Object get(String name, int data) {
        return get(name, data, null);
    }

    public static Object get(String name, Object defaultValue) {
        return variables.get(name, defaultValue);
    }

    public static Object get(String name) {
        return get(name, null);
    }

    public static List<Entry<Integer, ?>> getList(UUID uuid, String name) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        List<Entry<Integer, ?>> list = new ArrayList<>();
        for (Map.Entry<String, Object> variable : playerVariables.get(uuid).getValues(true).entrySet()) {
            if (variable.getKey().contains(".") && variable.getKey().split("\\.")[0].equals(name)) {
                list.add(new Entry<>(Integer.parseInt(variable.getKey().split("\\.")[1]), variable.getValue()));
            }
        }
        return list;
    }

    public static List<Object> getList(String name) {
        List<Object> list = new ArrayList<>();
        for (Map.Entry<String, Object> variable : variables.getValues(false).entrySet()) {
            if (variable.getKey().equals(name)) {
                list.add(variable.getValue());
            }
        }
        return list;
    }

    public static int getInt(UUID uuid, String name, int data, int defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getInt(name + "." + data, defaultValue);
    }

    public static int getInt(UUID uuid, String name, int defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getInt(name, defaultValue);
    }

    public static double getDouble(UUID uuid, String name, int data, double defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getDouble(name + "." + data, defaultValue);
    }

    public static double getDouble(UUID uuid, String name, double defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getDouble(name, defaultValue);
    }

    public static ItemStack getItemStack(UUID uuid, String name, int data, ItemStack defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getItemStack(name + "." + data, defaultObject);
    }

    public static ItemStack getItemStack(UUID uuid, String name, int data) {
        return getItemStack(uuid, name, data, null);
    }

    public static Vector getVector(UUID uuid, String name, int data, Vector defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getVector(name + "." + data, defaultObject);
    }

    public static Vector getVector(UUID uuid, String name, int data) {
        return getVector(uuid, name, data, null);
    }

    public static Vector getVector(String name, int data, Vector defaultObject) {
        return variables.getVector(name + "." + data, defaultObject);
    }

    public static Vector getVector(String name, int data) {
        return getVector(name, data, null);
    }

    public static String getString(UUID uuid, String name, int data, String defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getString(name + "." + data, defaultObject);
    }

    public static String getString(UUID uuid, String name, String defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getString(name, defaultObject);
    }

    public static String getString(UUID uuid, String name, int data) {
        return getString(uuid, name, data, null);
    }

    public static String getString(String name, int data, String defaultObject) {
        return variables.getString(name + "." + data, defaultObject);
    }

    public static String getString(String name, int data) {
        return getString(name, data, null);
    }

    public static boolean getBoolean(UUID uuid, String name, int data, boolean defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getBoolean(name + "." + data, defaultObject);
    }

    public static boolean getBoolean(UUID uuid, String name, boolean defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getBoolean(name, defaultObject);
    }

    public static boolean getBoolean(String name, int data, boolean defaultObject) {
        return variables.getBoolean(name + "." + data, defaultObject);
    }

    public static Enum<?> getEnum(UUID uuid, String name, int data, Enum<?> defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        String value = playerVariables.get(uuid).getString(name + "." + data, "");
        return value.isEmpty() ? defaultObject : Enum.valueOf(defaultObject.getClass(), value);
    }

    public static Enum<?> getEnum(UUID uuid, String name, Enum<?> defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        String value = playerVariables.get(uuid).getString(name, "");
        return value.isEmpty() ? defaultObject : Enum.valueOf(defaultObject.getClass(), value);
    }

    public static Enum<?> getEnum(String name, int data, Enum<?> defaultObject) {
        String value = variables.getString(name + "." + data, "");
        return value.isEmpty() ? defaultObject : Enum.valueOf(defaultObject.getClass(), value);
    }

    private static CustomConfig get(UUID uuid) {
        if (!playerVariables.containsKey(uuid))
            return playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()));

        return playerVariables.get(uuid);
    }
}

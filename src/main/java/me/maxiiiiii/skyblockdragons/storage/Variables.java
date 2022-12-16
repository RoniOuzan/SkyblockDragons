package me.maxiiiiii.skyblockdragons.storage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.CustomConfig;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;

public class Variables {
    private static final CustomConfig variables = new CustomConfig("Variables");
    private static final Map<UUID, CustomConfig> playerVariables = new HashMap<>();
    private static final String PLAYER_FILE = "Players";

    public static void save() {
        EntitySD.saveSpawns();
        for (PlayerSD player : SkyblockDragons.getPlayers()) {
            player.save();
        }
        NPC.saveAll();

        variables.save();
        for (CustomConfig customConfig : playerVariables.values()) {
            customConfig.save();
        }
    }

    public static void load() {
        variables.load();
    }

    public static void set(UUID uuid, String name, Object data, Object value) {
        CustomConfig config = get(uuid);
        config.set(name + "." + data, value);
    }

    public static void set(UUID uuid, String name, Object value) {
        CustomConfig config = get(uuid);
        config.set(name, value);
    }

    public static void set(String name, Object data, Object value) {
        variables.set(name + "." + data, value);
    }

    public static void set(String name, Object value) {
        variables.set(name, value);
    }

    public static void delete(UUID uuid, String name, Object data) {
        CustomConfig config = get(uuid);
        config.set(name + "." + data, null);
    }

    public static void delete(UUID uuid, String name) {
        CustomConfig config = get(uuid);
        config.set(name, null);
    }

    public static void delete(String name, Object data) {
        variables.set(name + "." + data, null);
    }

    public static void delete(String name) {
        variables.set(name, null);
    }

    public static Object getOrDefault(UUID uuid, String name, Object data, Object defaultValue) {
        if (!playerVariables.containsKey(uuid)) {
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());
        }

        return playerVariables.get(uuid).get(name + "." + data, defaultValue);
    }

    public static Object get(UUID uuid, String name, Object data) {
        return getOrDefault(uuid, name, data, null);
    }

    public static Object getOrDefault(UUID uuid, String name, Object defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).get(name, defaultValue);
    }

    public static Object get(UUID uuid, String name) {
        return getOrDefault(uuid, name, null);
    }

    public static Object getOrDefault(String name, Object data, Object defaultValue) {
        return variables.get(name + "." + data, defaultValue);
    }

    public static Object get(String name, Object data) {
        return getOrDefault(name, data, null);
    }

    public static Object getOrDefault(String name, Object defaultValue) {
        return variables.get(name, defaultValue);
    }

    public static Object get(String name) {
        return getOrDefault(name, null);
    }

    public static List<Entry<String, ?>> getVariablesList(UUID uuid, String name) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        List<Entry<String, ?>> list = new ArrayList<>();
        for (Map.Entry<String, Object> variable : playerVariables.get(uuid).getValues(true).entrySet()) {
            String[] path = variable.getKey().split("\\.");
            if (path[0].equals(name)) {
                list.add(new Entry<>(path[path.length - 1], variable.getValue()));
            }
        }
        return list;
    }

    public static List<Object> getVariablesList(String name) {
        List<Object> list = new ArrayList<>();
        for (Map.Entry<String, Object> variable : variables.getValues(false).entrySet()) {
            if (variable.getKey().equals(name)) {
                list.add(variable.getValue());
            }
        }
        return list;
    }

    public static int getInt(UUID uuid, String name, Object data, int defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getInt(name + "." + data, defaultValue);
    }

    public static int getInt(UUID uuid, String name, int defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getInt(name, defaultValue);
    }

    public static double getDouble(UUID uuid, String name, Object data, double defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getDouble(name + "." + data, defaultValue);
    }

    public static double getDouble(UUID uuid, String name, double defaultValue) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getDouble(name, defaultValue);
    }

    public static ItemStack getItemStack(UUID uuid, String name, Object data, ItemStack defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getItemStack(name + "." + data, defaultObject);
    }

    public static ItemStack getItemStack(UUID uuid, String name, Object data) {
        return getItemStack(uuid, name, data, null);
    }

    public static Vector getVector(UUID uuid, String name, Object data, Vector defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getVector(name + "." + data, defaultObject);
    }

    public static Vector getVector(UUID uuid, String name, Object data) {
        return getVector(uuid, name, data, null);
    }

    public static Vector getVector(String name, Object data, Vector defaultObject) {
        return variables.getVector(name + "." + data, defaultObject);
    }

    public static Vector getVector(String name, Object data) {
        return getVector(name, data, null);
    }

    public static String getString(UUID uuid, String name, Object data, String defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getString(name + "." + data, defaultObject);
    }

    public static String getString(UUID uuid, String name, String defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getString(name, defaultObject);
    }

    public static String getString(UUID uuid, String name, Object data) {
        return getString(uuid, name, data, null);
    }

    public static String getString(String name, Object data, String defaultObject) {
        return variables.getString(name + "." + data, defaultObject);
    }

    public static String getString(String name, Object data) {
        return getString(name, data, null);
    }

    public static boolean getBoolean(UUID uuid, String name, Object data, boolean defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getBoolean(name + "." + data, defaultObject);
    }

    public static boolean getBoolean(UUID uuid, String name, boolean defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return playerVariables.get(uuid).getBoolean(name, defaultObject);
    }

    public static boolean getBoolean(String name, Object data, boolean defaultObject) {
        return variables.getBoolean(name + "." + data, defaultObject);
    }

    public static <T> List<T> getList(UUID uuid, String name, Object data, List<T> defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return (List<T>) playerVariables.get(uuid).getList(name + "." + data, defaultObject);
    }

    public static <T> List<T> getList(UUID uuid, String name, List<T> defaultObject) {
        if (!playerVariables.containsKey(uuid))
            playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()).load());

        return (List<T>) playerVariables.get(uuid).getList(name, defaultObject);
    }

    public static <T> List<T> getList(UUID uuid, String name, Object data) {
        return getList(uuid, name, data, null);
    }

    public static <T> List<T> getList(String name, Object data, List<T> defaultObject) {
        return (List<T>) variables.getList(name + "." + data, defaultObject);
    }

    public static <T> List<T> getList(String name, Object data) {
        return getList(name, data, null);
    }

    private static CustomConfig get(UUID uuid) {
        if (!playerVariables.containsKey(uuid))
            return playerVariables.put(uuid, new CustomConfig(PLAYER_FILE + "/" + uuid.toString()));

        return playerVariables.get(uuid);
    }
}

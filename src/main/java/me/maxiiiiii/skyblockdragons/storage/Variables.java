package me.maxiiiiii.skyblockdragons.storage;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Variables {
    public static ArrayList<Variable<?>> variables = new ArrayList<>();
//    public static HashMap<Variable, Object> variables = new HashMap<>();

    public static final Variable<?> NULL = new Variable<>(null, "null", -1, -1);

    public static <T> T create(UUID uuid, String id, Object data, T value) {
        Variable<T> variable = new Variable<>(uuid, id, data, value);
        variables.add(variable);
        return value;
    }

    public static <T> T create(UUID uuid, String id, T value) {
        return create(uuid, id, -1, value);
    }

    public static <T> T create(String id, Object data, T value) {
        return create(null, id, data, value);
    }

    public static <T> T create(String id, T value) {
        return create(null, id, -1, value);
    }

//    public static <T> T getOrDefault(UUID uuid, String id, Object data, T defaultValue) {
//        T value = (T) get(uuid, id, data, defaultValue.getClass());
//        return value == null ? defaultValue : value;
//    }
//
//    public static <T> T getOrDefault(UUID uuid, String id, T defaultValue) {
//        T value = (T) get(uuid, id, defaultValue.getClass());
//        return value == null ? defaultValue : value;
//    }
//
//    public static <T> T getOrDefault(String id, T defaultValue) {
//        T value = (T) get(id, defaultValue.getClass());
//        return value == null ? defaultValue : value;
//    }

    public static <T> T get(UUID uuid, String id, Object data, T defaultValue) {
        for (Variable<?> variable : variables) {
            if (variable.uuid.equals(uuid) && variable.id.equals(id) && variable.data.equals(data)) {
                return (T) variable.value;
            }
        }
        return defaultValue;
    }

    public static <T> Variable<T> getVariable(UUID uuid, String id, Object data, Class<T> tClass) {
        for (Variable<?> variable : variables) {
            if (variable.uuid.equals(uuid) && variable.id.equals(id) && variable.data.equals(data)) {
                return (Variable<T>) variable;
            }
        }
        return (Variable<T>) NULL;
    }

    public static <T> T get(UUID uuid, String id, T defaultValue) {
        return get(uuid, id, -1, defaultValue);
    }

    public static <T> T get(String id, Object data, T defaultValue) {
        return get(null, id, data, defaultValue);
    }

    public static <T> T get(String id, T defaultValue) {
        return get(null, id, -1, defaultValue);
    }

    public static <T> T set(UUID uuid, String id, Object data, T value) {
        T variableValue = get(uuid, id, data, value);
        if (variableValue == value) {
            return create(uuid, id, data, value);
        } else {
            for (int i = 0; i < variables.size(); i++) {
                Variable<T> variable = (Variable<T>) variables.get(i);
                if (variable.uuid.equals(uuid) && variable.id.equals(id) && variable.data.equals(data)) {
                    variable.value = value;
                    variables.set(i, variable);
                }
            }
            return value;
        }
    }

    public static <T> T set(UUID uuid, String id, T value) {
        return set(uuid, id, -1, value);
    }

    public static <T> T set(String id, Object data, T value) {
        return set(null, id, data, value);
    }

    public static <T> T set(String id, T value) {
        return set(null, id, -1, value);
    }

    public static void delete(UUID uuid, String id, Object data) {
        variables.removeIf(v -> v.uuid.equals(uuid) && v.id.equals(id) && v.data.equals(data));
    }

    public static void delete(UUID uuid, String id, int minData, int maxData) {
        for (int i = minData; i <= maxData; i++) {
            delete(uuid, id, i);
        }
    }

    public static void delete(UUID uuid, String id) {
        delete(uuid, id, -1);
    }

    public static void delete(String id, Object data) {
        delete(null, id, data);
    }

    public static void delete(String id) {
        delete(null, id, -1);
    }

    public static void save() {
        Gson gson = new Gson();
        String json = gson.toJson(variables);
        try {
            FileWriter file = new FileWriter(SkyblockDragons.plugin.getDataFolder().getAbsolutePath() + "/Variables.json");
            file.write(json);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        Gson gson = new Gson();
        String json = "";
        try {
            BufferedReader file = new BufferedReader(new FileReader(SkyblockDragons.plugin.getDataFolder().getAbsolutePath() + "/Variables.json"));
            json = file.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Variable>>() {}.getType();
        variables = gson.fromJson(json, type);
        if (variables == null)
            variables = new ArrayList<>();
    }
}

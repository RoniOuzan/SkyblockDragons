package me.maxiiiiii.skyblockdragons.storage2;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class Variables {
    public static ArrayList<Variable<?>> variables = new ArrayList<>();

    public static <T> Variable<T> create(UUID uuid, String id, Object data, T value) {
        Variable<T> variable = new Variable<>(uuid, id, data, value);
        variables.add(variable);
        return variable;
    }

    public static <T> Variable<T> create(UUID uuid, String id, T value) {
        return create(uuid, id, -1, value);
    }

    public static <T> Variable<T> create(String id, Object data, T value) {
        return create(null, id, data, value);
    }

    public static <T> Variable<T> create(String id, T value) {
        return create(null, id, -1, value);
    }

    public static Variable<?> get(UUID uuid, String id, Object data) {
        for (Variable<?> variable : variables) {
            if (variable.uuid == uuid && variable.id.equals(id) && variable.data == data) {
                return variable;
            }
        }
        return null;
    }

    public static Variable<?> get(UUID uuid, String id) {
        return get(uuid, id, -1);
    }

    public static Variable<?> get(String id, Object data) {
        return get(null, id, data);
    }

    public static <T> Variable<?> set(UUID uuid, String id, Object data, T value) {
        Variable<T> variable = (Variable<T>) get(uuid, id, data);
        if (variable == null)
            return create(uuid, id, data, value);
        else {
            variable.value = value;
            return variable;
        }
    }

    public static <T> Variable<?> set(UUID uuid, String id, T value) {
        return set(uuid, id, -1, value);
    }

    public static <T> Variable<?> set(String id, Object data, T value) {
        return set(null, id, data, value);
    }

    public static <T> Variable<?> set(String id, T value) {
        return set(null, id, -1, value);
    }

    public static void save() {
        Gson gson = new Gson();
        String json = gson.toJson(variables);
        try {
            FileWriter file = new FileWriter(SkyblockDragons.plugin.getDataFolder().getAbsolutePath() + "/Variables2.json");
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
            BufferedReader file = new BufferedReader(new FileReader(SkyblockDragons.plugin.getDataFolder().getAbsolutePath() + "/Variables2.json"));
            file.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Variable<?>>>() {}.getType();
        variables = gson.fromJson(json, type);
    }
}

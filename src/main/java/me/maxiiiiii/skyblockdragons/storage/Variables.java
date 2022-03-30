package me.maxiiiiii.skyblockdragons.storage;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Variables {
    public static ArrayList<Variable<?>> variables = new ArrayList<>();
//    public static HashMap<Variable, Object> variables = new HashMap<>();

    public static final Variable<?> NULL = new Variable<>(null, "null", -1, -1);

    public static <T> T create(UUID uuid, String id, int data, T value) {
        Variable<T> variable = new Variable<>(uuid, id, data, value);
        variables.add(variable);
        return value;
    }

    public static <T> T create(UUID uuid, String id, T value) {
        return create(uuid, id, -1, value);
    }

    public static <T> T create(String id, int data, T value) {
        return create(null, id, data, value);
    }

    public static <T> T create(String id, T value) {
        return create(null, id, -1, value);
    }

//    public static <T> T getOrDefault(UUID uuid, String id, int data, T defaultValue) {
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

    public static <T> T get(UUID uuid, String id, int data, T defaultValue) {
        for (Variable<?> variable : variables) {
            if (variable.uuid.equals(uuid) && variable.id.equals(id) && variable.data== data) {
                return (T) variable.value;
            }
        }
        return defaultValue;
    }

    public static <T> Variable<T> getVariable(UUID uuid, String id, int data, Class<T> tClass) {
        for (Variable<?> variable : variables) {
            if (variable.uuid.equals(uuid) && variable.id.equals(id) && variable.data== data) {
                return (Variable<T>) variable;
            }
        }
        return (Variable<T>) NULL;
    }

    public static <T> T get(UUID uuid, String id, T defaultValue) {
        return get(uuid, id, -1, defaultValue);
    }

    public static <T> T get(String id, int data, T defaultValue) {
        return get(null, id, data, defaultValue);
    }

    public static <T> T get(String id, T defaultValue) {
        return get(null, id, -1, defaultValue);
    }

    public static <T> T set(UUID uuid, String id, int data, T value) {
        T variableValue = get(uuid, id, data, value);
        if (variableValue == value) {
            return create(uuid, id, data, value);
        } else {
            for (int i = 0; i < variables.size(); i++) {
                Variable<T> variable = (Variable<T>) variables.get(i);
                if (variable.uuid.equals(uuid) && variable.id.equals(id) && variable.data== data) {
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

    public static <T> T set(String id, int data, T value) {
        return set(null, id, data, value);
    }

    public static <T> T set(String id, T value) {
        return set(null, id, -1, value);
    }

    public static void delete(UUID uuid, String id, int data) {
        variables.removeIf(v -> v.uuid.equals(uuid) && v.id.equals(id) && v.data == data);
    }

    public static void delete(UUID uuid, String id, int minData, int maxData) {
        for (int i = minData; i <= maxData; i++) {
            delete(uuid, id, i);
        }
    }

    public static void delete(UUID uuid, String id) {
        delete(uuid, id, -1);
    }

    public static void delete(String id, int data) {
        delete(null, id, data);
    }

    public static void delete(String id) {
        delete(null, id, -1);
    }

    public static void save() {
        Gson gson = new Gson();
        ArrayList<Variable<?>> variablesToAdd = new ArrayList<>();
        ArrayList<Variable<?>> variablesToRemove = new ArrayList<>();
        for (Variable<?> variable : variables) {
            if (!Functions.isNumber(variable.value + "")) {
                variablesToAdd.add(new Variable<>(variable.uuid, variable.id, variable.data, SkyblockDragons.serializer.serialize(variable.value)));
                variablesToRemove.add(variable);
            }
        }
        variables.addAll(variablesToAdd);
        variables.removeAll(variablesToRemove);
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
        try {
            BufferedReader file = new BufferedReader(new FileReader(SkyblockDragons.plugin.getDataFolder().getAbsolutePath() + "/Variables.json"));
            String json = file.readLine();
            JsonReader reader = new JsonReader(new StringReader(json));
            JsonParser parser = new JsonParser();
            JsonArray arr = parser.parse(reader).getAsJsonArray();
            for (JsonElement variable : arr) {
                UUID uuid = UUID.fromString(variable.getAsJsonObject().get("uuid").getAsString());
                String id = variable.getAsJsonObject().get("id").getAsString();
                int data = variable.getAsJsonObject().get("data").getAsInt();
                String value = variable.getAsJsonObject().get("value").getAsString();
                if (Functions.isInt(value)) {
                    variables.add(new Variable<>(uuid, id, data, Integer.parseInt(value)));
                } else if (Functions.isLong(value)) {
                    variables.add(new Variable<>(uuid, id, data, Long.parseLong(value)));
                } else if (Functions.isFloat(value)) {
                    variables.add(new Variable<>(uuid, id, data, Float.parseFloat(value)));
                } else if (Functions.isDouble(value)) {
                    variables.add(new Variable<>(uuid, id, data, Double.parseDouble(value)));
                } else {
                    variables.add(new Variable<>(uuid, id, data, SkyblockDragons.serializer.deserialize(value)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (variables == null)
            variables = new ArrayList<>();
    }
}

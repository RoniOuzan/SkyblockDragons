package me.maxiiiiii.skyblockdragons.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.serialization.Serializer;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Variables {
    public static final List<Variable> variables = new ArrayList<>();
    public static final Map<UUID, List<Variable>> playerVariables = new HashMap<>();

    public static <T> T get(UUID uuid, String name, int data) {
        if (!playerVariables.containsKey(uuid)) return null;

        for (Variable variable : playerVariables.get(uuid)) {
            if (variable.name.equals(name) && variable.data == data) {
                return variable.getValue();
            }
        }
        return null;
    }

    public static <T> T get(UUID uuid, String name, int data, T defaultValue) {
        T output = get(uuid, name, data);
        return output == null ? defaultValue : output;
    }

    public static <T> T get(String name, int data) {
        List<Variable> output = variables.stream().filter(
                v -> v.name.equals(name) && v.data == data
        ).collect(Collectors.toList());
        if (output.size() > 0)
            return output.get(0).getValue();
        return null;
    }

    public static <T> void set(UUID uuid, String name, int data, T value) {
        List<Variable> variables = playerVariables.getOrDefault(uuid, new ArrayList<>());
        boolean did = false;
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).name.equals(name) && variables.get(i).data == data) {
                did = true;
                variables.set(i, variables.get(i).setValue(value));
            }
        }
        if (!did) {
            variables.add(new Variable(name, data, Serializer.serialize(value)));
            playerVariables.put(uuid, variables);
        }
    }

    public static <T> void set(String name, int data, T value) {
        boolean did = false;
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).name.equals(name) && variables.get(i).data == data) {
                did = true;
                variables.set(i, variables.get(i).setValue(value));
            }
        }
        if (!did) {
            variables.add(new Variable(name, data, Serializer.serialize(value)));
        }
    }

    public static void delete(UUID uuid, String name, int data) {
        playerVariables.getOrDefault(uuid, new ArrayList<>()).removeIf(v -> v.name.equals(name) && v.data == data);
    }

    public static void delete(UUID uuid, String name) {
        playerVariables.getOrDefault(uuid, new ArrayList<>()).removeIf(v -> v.name.equals(name));
    }

    public static void delete(String name, int data) {
        variables.removeIf(v -> v.name.equals(name) && v.data == data);
    }

    public static long getSize(UUID uuid, String name) {
        return playerVariables.getOrDefault(uuid, new ArrayList<>()).stream().filter(v -> v.name.equals(name)).count();
    }

    public static long getSize(String name) {
        return variables.stream().filter(v -> v.name.equals(name)).count();
    }

    public static void save() {
        EntitySD.saveLocations();
        for (PlayerSD player : SkyblockDragons.players.values()) {
            player.save();
        }

        try {
            Gson gson = new Gson();
            for (UUID uuid : playerVariables.keySet()) {
                FileWriter fileWriter = new FileWriter(SkyblockDragons.plugin.getDataFolder().getAbsoluteFile() + "/Players/" + uuid + ".json");
                String json = gson.toJson(playerVariables.get(uuid));
                fileWriter.write(json);
                fileWriter.close();
            }
            FileWriter fileWriter = new FileWriter(SkyblockDragons.plugin.getDataFolder().getAbsoluteFile() + "/Variables.json");
            String json = gson.toJson(variables);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        variables.clear();
        try {
            File file = new File(SkyblockDragons.plugin.getDataFolder().getAbsolutePath() + "/Players");
            if (file.list() != null) {
                for (String path : file.list()) {
                    BufferedReader reader = new BufferedReader(new FileReader(SkyblockDragons.plugin.getDataFolder().getAbsoluteFile() + "/Players/" + path));
                    String json = reader.readLine();
                    JsonReader jsonReader = new JsonReader(new StringReader(json));
                    JsonParser jsonParser = new JsonParser();
                    JsonArray array = jsonParser.parse(jsonReader).getAsJsonArray();

                    String[] names = path.split("/");
                    UUID uuid = UUID.fromString(names[names.length - 1].replace(".json", ""));

                    List<Variable> variables = new ArrayList<>();
                    for (JsonElement variable : array) {
                        String name = variable.getAsJsonObject().get("name").getAsString();
                        int data = variable.getAsJsonObject().get("data").getAsInt();
                        String value = variable.getAsJsonObject().get("value").getAsString();
                        variables.add(new Variable(name, data, value));
                    }
                    playerVariables.put(uuid, variables);
                }
            }
            BufferedReader reader = new BufferedReader(new FileReader(SkyblockDragons.plugin.getDataFolder().getAbsoluteFile() + "/Variables.json"));
            String json = reader.readLine();
            JsonReader jsonReader = new JsonReader(new StringReader(json));
            JsonParser jsonParser = new JsonParser();
            JsonArray array = jsonParser.parse(jsonReader).getAsJsonArray();

            for (JsonElement variable : array) {
                String name = variable.getAsJsonObject().get("name").getAsString();
                int data = variable.getAsJsonObject().get("data").getAsInt();
                String value = variable.getAsJsonObject().get("value").getAsString();
                variables.add(new Variable(name, data, value));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

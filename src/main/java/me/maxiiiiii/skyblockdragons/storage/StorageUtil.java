package me.maxiiiiii.skyblockdragons.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import me.maxiiiiii.hypixelitems.HypixelItems;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class StorageUtil {
    public static ArrayList<Variable> variables = new ArrayList<>();

    public static void loadVariables() throws IOException {
        variables.clear();
        File file = new File(HypixelItems.getInstance().getDataFolder().getAbsolutePath() + "/Variables.json");
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file.getAbsolutePath()));
        String json = bufferedReader.readLine();
        JsonReader reader = new JsonReader(new StringReader(json));
        // reader.setLenient(true);
        JsonParser parser = new JsonParser();
        JsonArray arr = parser.parse(reader).getAsJsonArray();
        // JSONArray arr = new JSONArray(json);
        // JSONArray object = new Gson().fromJson(json, JSONArray.class);
        // JSONArray scam = new JSONArray(json);
        // LOGGER.info("Your File Has {}", arr);
        for (JsonElement variable : arr) {
            UUID uuid = UUID.fromString(variable.getAsJsonObject().get("UUID").getAsString());
            String id = variable.getAsJsonObject().get("ID").getAsString();
            String value = variable.getAsJsonObject().get("value").getAsString();
            int data = variable.getAsJsonObject().get("data").getAsInt();
            variables.add(new Variable(uuid, id, value, data));
        }
        /*for (Variable variable : variables) {
            LOGGER.info("{} {} {}",variable.getId(),variable.getValue(),variable.getSlot());
        }*/
    }

    public static Variable createVariable(UUID uuid, String id, String value) {
        Variable data =  new Variable(uuid, id, value);
        variables.add(data);

        try {
            saveVariables();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static Variable createVariable(UUID uuid, String id, String value, int data) {
        Variable variable =  new Variable(uuid, id, value, data);
        variables.add(variable);

        try {
            saveVariables();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return variable;
    }

    public static Variable getVariable(String value) {
        for (Variable variable : variables) {
            if (variable.getValue().equalsIgnoreCase(value)) {
                return variable;
            }
        }
        return null;
    }

    public static Variable getVariable(UUID uuid, String id, int data) {
        for (Variable variable : variables) {
            if (variable.getUUID().equals(uuid) && variable.getId().equalsIgnoreCase(id) && variable.getData() == data) {
                return variable;
            }
        }
        return null;
    }

    public static String getVariableValue(UUID uuid, String id, int data, String defaultValue) {
        for (Variable variable : variables) {
            if (variable.getUUID().equals(uuid) && variable.getId().equalsIgnoreCase(id) && variable.getData() == data) {
                return variable.getValue();
            }
        }
        return defaultValue;
    }

    public static String getVariableValue(UUID uuid, String id, String defaultValue) {
        for (Variable variable : variables) {
            if (variable.getUUID().equals(uuid) && variable.getId().equalsIgnoreCase(id)) {
                return variable.getValue();
            }
        }
        return defaultValue;
    }

    public static Object getVariableOrDefault(UUID uuid, String id, int data, Object defaultValue) {
        for (Variable variable : variables) {
            if (variable.getUUID().equals(uuid) && variable.getId().equalsIgnoreCase(id) && variable.getData() == data) {
                return variable;
            }
        }
        return defaultValue;
    }

    public static Variable getVariable(UUID uuid, String id) {
        for (Variable variable : variables) {
            if (variable.getUUID().equals(uuid) && variable.getId().equalsIgnoreCase(id)) {
                return variable;
            }
        }
        return null;
    }

    public static ArrayList<Variable> getVariables() {
        return variables;
    }

    public static Variable setVariable(UUID uuid, String id, Variable newVariable) {
        for (Variable variable : variables) {
            if (variable.getUUID().equals(uuid) && variable.getId().equalsIgnoreCase(id)) {
                variable.setValue(newVariable.getValue());
                return variable;
            }
        }
        return null;
    }

    public static Variable setVariable(UUID uuid, String id, String value) {
        return setVariable(uuid, id, value, -1);
    }

    public static Variable setVariable(UUID uuid, String id, String value, int data) {
        int a = 0;
        for (Variable variable : variables) {
            if (variable.getUUID().equals(uuid) && variable.getId().equalsIgnoreCase(id) && variable.getData() == data) {
                variables.get(a).setValue(value);
                variables.get(a).setData(data);
                try {
                    saveVariables();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return variable;
            }
            a++;
        }
        createVariable(uuid, id, value, data);
        return null;
    }
    
    public static void deleteVariable(UUID uuid, String id, int data) {
        for (Variable variable : variables) {
            if (variable.getUUID().equals(uuid) && variable.getId().equalsIgnoreCase(id) && variable.getData() == data) {
                variables.remove(variable);
                break;
            }
        }
        try {
            saveVariables();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveVariables(boolean message) throws IOException {

        Gson gson = new Gson();
        File file = new File(HypixelItems.getPlugin().getDataFolder().getAbsolutePath() + "/Variables.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(variables, writer);
        writer.flush();
        writer.close();
        if (message) System.out.println("Variables saved.");
    }

    public static void saveVariables() throws IOException {
        saveVariables(false);
    }

    public static void clearVariables() {
        try {
            saveVariables();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

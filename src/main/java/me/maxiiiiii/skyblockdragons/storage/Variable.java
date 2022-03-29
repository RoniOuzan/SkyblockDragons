package me.maxiiiiii.skyblockdragons.storage;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Variable {
    private final UUID UUID;
    private final String ID;
    private String value;
    private int data;

    public Variable(UUID uuid, String id, String value, int data) {
        this.ID = id;
        this.UUID = uuid;
        this.value = value;
        this.data = data;
    }

    public Variable(UUID uuid, String id, String value) {
        this(uuid, id, value,-1);
    }

    public Variable(String id, String value) {
        this(null, id, value,-1);
    }

    public Variable(String id, String value, int data) {
        this(null, id, value,data);
    }

    public String getId() {
        try {
            return ID;
        } catch (NullPointerException e) {
            return "";
        }
    }

    public String getValueOrDefault(String defaultValue) {
        if (value == null)
            return defaultValue;
        return this.value;
    }
}

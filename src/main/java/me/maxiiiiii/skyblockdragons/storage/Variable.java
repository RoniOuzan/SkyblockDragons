package me.maxiiiiii.skyblockdragons.storage;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.serialization.Serializer;
import org.bukkit.Bukkit;

public class Variable {
    public String name;
    public int data;
    public String value;

    public Variable(String name, int data, String value) {
        this.name = name;
        this.data = data;
        this.value = value;
    }

    public <T> Variable setValue(T value) {
        this.value = Serializer.serialize(value);
        return this;
    }

    public <T> T getValue() {
        return Serializer.deserialize(this.value);
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name=" + name +
                "data=" + data +
                "value=" + value +
                "}";
    }
}

package me.maxiiiiii.skyblockdragons.storage2;

import java.util.UUID;

public class Variable<T> {
    public UUID uuid;
    public String id;
    public T value;
    public Object data;

    public Variable(UUID uuid, String id, Object data, T value) {
        this.uuid = uuid;
        this.id = id;
        this.value = value;
        this.data = data;
    }
}

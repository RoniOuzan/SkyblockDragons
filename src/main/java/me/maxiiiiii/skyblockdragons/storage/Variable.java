package me.maxiiiiii.skyblockdragons.storage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;

import java.io.Serializable;
import java.util.UUID;

public class Variable<T> {
    public UUID uuid;
    public String id;
    public int data;
    public T value;

    public Variable(UUID uuid, String id, int data, T value) {
        this.uuid = uuid;
        this.id = id;
        this.data = data;
        this.value = value;
    }
}

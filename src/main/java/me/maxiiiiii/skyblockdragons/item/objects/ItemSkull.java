package me.maxiiiiii.skyblockdragons.item.objects;

import java.util.function.Supplier;

public class ItemSkull {
    private final Supplier<String> id;
    private final Supplier<String> value;

    public ItemSkull(Supplier<String> id, Supplier<String> value) {
        this.id = id;
        this.value = value;
    }

    public ItemSkull(String id, String value) {
        this(() -> id, () -> value);
    }

    public String getValue() {
        return value.get();
    }

    public String getId() {
        return id.get();
    }
}

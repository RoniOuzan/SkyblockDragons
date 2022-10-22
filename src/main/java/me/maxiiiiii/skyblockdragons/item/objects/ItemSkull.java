package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class ItemSkull {
    private final Supplier<String> id;
    private final Supplier<String> nbt;

    public ItemSkull(Supplier<String> id, Supplier<String> nbt) {
        this.id = id;
        this.nbt = nbt;
    }

    public ItemSkull(String id, String nbt) {
        this(() -> id, () -> nbt);
    }
}

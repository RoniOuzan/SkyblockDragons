package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Data;
import me.maxiiiiii.skyblockdragons.item.drops.Drop;
import org.bukkit.Material;

@Data
public class CropAdder {
    private Material block;
    private Drop drop;
    private int adder;

    public CropAdder(Material block, Drop drop, int adder) {
        this.block = block;
        this.drop = drop;
        this.adder = adder;
    }
}

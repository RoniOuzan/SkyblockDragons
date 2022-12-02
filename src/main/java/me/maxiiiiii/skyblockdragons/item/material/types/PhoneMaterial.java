package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Material;

import java.util.function.Function;

public abstract class PhoneMaterial extends ToolMaterial {
    public PhoneMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Function<PlayerSD, String> description, MaterialModifier... objects) {
        super(itemID, material, family, name, type, rarity, description, objects);
    }

    public PhoneMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, MaterialModifier... objects) {
        super(itemID, material, family, name, type, rarity, description, objects);
    }
}

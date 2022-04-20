package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class WeaponMaterial extends ToolMaterial implements ItemStatsAble {
    private Stats stats;
    private String description;

    public WeaponMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice, Stats stats, String description, Object... objects) {
        super(material, family, name, type, rarity, id, nbt, sellPrice, description, objects);
        this.stats = stats;
        this.description = description;
    }
}

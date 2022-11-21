package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

@Getter
@Setter
public abstract class WeaponMaterial extends ToolMaterial implements ItemStatsAble {
    private Stats stats;

    public WeaponMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity, description, modifiers);
        this.stats = stats;
    }
}

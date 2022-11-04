package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

@Getter
public class CrystalMaterial extends ToolMaterial {
    private final CrystalType crystalType;
    private final int level;

    public CrystalMaterial(CrystalType crystalType, ItemFamily family, int level) {
        super(crystalType.getName().toUpperCase() + "_CRYSTAL_LEVEL_" + level,
                Material.SKULL_ITEM,
                family,
                crystalType.getName() + " Crystal Level " + level,
                ItemType.CRYSTAL,
                Rarity.getRarity(level),
                p -> "Apply on items in the Crystal Grinder. Adds "
        );
        this.crystalType = crystalType;
        this.level = level;
    }

    public Crystal getCrystal() {
        return new Crystal(crystalType, level);
    }
}

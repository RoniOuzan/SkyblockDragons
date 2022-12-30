package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.stone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class StoneArtifact extends AccessoryMaterial {
    public StoneArtifact() {
        super("STONE_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.STONE,
                "Stone Artifact",
                Rarity.RARE,
                new DamageStats(0, 6, 6, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("ff66bfb2-2649-4179-aa68-56068c2048e4",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGMxMTY5MWJhN2M0ZGNiYWYzNDdiOWFkZWJmZTkzM2NlMzAxNjcxN2I1NjUzZTQxMjJhMTJiMDI2YjM3ZmExOCJ9fX0="
        );
    }
}

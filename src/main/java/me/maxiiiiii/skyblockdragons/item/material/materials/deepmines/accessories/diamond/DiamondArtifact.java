package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.diamond;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class DiamondArtifact extends AccessoryMaterial {
    public DiamondArtifact() {
        super("DIAMOND_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.DIAMOND,
                "Diamond Artifact",
                Rarity.RARE,
                new Stats(0, 8, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("70beb9aa-9736-410a-ab1a-a71a4e434076",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAwYjI2YTQyZGYxM2M3Njk5NDJiMDE3MjdlMGE0MjA1YmJkNTZjNjFjNWZiZDI1Y2UzNWYzZDc0NzhjNzNiOCJ9fX0="
        );
    }
}

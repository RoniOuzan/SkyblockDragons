package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.coal;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class CoalArtifact extends AccessoryMaterial {
    public CoalArtifact() {
        super("COAL_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.COAL,
                "Coal Artifact",
                Rarity.RARE,
                new DamageStats(0, 8, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("c7b9f611-64c6-4e9c-ac97-8dedf8b97e17",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjZjNWVjYWM5NDJjNzdiOTVhYjQ2MjBkZjViODVlMzgwNjRjOTc0ZjljNWM1NzZiODQzNjIyODA2YTQ1NTcifX19"
        );
    }
}

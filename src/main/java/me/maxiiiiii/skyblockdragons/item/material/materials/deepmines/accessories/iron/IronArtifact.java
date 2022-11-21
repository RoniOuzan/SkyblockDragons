package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.iron;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class IronArtifact extends AccessoryMaterial {
    public IronArtifact() {
        super("IRON_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.IRON,
                "Iron Artifact",
                Rarity.RARE,
                new Stats(0, 8, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("d909eb70-59b9-48ec-a4d0-a947d91d3c52",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZjYTZmOWY4ZTZiMzE0Nzg0N2JiZGViMTBhNTI3NzM5NmI5YjA1ZDA3NjMyNjczNmY3ZjBiMmJmZDZkMWE2NCJ9fX0="
        );
    }
}

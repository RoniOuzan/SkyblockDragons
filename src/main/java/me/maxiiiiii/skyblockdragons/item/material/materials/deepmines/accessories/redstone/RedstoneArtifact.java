package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.redstone;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class RedstoneArtifact extends AccessoryMaterial {
    public RedstoneArtifact() {
        super("REDSTONE_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.REDSTONE,
                "Redstone Artifact",
                Rarity.RARE,
                new Stats(0, 8, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("544b201d-46ec-44b7-8555-a761471fbc7b",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzE2ODI5MWFiYWM0YTVmODZmZThiMzYwMzM4OTg2YWVlN2FiY2I3ZjRiODE2OWViNTVkZmVjOTI4NTYxMjU4In19fQ=="
        );
    }
}

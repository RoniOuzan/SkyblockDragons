package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.cobblestone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class CobblestoneArtifact extends AccessoryMaterial {
    public CobblestoneArtifact() {
        super("COBBLESTONE_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.COBBLESTONE,
                "Cobblestone Artifact",
                Rarity.RARE,
                new DamageStats(0, 6, 6, 2, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("6e2b29e1-0582-498a-8322-12614dbf905b",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODVmM2U0ZTNkODQ4NDJkN2Y0YmEwMGE5ZWRlYzM5ZDhhZTY1NzM4ZTc1NTAyNGQ2ZWJmYTFlMmRmNjk4ZGVhMyJ9fX0="
        );
    }
}

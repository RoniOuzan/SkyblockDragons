package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.emerald;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class EmeraldArtifact extends AccessoryMaterial {
    public EmeraldArtifact() {
        super("EMERALD_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.EMERALD,
                "Emerald Artifact",
                Rarity.RARE,
                new DamageStats(0, 8, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("5507ab36-636a-4d40-a74a-056156e5c03f",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTk2MGQ2ZmZhZjQ0ZThhZmNiZGY4YjI5YTc3ZDg0Y2UyMmM3MWQwMGM2NGJmZDk5YWYzNDBhNjk1MzViZmQ3In19fQ=="
        );
    }
}

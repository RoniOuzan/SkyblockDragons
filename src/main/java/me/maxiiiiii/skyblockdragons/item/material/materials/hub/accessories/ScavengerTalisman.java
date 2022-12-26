package me.maxiiiiii.skyblockdragons.item.material.materials.hub.accessories;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class ScavengerTalisman extends AccessoryMaterial {
    public ScavengerTalisman() {
        super("SCAVENGER_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.NULL,
                "Scavenger Talisman",
                Rarity.COMMON,
                new DamageStats(0, 1, 0, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("d154a69b-a5da-3a92-a217-259ce5b41c1a",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjExYWIzYTExMzJjOWQxZWY4MzVlYTgxZDk3MmVkOWI1Y2Q4ZGRmZjBhMDdjNTVhNzQ5YmNmY2Y4ZGY1In19fQ=="
        );
    }
}

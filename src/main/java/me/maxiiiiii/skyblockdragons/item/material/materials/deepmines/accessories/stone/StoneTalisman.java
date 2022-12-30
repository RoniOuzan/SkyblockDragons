package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.stone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class StoneTalisman extends AccessoryMaterial {
    public StoneTalisman() {
        super("STONE_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.STONE,
                "Stone Talisman",
                Rarity.COMMON,
                new DamageStats(0, 2, 2, 1, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("6bfd6d28-12d2-482c-8bbe-d144888419b1",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGUxNDBkYzMwZTdiZDU3ZDBjNWZmNzFhNjgxODUwMDQzNDY5MmY0OTI0NzlhNTQyNmFhMjMxZDdhZDk5NGQ4YSJ9fX0="
        );
    }
}

package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.redstone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class RedstoneTalisman extends AccessoryMaterial {
    public RedstoneTalisman() {
        super("REDSTONE_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.REDSTONE,
                "Redstone Talisman",
                Rarity.COMMON,
                new DamageStats(0, 2, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("97a050b5-da9b-4d60-bc4c-2bd07661494b",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjMyY2NmNzgxNDUzOWE2MWY4YmZjMTViY2YxMTFhMzlhZDhhZTE2M2MzNmU0NGI2Mzc5NDE1NTU2NDc1ZDcyYSJ9fX0="
        );
    }
}

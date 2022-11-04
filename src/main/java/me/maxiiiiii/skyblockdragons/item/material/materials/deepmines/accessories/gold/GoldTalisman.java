package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.gold;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class GoldTalisman extends AccessoryMaterial {
    public GoldTalisman() {
        super("GOLD_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.GOLD,
                "Gold Talisman",
                Rarity.COMMON,
                new Stats(0, 2, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("46bd18e7-4511-45b4-b1ae-783a4d9224a0",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODViNGFiZDRmMDdiNjg5NDYwN2NiZDg3MDg2OGY2N2UwMjVjN2ZiNTUyYTFhNTdmNTZmNzdjMDQ0Y2NhNDFjZSJ9fX0="
        );
    }
}

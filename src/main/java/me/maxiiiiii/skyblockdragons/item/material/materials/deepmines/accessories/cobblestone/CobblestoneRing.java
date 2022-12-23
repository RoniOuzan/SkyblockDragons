package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.cobblestone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class CobblestoneRing extends AccessoryMaterial {
    public CobblestoneRing() {
        super("COBBLESTONE_RING",
                Material.SKULL_ITEM,
                ItemFamily.COBBLESTONE,
                "Cobblestone Ring",
                Rarity.UNCOMMON,
                new DamageStats(0, 4, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("32289766-a220-44e6-9076-1283a6c53528",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ5MjM4ZWZjOTM0OTNiMTRhNTgyNjM5ZWIwYWE4ODM0ZWFhNDhlMTBiZDRjMjM0ZWIxYTRjMzYzYjQzZDViIn19fQ=="
        );
    }
}

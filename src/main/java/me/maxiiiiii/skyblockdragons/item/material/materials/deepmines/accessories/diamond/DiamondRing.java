package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.diamond;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class DiamondRing extends AccessoryMaterial {
    public DiamondRing() {
        super("DIAMOND_RING",
                Material.SKULL_ITEM,
                ItemFamily.DIAMOND,
                "Diamond Ring",
                Rarity.UNCOMMON,
                new DamageStats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("fe765590-5398-4bcb-bc6f-3157e1fa8b82",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2UwYWEzYTk1MjVkODY0NmYwNmIxMmE1NGExOTc3MGVhZjMyMDA1N2M5OGViZjYzZTY2M2ZkZTJkOWQ5YjEzMSJ9fX0="
        );
    }
}

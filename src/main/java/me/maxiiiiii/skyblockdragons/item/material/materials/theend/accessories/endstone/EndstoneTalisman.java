package me.maxiiiiii.skyblockdragons.item.material.materials.theend.accessories.endstone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class EndstoneTalisman extends AccessoryMaterial {
    public EndstoneTalisman() {
        super("ENDSTONE_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.ENDSTONE,
                "Endstone Talisman",
                Rarity.COMMON,
                new DamageStats(0, 3, 3, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("db796623-84df-4c1d-8689-e118b0237ccb",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDI4MTNjZjhjNGZkMDEzYmFmNWJmNTVhOGM5MzEyMWM0ODJkYTFjZjZlMTA1NGMxODBmZjNlODE3MjdiNjVkZCJ9fX0="
        );
    }
}

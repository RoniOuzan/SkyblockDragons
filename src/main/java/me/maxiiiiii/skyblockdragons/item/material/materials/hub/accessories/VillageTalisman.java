package me.maxiiiiii.skyblockdragons.item.material.materials.hub.accessories;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class VillageTalisman extends AccessoryMaterial {
    public VillageTalisman() {
        super("VILLAGE_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.NULL,
                "Village Talisman",
                Rarity.COMMON,
                new DamageStats(0, 1, 0, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("b03b72e1-8b61-30c6-8a0f-b0432d07fb21",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWMxMWQ2Yzc5YjhhMWYxODkwMmQ3ODNjZGRhNGJkZmI5ZDQ3MzM3YjczNzkxMDI4YTEyNmE2ZTZjZjEwMWRlZiJ9fX0="
        );
    }
}

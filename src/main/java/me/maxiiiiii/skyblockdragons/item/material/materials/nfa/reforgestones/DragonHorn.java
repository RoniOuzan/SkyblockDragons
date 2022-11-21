package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.reforgestones;

import me.maxiiiiii.skyblockdragons.item.material.types.ReforgeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class DragonHorn extends ReforgeMaterial {
    public DragonHorn() {
        super("DRAGON_HORN",
                Material.SKULL_ITEM,
                ItemFamily.REFORGE_STONE,
                "Dragon Horn",
                Rarity.EPIC,
                "Renowned"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("e63501f5-9f3e-49cd-ac39-c976bc94e18e",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc0NGM4MTA5OWMxYWViZjIwZjY3N2ZhZWQyNGNhY2U1MjBhMjk0Y2Y0NmJkZWI2YTI1N2Y0MzZhMzIzYTFkOCJ9fX0="
        );
    }
}

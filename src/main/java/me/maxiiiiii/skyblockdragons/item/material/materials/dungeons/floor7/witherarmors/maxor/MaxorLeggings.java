package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.maxor;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

public class MaxorLeggings extends ArmorMaterial {
    public MaxorLeggings() {
        super("MAXOR_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.MAXOR_ARMOR,
                "Maxor's Leggings",
                ItemType.LEGGINGS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 45, 0, 0, 0, 230, 105, 30, 10),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                ItemFullSetBonus.WITHER_ARMOR_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(93,47,185);
    }
}

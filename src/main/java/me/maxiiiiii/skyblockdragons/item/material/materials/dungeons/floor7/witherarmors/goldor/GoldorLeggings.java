package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.goldor;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

public class GoldorLeggings extends ArmorMaterial {
    public GoldorLeggings() {
        super("GOLDOR_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.GOLDOR_ARMOR,
                "Goldor's Leggings",
                ItemType.LEGGINGS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 250, 220, 0, 20),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                ItemFullSetBonus.WITHER_ARMOR_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(101,96,90);
    }
}

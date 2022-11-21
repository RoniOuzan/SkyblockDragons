package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.necron;

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

public class NecronBoots extends ArmorMaterial {
    public NecronBoots() {
        super("NECRON_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.NECRON_ARMOR,
                "Necron's Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 40, 30, 0, 0, 0, 145, 85, 0, 10),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                ItemFullSetBonus.WITHER_ARMOR_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(231,110,60);
    }
}

package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.maxor;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

public class MaxorChestplate extends ArmorMaterial {
    public MaxorChestplate() {
        super("MAXOR_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.MAXOR_ARMOR,
                "Maxor's Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new Stats(0, 0, 45, 0, 0, 0, 260, 110, 30, 15),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                ItemFullSetBonus.WITHER_ARMOR_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(74,20,183);
    }
}

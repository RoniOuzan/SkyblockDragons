package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.goldor;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

public class GoldorChestplate extends ArmorMaterial {
    public GoldorChestplate() {
        super("GOLDOR_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.GOLDOR_ARMOR,
                "Goldor's Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 310, 275, 0, 20),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                ItemFullSetBonus.WITHER_ARMOR_FULL_SET
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(69,65,60);
    }
}

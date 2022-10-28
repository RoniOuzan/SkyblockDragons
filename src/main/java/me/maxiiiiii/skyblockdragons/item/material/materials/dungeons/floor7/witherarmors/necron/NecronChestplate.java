package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.necron;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.WitherArmorFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

public class NecronChestplate extends ArmorMaterial {
    public NecronChestplate() {
        super("NECRON_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.NECRON_ARMOR,
                "Necron's Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new Stats(0, 40, 30, 0, 0, 0, 260, 140, 0, 10),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                new WitherArmorFullSetBonus()
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(231,65,60);
    }
}
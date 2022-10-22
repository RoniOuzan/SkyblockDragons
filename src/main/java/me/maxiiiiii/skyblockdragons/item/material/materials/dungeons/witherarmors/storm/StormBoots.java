package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.witherarmors.storm;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.witherarmors.WitherArmorFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSet;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

public class StormBoots extends ArmorMaterial {
    public StormBoots() {
        super("STORM_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.STORM_ARMOR,
                "Storm's Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 145, 65, 0, 250),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                new WitherArmorFullSetBonus()
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(28,212,228);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

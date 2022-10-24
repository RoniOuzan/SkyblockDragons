package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.storm;

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

public class StormChestplate extends ArmorMaterial {
    public StormChestplate() {
        super("STORM_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.STORM_ARMOR,
                "Storm's Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 260, 120, 0, 250),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                new WitherArmorFullSetBonus()
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(23,147,196);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

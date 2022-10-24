package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.maxor;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.WitherArmorFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class MaxorHelmet extends ArmorMaterial {
    public MaxorHelmet() {
        super("MAXOR_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.MAXOR_ARMOR,
                "Maxor's Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 0, 45, 0, 0, 0, 180, 80, 30, 15),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                new WitherArmorFullSetBonus()
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("51932520-2800-4162-9fcf-2898ba93a1a2",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ1MGE4ZmQ3YTFkZDQ3MWU4ZjkyOTJmMTI4M2U5OWM1ZTUyYTUxODYzMDJiZmYzYTkzZjgwN2ZhOWI0NDJhNCJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

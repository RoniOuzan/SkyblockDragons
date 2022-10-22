package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.witherarmors.goldor;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.witherarmors.WitherArmorFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class GoldorHelmet extends ArmorMaterial {
    public GoldorHelmet() {
        super("GOLDOR_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.GOLDOR_ARMOR,
                "Goldor's Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 210, 180, 0, 30),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                new WitherArmorFullSetBonus()
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("ab7539c4-b79d-4a7b-9b1b-2a855bc4b210",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM5OGNmM2Y2MzEyNDJlZjNhOTgxODRkZDk0MjgzZjMxOGZiYzJkOGM4ZDNhNDI5NzM0MWIwM2ZkM2FmNmZlMCJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

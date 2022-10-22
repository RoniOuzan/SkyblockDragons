package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.goldenskeleton;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.bows.GoldenSkeletonBow;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

public class GoldenSkeletonHelmet extends ArmorMaterial {
    public GoldenSkeletonHelmet() {
        super("GOLDEN_SKELETON_HELMET",
                Material.GOLD_HELMET,
                ItemFamily.NULL,
                "Golden Skeleton Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 25, 25, 0, 5),
                "Deals " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.WHITE + "Golden Skeleton Bow" + ChatColor.GRAY + ".",
                null
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(5, 240, 15);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {
        if (entityDamage.getEquipment().getTool().getMaterial() instanceof GoldenSkeletonBow) {
            entityDamage.getMultiplier().addBaseMultiplier(20);
        }
    }
}

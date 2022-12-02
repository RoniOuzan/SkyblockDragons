package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.goldenskeleton;

import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.bows.GoldenSkeletonBow;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.SurvivorStats;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class GoldenSkeletonHelmet extends ArmorMaterial {
    public GoldenSkeletonHelmet() {
        super("GOLDEN_SKELETON_HELMET",
                Material.GOLD_HELMET,
                ItemFamily.NULL,
                "Golden Skeleton Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new SurvivorStats(25, 25, 0, 0, 0, 5),
                "Deals " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.WHITE + "Golden Skeleton Bow" + ChatColor.GRAY + "."
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(5, 240, 15);
    }

    @EventHandler
    public void updateDamage(UpdateEntityDamageEntityEvent e) {
        if (!(e.getDamage().getAttackerEquipment().getHelmetMaterial() instanceof GoldenSkeletonHelmet)) return;

        if (e.getDamage().getAttackerEquipment().getToolMaterial() instanceof GoldenSkeletonBow) {
            e.getDamage().getMultiplier().addBase(20);
        }
    }
}

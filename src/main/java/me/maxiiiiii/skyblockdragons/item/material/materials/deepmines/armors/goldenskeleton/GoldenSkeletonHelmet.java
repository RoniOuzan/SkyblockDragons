package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.goldenskeleton;

import me.maxiiiiii.skyblockdragons.events.events.update.UpdateEntityDamageEvent;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.bows.GoldenSkeletonBow;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
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
                new Stats(0, 0, 0, 0, 0, 0, 25, 25, 0, 5),
                "Deals " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.WHITE + "Golden Skeleton Bow" + ChatColor.GRAY + "."
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(5, 240, 15);
    }

    @EventHandler
    public void updateDamage(UpdateEntityDamageEvent e) {
        if (e.getEntityDamage().getEquipment().getTool().getMaterial() instanceof GoldenSkeletonBow) {
            e.getEntityDamage().getMultiplier().addBase(20);
        }
    }
}

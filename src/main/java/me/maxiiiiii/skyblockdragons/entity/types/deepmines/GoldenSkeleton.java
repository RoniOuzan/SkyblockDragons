package me.maxiiiiii.skyblockdragons.entity.types.deepmines;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.Drop;
import me.maxiiiiii.skyblockdragons.item.objects.RareDrop;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class GoldenSkeleton extends EntityMaterial {
    public GoldenSkeleton() {
        super(
                EntityType.SKELETON,
                ChatColor.GOLD + "Golden Skeleton",
                1,
                50,
                0,
                20,
                0,
                new Equipment(Material.GOLD_HELMET, null, null, null, Material.BOW, null),
                105,
                0,
                4,
                0.5,
                new RareDrop(Items.get("GOLDEN_SKELETON_BOW"), 1, 2d),
                new RareDrop(Items.get("GOLDEN_SKELETON_HELMET"), 1, 2d),
                new Drop(Items.get("BONE"), 1),
                new Drop(Items.get("GOLD_INGOT"), 1, 50),
                new Drop(Items.get("ARROW"), 2, 30)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {

    }
}

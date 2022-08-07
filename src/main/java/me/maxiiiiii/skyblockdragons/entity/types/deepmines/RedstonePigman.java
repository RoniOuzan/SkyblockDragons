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

public class RedstonePigman extends EntityMaterial {
    public RedstonePigman() {
        super(
                EntityType.PIG_ZOMBIE,
                ChatColor.RED + "Redstone Pigman",
                3,
                150,
                5,
                150,
                0,
                new Equipment(null, null, null, null, Material.GOLD_SWORD, null),
                125,
                0,
                10,
                1,
                new Drop(Items.get("ROTTEN_FLESH"), 1, 6),
                new Drop(Items.get("REDSTONE"), 1, 5),
                new RareDrop(Items.get("ENCHANTED_REDSTONE"), 1, 2, 1d),
                new RareDrop(Items.get("PIGMAN_HELMET"), 1, 2d),
                new RareDrop(Items.get("PIGMAN_CHESTPLATE"), 1, 2d),
                new RareDrop(Items.get("PIGMAN_LEGGINGS"), 1, 2d),
                new RareDrop(Items.get("PIGMAN_BOOTS"), 1, 2d),
                new RareDrop(Items.get("PIGMAN_DAGGER"), 1, 2d),
                new RareDrop(Items.get("REDSTONE_TALISMAN"), 1, 0.5d)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {

    }
}

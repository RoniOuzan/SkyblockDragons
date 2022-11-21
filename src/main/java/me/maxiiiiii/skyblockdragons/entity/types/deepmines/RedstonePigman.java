package me.maxiiiiii.skyblockdragons.entity.types.deepmines;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemRareDrop;
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
                new EntityItemDrop(Items.get("ROTTEN_FLESH"), 1),
                new EntityItemDrop(Items.get("REDSTONE"), 1, 50),
                new EntityItemRareDrop(Items.get("ENCHANTED_REDSTONE"), 1, 2, 1d),
                new EntityItemRareDrop(Items.get("PIGMAN_HELMET"), 1, 2d),
                new EntityItemRareDrop(Items.get("PIGMAN_CHESTPLATE"), 1, 2d),
                new EntityItemRareDrop(Items.get("PIGMAN_LEGGINGS"), 1, 2d),
                new EntityItemRareDrop(Items.get("PIGMAN_BOOTS"), 1, 2d),
                new EntityItemRareDrop(Items.get("PIGMAN_DAGGER"), 1, 2d),
                new EntityItemRareDrop(Items.get("REDSTONE_TALISMAN"), 1, 0.5d)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {

    }
}

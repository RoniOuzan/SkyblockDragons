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

public class DiamondZombie extends EntityMaterial {
    public DiamondZombie() {
        super(
                EntityType.ZOMBIE,
                ChatColor.AQUA + "Diamond Zombie",
                8,
                300,
                10,
                200,
                0,
                new Equipment(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD, null),
                120,
                0.1,
                25,
                3,
                new Drop(Items.get("DIAMOND"), 1, 50),
                new RareDrop(Items.get("ENCHANTED_DIAMOND"), 1, 1d),
                new RareDrop(Items.get("DIAMOND_TALISMAN"), 1, 0.5d),
                new RareDrop(Items.get("DIAMOND_BLOCK_TALISMAN"), 1, 0.1d)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {

    }
}

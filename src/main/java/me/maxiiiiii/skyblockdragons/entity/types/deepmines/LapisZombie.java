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

public class LapisZombie extends EntityMaterial {
    public LapisZombie() {
        super(
                EntityType.ZOMBIE,
                ChatColor.BLUE + "Lapis Zombie",
                2,
                80,
                0,
                100,
                0,
                new Equipment(Material.LAPIS_BLOCK, null, null, null, Material.STONE_SWORD, null),
                110,
                0,
                5,
                1,
                new EntityItemDrop(Items.get("ROTTEN_FLESH"), 1),
                new EntityItemDrop(Items.get("LAPIS"), 1, 50),
                new EntityItemRareDrop(Items.get("ENCHANTED_LAPIS"), 1, 2, 2d),
                new EntityItemRareDrop(Items.get("LAPIS_TALISMAN"), 1, 0.5d)
        );
    }
}

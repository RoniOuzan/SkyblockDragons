package me.maxiiiiii.skyblockdragons.entity.types.hub;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class ZombieVillager extends EntityMaterial {
    public ZombieVillager() {
        super(EntityType.ZOMBIE_VILLAGER,
                ChatColor.RED + "Zombie Villager",
                3,
                100,
                0,
                30,
                0,
                new Equipment(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, null, null),
                100,
                0,
                8,
                1.2,
                new EntityItemDrop(Items.get("ROTTEN_FLESH"), 2, 4, 100)
        );
    }
}

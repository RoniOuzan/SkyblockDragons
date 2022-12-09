package me.maxiiiiii.skyblockdragons.entity.types.hub;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public class Zombie extends EntityMaterial {
    public Zombie() {
        super(EntityType.ZOMBIE,
                ChatColor.RED + "Zombie",
                1,
                50,
                0,
                5,
                0,
                new Equipment(),
                100,
                0,
                5,
                1,
                new EntityItemDrop(Items.get("ROTTEN_FLESH"), 1, 4, 100)
        );
    }
}

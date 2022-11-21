package me.maxiiiiii.skyblockdragons.entity.types.deepmines;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemRareDrop;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public class EmeraldCreeper extends EntityMaterial {
    public EmeraldCreeper() {
        super(
                EntityType.CREEPER,
                ChatColor.GREEN + "Emerald Creeper",
                5,
                100,
                15,
                100,
                0,
                new Equipment(),
                140,
                0,
                15,
                2,
                new EntityItemDrop(Items.get("GUNPOWDER"), 1),
                new EntityItemDrop(Items.get("EMERALD"), 1, 50),
                new EntityItemRareDrop(Items.get("EMERALD_TALISMAN"), 1, 0.5d)
        );
    }
}

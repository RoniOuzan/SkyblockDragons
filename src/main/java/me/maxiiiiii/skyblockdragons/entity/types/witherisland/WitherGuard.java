package me.maxiiiiii.skyblockdragons.entity.types.witherisland;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemRareDrop;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;

public class WitherGuard extends EntityMaterial {
    public WitherGuard() {
        super(
                EntityType.WITHER_SKELETON,
                ChatColor.BLACK + "Wither Guard",
                24,
                9_000,
                10,
                1000,
                0,
                new Equipment(),
                100,
                0,
                2500,
                25,
                new EntityItemDrop(Items.get("COAL"), 1, 3),
                new EntityItemRareDrop(Items.get("WITHER_SKULL"), 1, 0.5)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {
        entity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(6);
    }
}

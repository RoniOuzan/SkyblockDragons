package me.maxiiiiii.skyblockdragons.entity.types.deepermines;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ghost extends EntityMaterial {
    public Ghost() {
        super(
                EntityType.CREEPER,
                ChatColor.GRAY + "Ghost",
                30,
                30_000,
                0,
                500,
                10,
                new Equipment((Material) null, null, null, null, null, null),
                120,
                0,
                250,
                5,
                new EntityItemDrop(Items.get("DERNIC_SHARD"), 1, 1),
                new EntityItemDrop(Items.get("HEMATITE_SHARD"), 1, 0.2),
                new EntityItemDrop(Items.get("VOID_CRYSTAL_SHARD"), 1, 0.05)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {
        ((Creeper) entity.entity).setPowered(true);
        entity.entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
        ((Creeper) entity.entity).setMaxFuseTicks(Integer.MAX_VALUE);
    }
}

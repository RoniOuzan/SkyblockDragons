package me.maxiiiiii.skyblockdragons.entity.types.deepermines;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemRareDrop;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class IceMiner extends EntityMaterial {
    public IceMiner() {
        super(
                EntityType.ZOMBIE,
                ChatColor.AQUA + "Ice Miner",
                18,
                3_000,
                30,
                200,
                10,
                new Equipment(new ItemStack(Material.PACKED_ICE), Functions.setArmorColor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromBGR(3, 252, 248)), Functions.setArmorColor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromBGR(3, 252, 248)), Functions.setArmorColor(new ItemStack(Material.LEATHER_BOOTS), Color.fromBGR(3, 252, 248)), null, null),
                90,
                0.05,
                80,
                2,
                new EntityItemRareDrop(Items.get("COBALT_SHARD"), 1, 2),
                new EntityItemRareDrop(Items.get("CHLOROPHYTE_SHARD"), 1, 0.5),
                new EntityItemRareDrop(Items.get("LUMINATE_SHARD"), 1, 0.1)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {

    }
}

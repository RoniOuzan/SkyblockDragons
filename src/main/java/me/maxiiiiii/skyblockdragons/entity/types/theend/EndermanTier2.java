package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemRareDrop;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public class EndermanTier2 extends EntityMaterial {
    public EndermanTier2() {
        super(
                EntityType.ENDERMAN,
                ChatColor.WHITE + "Enderman Tier 2",
                18,
                2_000,
                0,
                400,
                0,
                new Equipment(),
                100,
                0,
                750,
                12,
                new EntityItemDrop(Items.get("ENDER_PEARL"), 1, 2),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_COMMON"), 1, 0.5),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_UNCOMMON"), 1, 0.2),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_RARE"), 1, 0.05),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_EPIC"), 1, 0.01)
        );
    }
}

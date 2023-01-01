package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public class EndermanTier2 extends EntityMaterial {
    public EndermanTier2() {
        super(
                EntityType.ENDERMAN,
                ChatColor.WHITE + "Enderman Tier 2",
                18,
                800,
                0,
                400,
                0,
                new Equipment(),
                100,
                0,
                750,
                12,
                new EntityItemDrop(Items.get("ENDER_PEARL"), 1, 2, 100),
                new EntityItemRareDrop(Items.get("EYE_OF_ENDERMAN"), 1, 2.5),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_COMMON"), 1, 1.2),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_UNCOMMON"), 1, 0.6),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_RARE"), 1, 0.3),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_EPIC"), 1, 0.15),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_LEGENDARY"), 1, 0.05)
        );
    }
}

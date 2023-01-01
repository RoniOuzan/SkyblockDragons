package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public class EndermanTier1 extends EntityMaterial {
    public EndermanTier1() {
        super(
                EntityType.ENDERMAN,
                ChatColor.WHITE + "Enderman Tier 1",
                15,
                500,
                0,
                350,
                0,
                new Equipment(),
                100,
                0,
                500,
                8,
                new EntityItemDrop(Items.get("ENDER_PEARL"), 1),
                new EntityItemRareDrop(Items.get("EYE_OF_ENDERMAN"), 1, 2),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_COMMON"), 1, 1),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_UNCOMMON"), 1, 0.5),
                new EntityItemRareDrop(Items.get("ENDERMAN_TALISMAN_RARE"), 1, 0.25)
        );
    }
}

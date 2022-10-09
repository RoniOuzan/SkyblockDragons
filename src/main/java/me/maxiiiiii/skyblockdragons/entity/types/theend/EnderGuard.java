package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.Drop;
import me.maxiiiiii.skyblockdragons.item.objects.RareDrop;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public class EnderGuard extends EntityMaterial {
    public EnderGuard() {
        super(
                EntityType.ENDERMAN,
                ChatColor.DARK_PURPLE + "Ender Guard",
                24,
                4_500,
                5,
                500,
                0,
                new Equipment(),
                100,
                0,
                1100,
                15,
                new Drop(Items.get("ENDER_PEARL"), 1, 3),
                new RareDrop(Items.get("SUMMONING_EYE"), 1, 1)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {

    }
}

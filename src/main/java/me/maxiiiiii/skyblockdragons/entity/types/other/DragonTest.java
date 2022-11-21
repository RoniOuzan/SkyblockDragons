package me.maxiiiiii.skyblockdragons.entity.types.other;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public class DragonTest extends EntityMaterial {
    public DragonTest() {
        super(EntityType.ENDER_DRAGON,
                ChatColor.YELLOW + "Dragon Test",
                -1,
                100,
                0,
                0,
                0,
                new Equipment(),
                1,
                0,
                true,
                50,
                30
        );
    }

    /*
    EntityType.ENDER_DRAGON,
                ChatColor.YELLOW + "Old Dragon",
                -1,
                3_000_000,
                10,
                10,
                10,
                new Equipment(),
                100,
                1,
                10,
                10
     */
}

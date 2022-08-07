package me.maxiiiiii.skyblockdragons.entity.types.theend.dragon;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EntityDragon;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;

public class OldDragon extends EntityDragon {
    public OldDragon() {
        super(
                ChatColor.YELLOW + "Old Dragon",
                -1,
                3_000_000,
                0,
                0,
                10,
                new Equipment(),
                100,
                1
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {

    }
}

package me.maxiiiiii.skyblockdragons.entity.types.theend.dragon;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EntityDragon;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;

public class YoungDragon extends EntityDragon {
    public YoungDragon() {
        super(
                ChatColor.GREEN + "Young Dragon",
                -1,
                2_000_000,
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

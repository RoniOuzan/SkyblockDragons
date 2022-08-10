package me.maxiiiiii.skyblockdragons.entity.types.theend.dragon;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EntityDragon;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;

public class ERRORDragon extends EntityDragon {
    public ERRORDragon() {
        super(
                ChatColor.RED + "ERROR Dragon",
                -1,
                5_000_000,
                20,
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

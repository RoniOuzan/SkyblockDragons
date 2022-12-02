package me.maxiiiiii.skyblockdragons.entity.types.theend.dragon;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EntityDragon;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;

public class ProtectorDragon extends EntityDragon {
    public ProtectorDragon() {
        super(
                ChatColor.GRAY + "Protector Dragon",
                -1,
                2_000_000,
                50,
                0,
                10,
                new Equipment(),
                100,
                1
        );
    }
}

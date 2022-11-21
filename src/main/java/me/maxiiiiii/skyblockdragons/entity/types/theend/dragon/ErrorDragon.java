package me.maxiiiiii.skyblockdragons.entity.types.theend.dragon;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EntityDragon;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;

public class ErrorDragon extends EntityDragon {
    public ErrorDragon() {
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
    public void strikeAbility(EntitySD entity) {
        super.strikeAbility(entity, 50);
    }
}

package me.maxiiiiii.skyblockdragons.damage.interfaces;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface TrueDamage {
    default double damageReduction(EntitySD entity) {
        if (entity instanceof PlayerSD) {
            double defense = ((PlayerSD) entity).getStats().getTrueDefense().get();
            return 1 - (defense / (defense + 100));
        }
        return 1;
    }
}

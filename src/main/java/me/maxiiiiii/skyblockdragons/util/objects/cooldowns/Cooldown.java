package me.maxiiiiii.skyblockdragons.util.objects.cooldowns;

import java.util.HashMap;
import java.util.Map;

public class Cooldown<T> {
    private final Map<T, Long> cooldowns = new HashMap<>();

    public void setCooldown(T player, Long time) {
        if (time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public long getCooldown(T player) {
        return cooldowns.getOrDefault(player, 0L);
    }

    public long getCurrentCooldown(T player) {
        return System.currentTimeMillis() - this.getCooldown(player);
    }
}

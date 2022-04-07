package me.maxiiiiii.skyblockdragons.util.objects;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

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

    public Long getCooldown(T player) {
        return cooldowns.getOrDefault(player, 0L);
    }

    public Long getCurrentCooldown(T player) {
        return System.currentTimeMillis() - this.getCooldown(player);
    }
}

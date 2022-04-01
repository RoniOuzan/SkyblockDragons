package me.maxiiiiii.skyblockdragons.util.objects;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {
    private final Map<Entity, Long> cooldowns = new HashMap<>();

    public void setCooldown(Entity player, Long time) {
        if (time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public Long getCooldown(Entity player) {
        return cooldowns.getOrDefault(player, 0L);
    }

    public Long getCurrentCooldown(Entity player) {
        return System.currentTimeMillis() - this.getCooldown(player);
    }
}

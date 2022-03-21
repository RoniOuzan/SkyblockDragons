package me.maxiiiiii.skyblockdragons.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Cooldown {

    private final Map<Player, Long> cooldowns = new HashMap<>();

    public void setCooldown(Player player, Long time) {
        if (time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public Long getCooldown(Player player) {
        return cooldowns.getOrDefault(player, 0L);
    }

    public Long getCurrentCooldown(Player player) {
        return System.currentTimeMillis() - this.getCooldown(player);
    }
}

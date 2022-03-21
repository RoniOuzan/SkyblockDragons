package me.maxiiiiii.skyblockdragons.util;

import java.util.HashMap;
import java.util.Map;

public class SlotCooldown {

    private final Map<String, Long> itemCooldowns = new HashMap<>();

    public void setCooldown(String player, Long time) {
        if (time < 1) {
            itemCooldowns.remove(player);
        } else {
            itemCooldowns.put(player, time);
        }
    }

    public Long getCooldown(String player, int slot) {
        return itemCooldowns.getOrDefault(player, 0L);
    }
}

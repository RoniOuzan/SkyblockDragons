package me.maxiiiiii.skyblockdragons.util.objects.cooldowns;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;

import java.util.HashMap;
import java.util.Map;

public class SingleSlotCooldown {
    private Map<Integer, Double> slots;

    public SingleSlotCooldown() {
        this.slots = new HashMap<>();
    }

    public void resetAll() {
        this.slots = new HashMap<>();
    }

    public void resetSlot(int slot) {
        this.slots.put(slot, SkyblockDragons.getCurrentTimeInSeconds());
    }

    public boolean isExpired(int slot, double seconds) {
        return SkyblockDragons.getCurrentTimeInSeconds() - this.slots.getOrDefault(slot, 0d) >= seconds;
    }
}

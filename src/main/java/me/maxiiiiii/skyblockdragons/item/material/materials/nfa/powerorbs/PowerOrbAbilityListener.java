package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.item.stats.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PowerOrbAbilityListener implements Listener {
    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getActivePowerOrb() != null) {
            e.getStats().add(StatTypes.STRENGTH, e.getPlayer().getActivePowerOrb().getStrength(), new StatAdd<>(StatAddType.POWER_ORB, e.getPlayer().getActivePowerOrb()));
        }
    }
}

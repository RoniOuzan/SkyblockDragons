package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.item.stats.StatAdderType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PowerOrbAbilityListener implements Listener {
    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getActivePowerOrb() != null) {
            e.getStats().add(StatTypes.STRENGTH, e.getPlayer().getActivePowerOrb().getStrength(), StatAdderType.POWER_ORB, e.getPlayer().getActivePowerOrb());
        }
    }
}

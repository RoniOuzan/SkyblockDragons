package me.maxiiiiii.skyblockdragons.player.skill.listeners;

import me.maxiiiiii.skyblockdragons.player.skill.events.PlayerGetSkillXpEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static me.maxiiiiii.skyblockdragons.util.Functions.getInt;

public class PlayerGetSkillXpListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetSkill(PlayerGetSkillXpEvent e) {
        double amount = e.applyMultipliers();
        e.getPlayer().getSkills().get(e.getSkillType()).giveXp(amount);
        String message = ChatColor.DARK_AQUA + "+" + getInt(amount + "") + " " + e.getSkillType() + " (" + Math.floor(e.getSkill().getCurrentXp() / e.getSkill().getCurrentNeedXp() * 1000d) / 10d + "%)";
        e.getPlayer().addActionBar(message, 2);
    }
}

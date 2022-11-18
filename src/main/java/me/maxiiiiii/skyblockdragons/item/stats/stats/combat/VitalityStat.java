package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.player.events.PlayerRegainHealthEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class VitalityStat extends CombatStat implements Listener {
    public VitalityStat() {
        super("Vitality",
                "♨",
                ChatColor.DARK_RED,
                "",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.REDSTONE);
    }

    @EventHandler
    public void onPlayerRegainHealth(PlayerRegainHealthEvent e) {
        e.getMultiplier().addPost(e.getPlayer().getStats().getVitality().get() / 100);
    }
}

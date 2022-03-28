package me.maxiiiiii.skyblockdragons.worlds.deepmines;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class DeepMines implements Listener {
    public DeepMines(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new BreakBlockEvent(), plugin);
    }
}

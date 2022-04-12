package me.maxiiiiii.skyblockdragons.worlds.mining.deepmines;

import me.maxiiiiii.skyblockdragons.worlds.WorldSD;
import me.maxiiiiii.skyblockdragons.worlds.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class DeepMines extends WorldSD {
    public DeepMines(Plugin plugin) {
        super(Bukkit.getWorld("DeepMines"), "Deep Mines", WorldType.MINING);
    }
}

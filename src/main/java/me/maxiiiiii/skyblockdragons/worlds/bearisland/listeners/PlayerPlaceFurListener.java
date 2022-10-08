package me.maxiiiiii.skyblockdragons.worlds.bearisland.listeners;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.worlds.bearisland.BearIsland;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.worlds.end.events.PlayerPlaceEyeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerPlaceFurListener implements Listener {
    public static final Map<PlayerSD, Integer> amountOfPlacedFurs = new HashMap<>();

    @EventHandler
    public void onPlaceFur(PlayerPlaceEyeEvent e) {
        e.getBlock().setData((byte) (e.getBlock().getData() + 4));
        e.addToAmountOfEyes();
        amountOfPlacedFurs.put(e.getPlayer(), amountOfPlacedFurs.getOrDefault(e.getPlayer(), 0) + 1);
        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("BearIsland")).collect(Collectors.toList())) {
            player.sendMessage( ChatColor.DARK_PURPLE + "☬ " + e.getPlayer().getDisplayName() + ChatColor.LIGHT_PURPLE + " placed an fur! (" + e.getAmountOfEyes() + "/8)");
        }


        if (e.getAmountOfEyes() >= 8) {
            Functions.Wait(20L, BearIsland::spawnBear);
        }
    }

}
package me.maxiiiiii.skyblockdragons.worlds.end.listeners;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.worlds.end.events.PlayerPlaceEyeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerPlaceEyeListener implements Listener {
    public static final Map<PlayerSD, Integer> amountOfPlacedEyes = new HashMap<>();

    @EventHandler
    public void onPlaceEye(PlayerPlaceEyeEvent e) {
        e.getBlock().setData((byte) (e.getBlock().getData() + 4));
        e.addToAmountOfEyes();
        amountOfPlacedEyes.put(e.getPlayer(), amountOfPlacedEyes.getOrDefault(e.getPlayer(), 0) + 1);
        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("TheEnd")).collect(Collectors.toList())) {
            player.sendMessage(e.getPlayer().getDisplayName() + " " + ChatColor.DARK_PURPLE + "placed an eye! (" + e.getAmountOfEyes() + "/8)");
        }

        if (e.getAmountOfEyes() >= 8) {
            Functions.Wait(20L, () -> {
                TheEnd.dragon = new EntitySD(TheEnd.DRAGON_SPAWN, TheEnd.getRandomDragon());
                for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("TheEnd")).collect(Collectors.toList())) {
                    if (player.getLocation().distance(TheEnd.MIDDLE) <= 50) {
                        player.setVelocity(new Vector(
                                TheEnd.DRAGON_SPAWN.getX() - player.getLocation().getX(),
                                0,
                                TheEnd.DRAGON_SPAWN.getZ() - player.getLocation().getZ()
                        ).normalize().multiply(2).setY(3));
                    }
                }
            });
        }
    }
}
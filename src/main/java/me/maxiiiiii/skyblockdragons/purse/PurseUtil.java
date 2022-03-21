package me.maxiiiiii.skyblockdragons.purse;

import org.bukkit.entity.Player;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;
import static me.maxiiiiii.skyblockdragons.storage.StorageUtil.setVariable;

public class PurseUtil {
    public static void add(Player player, double amount) {
        setVariable(player.getUniqueId(), "Purse", (purses.getOrDefault(player.getUniqueId(), 0d) + amount) + "");
        purses.put(player.getUniqueId(), purses.getOrDefault(player.getUniqueId(), 0d) + amount);
    }

    public static void set(Player player, double amount) {
        setVariable(player.getUniqueId(), "Purse", amount + "");
        purses.put(player.getUniqueId(), amount);
    }
}

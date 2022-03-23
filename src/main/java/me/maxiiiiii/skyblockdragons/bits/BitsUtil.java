package me.maxiiiiii.skyblockdragons.bits;

import org.bukkit.entity.Player;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.bits;
import static me.maxiiiiii.skyblockdragons.storage.Variables.setVariable;

public class BitsUtil {
    public static void add(Player player, long amount) {
        setVariable(player.getUniqueId(), "Bits", (bits.getOrDefault(player.getUniqueId(), 0L) + amount) + "");
        bits.put(player.getUniqueId(), bits.getOrDefault(player.getUniqueId(), 0L) + amount);
    }

    public static void set(Player player, long amount) {
        setVariable(player.getUniqueId(), "Bits", amount + "");
        bits.put(player.getUniqueId(), amount);
    }
}

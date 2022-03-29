package me.maxiiiiii.skyblockdragons.player.bits;

import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.entity.Player;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.bits;

public class BitsUtil {
    public static void add(Player player, long amount) {
        Variables.set(player.getUniqueId(), "Bits", (bits.getOrDefault(player.getUniqueId(), 0L) + amount));
        bits.put(player.getUniqueId(), bits.getOrDefault(player.getUniqueId(), 0L) + amount);
    }

    public static void set(Player player, long amount) {
        Variables.set(player.getUniqueId(), "Bits", amount);
        bits.put(player.getUniqueId(), amount);
    }
}

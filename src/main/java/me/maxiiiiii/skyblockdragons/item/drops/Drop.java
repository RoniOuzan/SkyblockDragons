package me.maxiiiiii.skyblockdragons.item.drops;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public interface Drop {
    double getChances();

    int getAmount();

    void setAmount(int amount);

    void give(PlayerSD player);

    void dropItem(PlayerSD player, Location location);

    DropSourceType getSourceType();

    default void drop(PlayerSD player, Object source) {
        Bukkit.getPluginManager().callEvent(new PlayerGetDropEvent(player, this, source));
    }
}

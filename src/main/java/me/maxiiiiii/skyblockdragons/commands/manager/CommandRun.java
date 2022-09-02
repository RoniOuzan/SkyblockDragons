package me.maxiiiiii.skyblockdragons.commands.manager;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface CommandRun {
    void perform(PlayerSD player, String[] args);
}

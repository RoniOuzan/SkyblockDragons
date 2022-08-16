package me.maxiiiiii.skyblockdragons.commands.manager;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface CommandRun {
    void perform(Player player, String[] args);
}

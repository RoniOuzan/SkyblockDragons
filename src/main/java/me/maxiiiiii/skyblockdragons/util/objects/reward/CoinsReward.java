package me.maxiiiiii.skyblockdragons.util.objects.reward;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;

public class CoinsReward implements Reward {
    private final double amount;

    public CoinsReward(double amount) {
        this.amount = amount;
    }

    @Override
    public void give(PlayerSD player) {
        player.giveCoins(this.amount);
    }

    @Override
    public String getLore(PlayerSD player) {
        return ChatColor.GOLD + Functions.getNumberFormat(this.amount);
    }
}

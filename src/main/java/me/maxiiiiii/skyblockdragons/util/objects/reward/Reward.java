package me.maxiiiiii.skyblockdragons.util.objects.reward;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public interface Reward {
    void give(PlayerSD player);

    String getLore();
}

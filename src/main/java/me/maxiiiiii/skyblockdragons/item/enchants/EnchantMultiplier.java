package me.maxiiiiii.skyblockdragons.item.enchants;

public class EnchantMultiplier {
    private final double[] multiplayers;

    public EnchantMultiplier(double... multiplayers) {
        this.multiplayers = multiplayers;
    }

    public double get(int level) {
        if (level >= multiplayers.length)
            return multiplayers[0] * level;
        else
            return multiplayers[level - 1];
    }
}

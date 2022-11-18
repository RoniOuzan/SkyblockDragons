package me.maxiiiiii.skyblockdragons.item.enchants;

public class EnchantMultiplier {
    private final double[] multiplayers;

    public EnchantMultiplier(double... multiplayers) {
        this.multiplayers = multiplayers;
    }

    public double get(int level) {
        int length = multiplayers.length;
        if (level > length)
            return multiplayers[length - 1] + (multiplayers[length - 1] - multiplayers[length - 2]) * (level - length);
        else
            return multiplayers[level - 1];
    }
}

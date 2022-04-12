package me.maxiiiiii.skyblockdragons.item.enchants;

public class EnchantMultiplayer {
    private final double[] multiplayers;

    public EnchantMultiplayer(double... multiplayers) {
        this.multiplayers = multiplayers;
    }

    public double get(int level) {
        if (level >= multiplayers.length)
            return multiplayers[multiplayers.length - 1];
        else
            return multiplayers[level - 1];
    }
}

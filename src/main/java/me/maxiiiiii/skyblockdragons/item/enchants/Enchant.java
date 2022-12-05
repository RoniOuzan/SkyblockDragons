package me.maxiiiiii.skyblockdragons.item.enchants;

import lombok.Getter;

@Getter
public class Enchant {
    private final int level;
    private final EnchantType enchant;

    public Enchant(int level, EnchantType enchant) {
        this.level = level;
        this.enchant = enchant;
    }
}

package me.maxiiiiii.skyblockdragons.item.enchants;

import lombok.Getter;

@Getter
public class Enchant {
    private final EnchantType enchant;
    private final int level;

    public Enchant(EnchantType enchant, int level) {
        this.enchant = enchant;
        this.level = level;
    }
}

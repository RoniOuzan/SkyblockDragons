package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EnchantModifier extends ItemModifier {
    private final Map<EnchantType, Short> enchants;

    public EnchantModifier(Map<EnchantType, Short> enchants) {
        super(EnchantModifier.class);
        this.enchants = enchants;
    }

    public EnchantModifier() {
        this(new HashMap<>());
    }

    public Map<EnchantType, Short> get() {
        return enchants;
    }

    public static ItemModifier getModifier(ItemStack item) {
        return Functions.getEnchants(item);
    }
}

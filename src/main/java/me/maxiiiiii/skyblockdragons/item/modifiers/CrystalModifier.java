package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CrystalModifier extends ItemModifier {
    private final Map<CrystalType, Short> crystals;

    public CrystalModifier(Map<CrystalType, Short> enchants) {
        super(CrystalModifier.class);
        this.crystals = enchants;
    }

    public CrystalModifier() {
        this(new HashMap<>());
    }

    public Map<CrystalType, Short> get() {
        return crystals;
    }

    public static ItemModifier getModifier(ItemStack item) {
        return Functions.getEnchants(item);
    }
}

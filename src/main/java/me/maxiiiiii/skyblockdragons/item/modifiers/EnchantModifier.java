package me.maxiiiiii.skyblockdragons.item.modifiers;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.UltimateEnchantType;
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

    @Override
    public void applyNBT(Item item, NBTCompound nbt) {
        NBTCompound nbtEnchants = nbt.addCompound("Enchants");
        NBTCompound nbtUltimateEnchant = nbt.addCompound("UltimateEnchant");
        if (this.enchants.containsKey(EnchantType.ONE_FOR_ALL)) {
            if (this.enchants.containsKey(EnchantType.TELEKINESIS)) {
                nbtEnchants.setShort("TELEKINESIS", (short) 1);
            }
            nbtEnchants.setShort("ONE_FOR_ALL", (short) 1);
        } else {
            for (EnchantType enchantType : EnchantType.enchants.values()) {
                if (this.enchants.containsKey(enchantType) && enchantType.getTypes().contains(item.getMaterial().getType())) {
                    if (enchantType instanceof UltimateEnchantType) {
                        nbtUltimateEnchant.setShort(enchantType.name(), this.enchants.get(enchantType));
                    }
                    nbtEnchants.setShort(enchantType.name(), this.enchants.get(enchantType));
                }
            }
        }
    }

    public static EnchantModifier getModifier(ItemStack item) {
        return Functions.getEnchants(item);
    }
}

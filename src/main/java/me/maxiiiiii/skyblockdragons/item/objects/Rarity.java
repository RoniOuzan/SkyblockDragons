package me.maxiiiiii.skyblockdragons.item.objects;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.UltimateEnchantType;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.ItemModifiers;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

@Getter
public enum Rarity {
    NONE(0, ChatColor.DARK_GRAY),
    COMMON(1, ChatColor.WHITE),
    UNCOMMON(2, ChatColor.GREEN),
    RARE(3, ChatColor.BLUE),
    EPIC(4, ChatColor.DARK_PURPLE),
    LEGENDARY(5, ChatColor.GOLD),
    MYTHIC(6, ChatColor.LIGHT_PURPLE),
    DIVINE(7, ChatColor.AQUA),
    SPECIAL(8, ChatColor.RED);

    private final int level;
    private final ChatColor color;

    Rarity(int level, ChatColor color) {
        this.level = level;
        this.color = color;
    }

    public static Rarity getRarity(int level) {
        for (Rarity rarity : values()) {
            if (rarity.getLevel() == level) {
                return rarity;
            }
        }
        return SPECIAL;
    }

    public static Rarity getRarity(ChatColor color) {
        for (Rarity rarity : values()) {
            if (rarity.getColor().equals(color)) {
                return rarity;
            }
        }
        return SPECIAL;
    }

    public static Rarity getRarity(ItemStack item) {
        ItemMaterial itemMaterial = Functions.getItemMaterial(item);
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound nbt = nbtItem.getCompound("Item");
        if (nbt.getBoolean("RarityUpgraded")) {
            return getRarity(itemMaterial.getRarity().getLevel() + 1);
        }
        return getRarity(itemMaterial.getRarity().getLevel());
    }

    @Override
    public String toString() {
        return this.getColor() + "" + ChatColor.BOLD + this.name();
    }

    public static Rarity getBookRarity(int level) {
        if (level == 5) {
            return Rarity.UNCOMMON;
        } else if (level == 6) {
            return Rarity.RARE;
        } else if (level == 7) {
            return Rarity.EPIC;
        } else if (level == 8) {
            return Rarity.LEGENDARY;
        } else if (level == 9 || level == 10) {
            return Rarity.MYTHIC;
        }
        return Rarity.COMMON;
    }

    public static Rarity getBookRarity(ItemModifiers modifiers) {
        short highest = 0;
        for (EnchantType enchant : EnchantType.enchants.values()) {
            if (modifiers.getEnchants().containsKey(enchant)) {
                if (enchant instanceof UltimateEnchantType) {
                    highest = 10;
                } else if (modifiers.getEnchants().get(enchant) > highest) {
                    highest = modifiers.getEnchants().get(enchant);
                }
            }
        }
        return getBookRarity(highest);
    }
}

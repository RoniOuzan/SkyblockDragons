package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public enum StatType {
    DAMAGE(Material.BLAZE_POWDER, ChatColor.RED, "❁", "Damage", false),
    STRENGTH(Material.BLAZE_POWDER, ChatColor.RED, "❁", "Strength", false),
    CRIT_DAMAGE(new ItemStack(Material.INK_SACK, 1, (short) 4), ChatColor.BLUE, "☠", "Crit Damage", true),
    CRIT_CHANCE(new ItemStack(Material.INK_SACK, 1, (short) 4), ChatColor.BLUE, "☣", "Crit Chance", true),
    ABILITY_DAMAGE(Material.BEACON, ChatColor.RED, "๑", "Ability Damage", true),
    ATTACK_SPEED(Material.GOLD_AXE, ChatColor.YELLOW, "⚔", "Attack Speed", true),
    FEROCITY(new ItemStack(Material.INK_SACK, 1, (short) 1), ChatColor.RED, "⫽", "Ferocity", false),
    HEALTH(Material.GOLDEN_APPLE, ChatColor.RED, "❤", "Health", false),
    DEFENSE(Material.IRON_CHESTPLATE, ChatColor.GREEN, "❈", "Defense", false),
    TRUE_DEFENSE(Material.IRON_BLOCK, ChatColor.WHITE, "❂", "True Defense", false),
    SPEED(Material.SUGAR, ChatColor.WHITE, "✦", "Speed", true),
    INTELLIGENCE(Material.ENCHANTED_BOOK, ChatColor.AQUA, "✎", "Intelligence", false),
    MANA(Material.ENCHANTED_BOOK, ChatColor.AQUA, "✎", "Mana", false),
    MAGIC_FIND(new ItemStack(Material.INK_SACK, 1, (short) 6), ChatColor.AQUA, "✯", "Magic Find", true),
    PET_LUCK(new ItemStack(Material.INK_SACK, 1, (short) 5), ChatColor.LIGHT_PURPLE, "♣", "Pet Luck", true),
    MINING_SPEED(Material.IRON_PICKAXE, ChatColor.GOLD, "⸕", "Mining Speed", false),
    MINING_FORTUNE(Material.GOLD_PICKAXE, ChatColor.GOLD, "☘", "Mining Fortune", false),
    FARMING_FORTUNE(Material.GOLD_HOE, ChatColor.GOLD, "☘", "Farming Fortune", false),
    FORAGING_FORTUNE(Material.GOLD_AXE, ChatColor.GOLD, "☘", "Foraging Fortune", false),
    SEA_CREATURE_CHANCE(Material.RAW_FISH, ChatColor.DARK_AQUA, "α", "Sea Create Chance", true),
    VITALITY(Material.REDSTONE, ChatColor.DARK_RED, "♨", "Vitality", true),

    ;

    private final ItemStack item;
    private final ChatColor color;
    private final String icon;
    private final String text;
    private final boolean percentage;

    StatType(ItemStack item, ChatColor color, String icon, String text, boolean percentage) {
        this.item = item;
        this.color = color;
        this.icon = icon;
        this.text = text;
        this.percentage = percentage;
    }

    StatType(Material material, ChatColor color, String icon, String text, boolean percentage) {
        this(new ItemStack(material), color, icon, text, percentage);
    }

    public String getStatDisplay(double amount) {
        return (this.color.toString() + amount + this.icon + " " + this.text).replace(".0", "");
    }

    public String getIconAndText() {
        return this.color + this.icon + " " + this.text;
    }

    @Override
    public String toString() {
        return setTitleCase(this.name().replaceAll("_", " "));
    }
}
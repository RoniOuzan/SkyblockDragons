package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public enum StatType {
    DAMAGE(Material.BLAZE_POWDER, ChatColor.RED + "❁", ChatColor.RED + "❁ Damage", false),
    STRENGTH(Material.BLAZE_POWDER, ChatColor.RED + "❁", ChatColor.RED + "❁ Strength", false),
    CRIT_DAMAGE(new ItemStack(Material.INK_SACK, 1, (short) 4), ChatColor.BLUE + "☠", ChatColor.BLUE + "☠ Crit Damage", true),
    CRIT_CHANCE(new ItemStack(Material.INK_SACK, 1, (short) 4), ChatColor.BLUE + "☣", ChatColor.BLUE + "☣ Crit Chance", true),
    ABILITY_DAMAGE(Material.BEACON, ChatColor.RED + "๑", ChatColor.RED + "๑ Ability Damage", true),
    ABILITY_SCALING(Material.ENDER_PEARL, ChatColor.RED + "๑", ChatColor.RED + "๑ Ability Scaling", true),
    ATTACK_SPEED(Material.GOLD_AXE, ChatColor.YELLOW + "✎", ChatColor.YELLOW + "✎ Attack Speed", true),
    FEROCITY(new ItemStack(Material.INK_SACK, 1, (short) 1), ChatColor.RED + "⫽", ChatColor.RED + "⫽ Ferocity", false),
    HEALTH(Material.GOLDEN_APPLE, ChatColor.RED + "❤", ChatColor.RED + "❤ Health", false),
    DEFENSE(Material.IRON_CHESTPLATE, ChatColor.GREEN + "❈", ChatColor.GREEN + "❈ Defense", false),
    TRUE_DEFENSE(Material.IRON_BLOCK, ChatColor.WHITE + "❂", ChatColor.WHITE + "❂ Defense", false),
    SPEED(Material.SUGAR, ChatColor.WHITE + "✦", ChatColor.WHITE + "✦ Speed", true),
    MANA(Material.ENCHANTED_BOOK, ChatColor.AQUA + "✎", ChatColor.AQUA + "✎ Mana", false),
    INTELLIGENCE(Material.ENCHANTED_BOOK, ChatColor.AQUA + "✎", ChatColor.AQUA + "✎ Intelligence", false),
    MAGIC_FIND(new ItemStack(Material.INK_SACK, 1, (short) 6), ChatColor.AQUA + "✯", ChatColor.AQUA + "✯ Magic Find", true),
    PET_LUCK(new ItemStack(Material.INK_SACK, 1, (short) 5), ChatColor.LIGHT_PURPLE + "♣", ChatColor.LIGHT_PURPLE + "♣ Pet Luck", true),
    MINING_SPEED(Material.IRON_PICKAXE, ChatColor.GOLD + "⸕", ChatColor.GOLD + "⸕ Mining Speed", false),
    MINING_FORTUNE(Material.GOLD_PICKAXE, ChatColor.GOLD + "☘", ChatColor.GOLD + "☘ Mining Fortune", false),
    SEA_CREATURE_CHANCE(Material.RAW_FISH, ChatColor.DARK_AQUA + "α", ChatColor.DARK_AQUA + "α Sea Create Chance", true),
    ABSORPTION(Material.GOLDEN_APPLE, ChatColor.GOLD + "❤", ChatColor.GOLD + "❤ Intelligence", false),
    ;

    private final ItemStack item;
    private final String icon;
    private final String iconAndText;
    private final boolean percentage;

    StatType(ItemStack item, String icon, String iconAndText, boolean percentage) {
        this.item = item;
        this.icon = icon;
        this.iconAndText = iconAndText;
        this.percentage = percentage;
    }

    StatType(Material material, String icon, String iconAndText, boolean percentage) {
        this(new ItemStack(material), icon, iconAndText, percentage);
    }

    @Override
    public String toString() {
        return setTitleCase(this.name().replaceAll("_", " "));
    }
}
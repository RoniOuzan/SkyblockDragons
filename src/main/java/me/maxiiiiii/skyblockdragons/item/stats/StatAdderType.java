package me.maxiiiiii.skyblockdragons.item.stats;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs.PowerOrbDeployAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.accessorybag.PowerStone;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Function;

public class StatAdderType<T> {
    public static final StatAdderType<Object> BASE = new StatAdderType<>("Innate", s -> new ItemStack(Material.NETHER_STAR), s -> "Base Value");
    public static final StatAdderType<Item> ITEM = new StatAdderType<>("Item", s -> s, s -> s.getMaterial().getName());
    public static final StatAdderType<EnchantType> ENCHANT = new StatAdderType<>("Enchant", s -> new Item(Items.get("ENCHANTED_BOOK")), EnchantType::toString);
    public static final StatAdderType<PowerStone> POWER_STONE = new StatAdderType<>("Power Stone", PowerStone::getItemStack, s -> s.getName() + " Power Stone");
    public static final StatAdderType<Object> TUNING_POINTS = new StatAdderType<>("Tuning Points", s -> new ItemStack(Material.REDSTONE_COMPARATOR), s -> "Tuning Points");
    public static final StatAdderType<ItemAbility> ITEM_ABILITY = new StatAdderType<>("Item Ability", s -> new ItemStack(Material.PAPER), s -> s.getName() + " Ability");
    public static final StatAdderType<ItemFullSetBonus> FULL_SET_BONUS = new StatAdderType<>("Full Set Bonus", s -> new ItemStack(Material.LEATHER_CHESTPLATE), s -> s.getName() + " Full Set");
    public static final StatAdderType<PowerOrbDeployAbility.PowerOrbType> POWER_ORB = new StatAdderType<>("Power Orb", s -> new Item(Items.get(s.name() + "_POWER_ORB")), s -> s.toString() + " Power Orb");
    public static final StatAdderType<Skill> SKILL = new StatAdderType<>("Skill", Skill::getItem, s -> s.getName() + " Skill");

    private final String name;
    private final Function<T, ItemStack> item;
    private final Function<T, String> title;

    private StatAdderType(String name, Function<T, ItemStack> item, Function<T, String> title) {
        this.name = name;
        this.item = item;
        this.title = title;
    }

    public ItemStack getItem(T source, double amount, boolean isPercentage, StatType statType) {
        ItemStack item = this.item.apply(source).clone();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(statType.getColor() + statType.getIcon() + " " + this.title.apply(source));
        meta.setLore(Arrays.asList(ChatColor.DARK_GRAY + name, "", ChatColor.GRAY + "Value: " + statType.getColor() + Functions.getNumSymbolNonPercentage(new Stat(statType, amount)) + (isPercentage ? "%" : "") + statType.getIcon()));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public String toString() {
        return name;
    }
}

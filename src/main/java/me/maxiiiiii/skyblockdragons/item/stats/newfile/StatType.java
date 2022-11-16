package me.maxiiiiii.skyblockdragons.item.stats.newfile;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Function;

@Getter
public abstract class StatType {
    public static StatType DAMAGE;
    public static StatType STRENGTH;

    private final String name;
    private final String icon;
    private final ChatColor color;
    private final String description;
    @Getter(AccessLevel.NONE) private final Function<PlayerSD, Double> maxLevel;

    public StatType(String name, String icon, ChatColor color, String description, Function<PlayerSD, Double> maxLevel) {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.description = description;
        this.maxLevel = maxLevel;

        StatTypes.STATS.add(this);
    }

    public StatType(String name, String icon, ChatColor color, String description) {
        this(name, icon, color, description, null);
    }

    public ItemStack getItem() {
        ItemStack item = this.getItemStack();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(this.toString());
        meta.setLore(Functions.loreBuilder(this.description));
        item.setItemMeta(meta);
        return item;
    }

    public double getMaxLevel(PlayerSD player) {
        return maxLevel.apply(player);
    }

    public abstract ItemStack getItemStack();

    public String toString(double statAmount) {
        return (this.color.toString() + statAmount + this.icon + " " + this.getName()).replace(".0", "");
    }

    @Override
    public String toString() {
        return this.color + this.icon + " " + this.getName();
    }
}

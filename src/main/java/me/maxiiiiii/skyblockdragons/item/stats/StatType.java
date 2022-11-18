package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Function;

@Getter
public abstract class StatType {
    private final String name;
    private final String icon;
    private final ChatColor color;
    private final String description;
    @Getter(AccessLevel.NONE) private final Function<PlayerSD, Double> maxLevel;
    @Getter(AccessLevel.NONE) private final Function<PlayerSD, Double> base;

    public StatType(String name, String icon, ChatColor color, String description, Function<PlayerSD, Double> maxLevel, Function<PlayerSD, Double> base) {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.description = description;
        this.maxLevel = maxLevel;
        this.base = base;

        StatTypes.STATS.add(this);

        if (this instanceof Listener) {
            SkyblockDragons.plugin.registerEvents((Listener) this);
        }
    }

    public StatType(String name, String icon, ChatColor color, String description, double base) {
        this(name, icon, color, description, null, p -> base);
    }

    public StatType(String name, String icon, ChatColor color, String description) {
        this(name, icon, color, description, null, p -> 0.0);
    }

    public ItemStack getItem(double stat) {
        ItemStack item = this.getItemStack();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(this.toString(stat));
        meta.setLore(Functions.loreBuilder(this.description));
        item.setItemMeta(meta);
        return item;
    }

    public double getMaxLevel(PlayerSD player) {
        if (maxLevel == null) return Double.MAX_VALUE;

        return maxLevel.apply(player);
    }

    public double getBase(PlayerSD player) {
        return base.apply(player);
    }

    public abstract ItemStack getItemStack();

    public String toStringLore(double statAmount) {
        return this + " " + ChatColor.WHITE + Functions.getNumberFormat(statAmount) + " " + (this instanceof PercentageStat ? "%" : "");
    }

    public String toString(double statAmount) {
        return (this.color.toString() + statAmount + this.icon + " " + this.getName()).replace(".0", "");
    }

    @Override
    public String toString() {
        return this.color + this.icon + " " + this.getName();
    }
}

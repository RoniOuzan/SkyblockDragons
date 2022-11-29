package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
public enum SlayerType {
    REVENANT("Revenant Horror", "REVENANT_HORROR", "Abhorrent Zombie stuck between life and death for an eternity.", new ItemStack(Material.ROTTEN_FLESH), new double[]{150, 1440, 2400, 4800, 6000}),
    TARANTULA("Tarantula Broodfather", "TARANTULA_BROODFATHER", "Monstrous Spider who poisons and devours its victims.", new ItemStack(Material.WEB), new double[]{250, 6600, 1000, 2000, 3500}),
    SVEN("Sven Packmaster", "SVEN_PACKMASTER", "Rabid Wolf genetically modified by a famous mad scientist. Eats bones and flesh.", new ItemStack(Material.MUTTON), new double[]{250, 650, 1500, 3000, 4500});

    private static final ChatColor[] TIERS_COLORS = {ChatColor.GREEN, ChatColor.YELLOW, ChatColor.RED, ChatColor.DARK_RED, ChatColor.DARK_PURPLE};

    private final String name;
    private final String entity;
    private final String description;
    private final ItemStack itemStack;
    private final double[] needXp;

    SlayerType(String name, String entity, String description, ItemStack itemStack, double[] needXp) {
        this.name = name;
        this.entity = entity;
        this.description = description;
        this.itemStack = itemStack;
        this.needXp = needXp;
    }

    public double getNeedXp(int tier) {
        return this.needXp[tier - 1];
    }

    public ItemStack getItem() {
        ItemStack item = this.itemStack.clone();
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Functions.loreBuilder(this.description));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public String toString() {
        return Functions.setTitleCase(this.name().replace("_", " "));
    }

    public static ChatColor getTiersColors(int tier) {
        return TIERS_COLORS[tier - 1];
    }
}

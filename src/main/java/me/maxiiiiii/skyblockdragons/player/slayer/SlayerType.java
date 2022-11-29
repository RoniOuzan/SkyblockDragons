package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
public enum SlayerType {
    REVENANT("Revenant", "Abhorrent Zombie stuck between life and death for an eternity.", new ItemStack(Material.ROTTEN_FLESH), new double[]{150, 1440, 2400, 4800, 6000}),
    TARANTULA("Tarantula", "Monstrous Spider who poisons and devours its victims.", new ItemStack(Material.WEB), new double[]{250, 6600, 1000, 2000, 3500}),
    SVEN("Sven", "Rabid Wolf genetically modified by a famous mad scientist. Eats bones and flesh.", new ItemStack(Material.MUTTON), new double[]{250, 650, 1500, 3000, 4500});

    private final String name;
    private final String description;
    private final ItemStack itemStack;
    private final double[] needXp;

    SlayerType(String name, String description, ItemStack itemStack, double[] needXp) {
        this.name = name;
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
        return this.name;
    }
}

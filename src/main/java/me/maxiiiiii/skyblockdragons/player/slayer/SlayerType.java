package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum SlayerType {
    REVENANT("Revenant", "Abhorrent Zombie stuck between life and death for an eternity.", new ItemStack(Material.ROTTEN_FLESH)),
    TARANTULA("Tarantula", "Monstrous Spider who poisons and devours its victims.", new ItemStack(Material.WEB)),
    SVEN("Sven", "Rabid Wolf genetically modified by a famous mad scientist. Eats bones and flesh.", new ItemStack(Material.MUTTON));

    private final String name;
    private final String description;
    private final ItemStack itemStack;

    SlayerType(String name, String description, ItemStack itemStack) {
        this.name = name;
        this.description = description;
        this.itemStack = itemStack;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

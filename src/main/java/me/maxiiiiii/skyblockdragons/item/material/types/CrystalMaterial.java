package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
public class CrystalMaterial extends ToolMaterial {
    private final Crystal crystal;

    public CrystalMaterial(Crystal crystal, ItemFamily family) {
        super(crystal.getCrystal().getName().toUpperCase() + "_CRYSTAL_LEVEL_" + crystal.getLevel(),
                Material.SKULL_ITEM,
                family,
                crystal.getCrystal().getName() + " Crystal Level " + crystal.getLevel(),
                ItemType.CRYSTAL,
                Rarity.getRarity(crystal.getLevel()),
                p -> "Apply on items in the Crystal NEW_LINE Grinder to add additional stats. Adds " + crystal.getStat().getStatDisplay() + ChatColor.GRAY + " to your stats."
        );
        this.crystal = crystal;
    }
}

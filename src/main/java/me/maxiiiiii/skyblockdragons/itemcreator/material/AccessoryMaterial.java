package me.maxiiiiii.skyblockdragons.itemcreator.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemType;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class AccessoryMaterial extends ItemMaterial {
    public static final AccessoryMaterial NULL = new AccessoryMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "", new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.");

    private final ArrayList<Integer> stats;
    private final String description;

    public AccessoryMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, ArrayList<Integer> stats, String description) {
        super(material, family, name, ItemType.ACCESSORY, rarity, id, nbt);
        this.stats = stats;
        this.description = description;
    }
}

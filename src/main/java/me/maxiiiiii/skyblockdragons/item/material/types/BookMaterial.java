package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemEnchantAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public abstract class BookMaterial extends ItemMaterial implements ItemEnchantAble {
    public BookMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity) {
        super(itemID, material, family, name, type, rarity);
    }
}

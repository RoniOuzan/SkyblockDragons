package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemEnchantAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class BookMaterial extends ItemMaterial implements ItemEnchantAble {
    public BookMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt) {
        super(material, family, name, type, rarity, id, nbt, 0);
    }
}

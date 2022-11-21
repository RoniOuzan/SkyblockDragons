package me.maxiiiiii.skyblockdragons.item.material.materials.other;

import me.maxiiiiii.skyblockdragons.item.material.types.BookMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class EnchantedBook extends BookMaterial {
    public EnchantedBook() {
        super("ENCHANTED_BOOK",
                Material.ENCHANTED_BOOK,
                ItemFamily.BOOK,
                "Enchanted Book",
                ItemType.BOOK,
                Rarity.COMMON
        );
    }
}

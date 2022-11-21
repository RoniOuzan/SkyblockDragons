package me.maxiiiiii.skyblockdragons.item.drops.types.block;

import me.maxiiiiii.skyblockdragons.item.drops.DropRarity;
import me.maxiiiiii.skyblockdragons.item.drops.types.ItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;

public class BlockItemRareDrop extends ItemRareDrop implements BlockDrop {
    public BlockItemRareDrop(ItemMaterial material, int amount, double chances, DropRarity rarity) {
        super(material, amount, chances, rarity);
    }

    public BlockItemRareDrop(ItemMaterial material, int amount, double chances) {
        super(material, amount, chances);
    }

    public BlockItemRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chances, DropRarity rarity) {
        super(material, minAmount, maxAmount, chances, rarity);
    }

    public BlockItemRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chances) {
        super(material, minAmount, maxAmount, chances);
    }

    public BlockItemRareDrop(ItemMaterial material, DropRarity rarity) {
        super(material, rarity);
    }
}

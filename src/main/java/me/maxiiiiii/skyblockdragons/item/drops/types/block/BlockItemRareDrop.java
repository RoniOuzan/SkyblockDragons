package me.maxiiiiii.skyblockdragons.item.drops.types.block;

import me.maxiiiiii.skyblockdragons.item.drops.DropRarity;
import me.maxiiiiii.skyblockdragons.item.drops.types.ItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;

public class BlockItemRareDrop extends ItemRareDrop implements BlockDrop {
    private BlockMaterial blockMaterial;

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

    public void setBlockMaterial(BlockMaterial blockMaterial) {
        this.blockMaterial = blockMaterial;
    }

    @Override
    public BlockMaterial getBlockMaterial() {
        return this.blockMaterial;
    }
}

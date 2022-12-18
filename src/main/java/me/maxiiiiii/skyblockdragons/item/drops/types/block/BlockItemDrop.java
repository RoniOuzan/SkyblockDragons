package me.maxiiiiii.skyblockdragons.item.drops.types.block;

import me.maxiiiiii.skyblockdragons.item.drops.types.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;

public class BlockItemDrop extends ItemDrop implements BlockDrop {
    private BlockMaterial blockMaterial = null;

    public BlockItemDrop(ItemMaterial material, int amount, double chances) {
        super(material, amount, chances);
    }

    public BlockItemDrop(ItemMaterial material, int minAmount, int maxAmount, double chances) {
        super(material, minAmount, maxAmount, chances);
    }

    public BlockItemDrop(ItemMaterial material, int amount) {
        super(material, amount);
    }

    public BlockItemDrop(ItemMaterial material) {
        super(material);
    }

    public void setBlockMaterial(BlockMaterial blockMaterial) {
        this.blockMaterial = blockMaterial;
    }

    @Override
    public BlockMaterial getBlockMaterial() {
        return this.blockMaterial;
    }
}

package me.maxiiiiii.skyblockdragons.item.drops.types.block;

import me.maxiiiiii.skyblockdragons.item.drops.Drop;
import me.maxiiiiii.skyblockdragons.item.drops.DropSourceType;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;

public interface BlockDrop extends Drop {
    @Override
    default DropSourceType getSourceType() {
        return DropSourceType.BLOCK;
    }

    BlockMaterial getBlockMaterial();
}

package me.maxiiiiii.skyblockdragons.item.drops.types.block;

import me.maxiiiiii.skyblockdragons.item.drops.Drop;
import me.maxiiiiii.skyblockdragons.item.drops.DropSourceType;

public interface BlockDrop extends Drop {
    @Override
    default DropSourceType getSourceType() {
        return DropSourceType.BLOCK;
    }
}

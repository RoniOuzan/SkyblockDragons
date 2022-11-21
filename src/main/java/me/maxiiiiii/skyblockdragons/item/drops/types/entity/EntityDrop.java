package me.maxiiiiii.skyblockdragons.item.drops.types.entity;

import me.maxiiiiii.skyblockdragons.item.drops.Drop;
import me.maxiiiiii.skyblockdragons.item.drops.DropSourceType;

public interface EntityDrop extends Drop {
    @Override
    default DropSourceType getSourceType() {
        return DropSourceType.ENTITY;
    }
}

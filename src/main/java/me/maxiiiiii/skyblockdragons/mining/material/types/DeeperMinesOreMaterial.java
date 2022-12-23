package me.maxiiiiii.skyblockdragons.mining.material.types;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.world.WorldSD;

import java.util.Collections;
import java.util.List;

public abstract class DeeperMinesOreMaterial extends OreMaterial {
    public DeeperMinesOreMaterial(String itemID, BlockMaterials material, double blockStrength, int breakingPower, double skillExp, int experience, BlockDrop... drops) {
        super(itemID, material, blockStrength, breakingPower, skillExp, experience, drops);
    }

    @Override
    public List<WorldSD> getWorlds() {
        return Collections.singletonList(WorldSD.DEEPER_MINES);
    }
}

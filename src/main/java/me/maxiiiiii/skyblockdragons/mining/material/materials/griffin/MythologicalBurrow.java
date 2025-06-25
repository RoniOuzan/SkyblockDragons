package me.maxiiiiii.skyblockdragons.mining.material.materials.griffin;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.material.interfaces.RespawnBlock;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Collections;
import java.util.List;

public class MythologicalBurrow extends BlockMaterial implements RespawnBlock {
    public MythologicalBurrow() {
        super("MYTHOLOGICAL_BURROW",
                new BlockMaterials(Material.DIRT, Material.GRASS),
                0.5,
                0,
                ItemType.ITEM,
                0,
                SkillType.COMBAT,
                0,
                new BlockItemDrop(Items.get("GRIFFIN_FEATHER"), 1)
        );
    }

    @Override
    public long getTimeToRespawn(PlayerSD player, Block block) {
        return 0;
    }

    public List<WorldSD> getWorlds() {
        return Collections.singletonList(WorldSD.GRIFFIN_ISLAND);
    }
}

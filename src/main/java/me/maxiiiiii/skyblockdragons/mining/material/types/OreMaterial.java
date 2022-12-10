package me.maxiiiiii.skyblockdragons.mining.material.types;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.mining.material.interfaces.RespawnBlock;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Material;
import org.bukkit.block.Block;

public abstract class OreMaterial extends MiningBlockMaterial implements RespawnBlock {
    public OreMaterial(String itemID, BlockMaterials material, int blockStrength, int breakingPower, double skillExp, int experience, BlockDrop... drops) {
        super(itemID, material, blockStrength, breakingPower, skillExp, experience, drops);
    }

    @Override
    public Entry<Material, Integer> getRespawnsTo(PlayerSD player, Block block, Material defaultMaterial, int defaultData) {
        if (player.getWorldSD() != WorldSD.DEEP_MINES)
            return RespawnBlock.super.getRespawnsTo(player, block, defaultMaterial, defaultData);

        if (Functions.randomInt(1, 2) == 1) {
            return new Entry<>(Material.STONE, 0);
        }

        Material material;
        if (block.getLocation().getY() > 166)
            material = Functions.getRandom(Material.COAL_ORE, Material.GOLD_ORE, Material.IRON_ORE);
        else if (block.getLocation().getY() > 122)
            material = Functions.getRandom(Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.EMERALD_ORE);
        else if (block.getLocation().getY() > 71)
            material = Functions.getRandom(Material.DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.OBSIDIAN);
        else
            material = Material.STONE;

        return new Entry<>(material, 0);
    }
}

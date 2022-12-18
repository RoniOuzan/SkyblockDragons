package me.maxiiiiii.skyblockdragons.mining.material.materials.mining;

import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.mining.material.interfaces.RespawnBlock;
import me.maxiiiiii.skyblockdragons.mining.material.types.MiningBlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.objects.BlockMaterials;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Stone extends MiningBlockMaterial implements RespawnBlock {
    public Stone() {
        super("STONE",
                new BlockMaterials(Material.STONE, Material.STONE, 5),
                15,
                1,
                1,
                0,
                new BlockItemDrop(Items.get("COBBLESTONE"), 1)
        );
    }

    @Override
    public Entry<Material, Integer> getRespawnsTo(PlayerSD player, Block block, Material defaultMaterial, int defaultData) {
        if (player.getWorldSD() == WorldSD.HUB) {
            if (block.getData() != 0) {
                return new Entry<>(block.getType(), (int) block.getData());
            }
            return Math.random() > 0.1 ? new Entry<>(Material.STONE, 0) : new Entry<>(Material.COAL_ORE, 0);
        } else if (player.getWorldSD() == WorldSD.DEEP_MINES) {
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

        return RespawnBlock.super.getRespawnsTo(player, block, defaultMaterial, defaultData);
    }
}

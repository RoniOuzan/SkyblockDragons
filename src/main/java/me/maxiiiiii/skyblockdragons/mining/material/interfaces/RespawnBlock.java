package me.maxiiiiii.skyblockdragons.mining.material.interfaces;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.Material;
import org.bukkit.block.Block;

public interface RespawnBlock {
    default Entry<Material, Integer> getBlockWhenBreaks(PlayerSD player, Block block) {
        return new Entry<>(Material.BEDROCK, 0);
    }

    default Entry<Material, Integer> getRespawnsTo(PlayerSD player, Block block, Material defaultMaterial, int defaultData) {
        return new Entry<>(defaultMaterial, defaultData);
    }

    default long getTimeToRespawn(PlayerSD player, Block block) {
        return 100;
    }
}

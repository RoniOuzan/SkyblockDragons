package me.maxiiiiii.skyblockdragons.mining.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.mining.events.PlayerBreakBlockEvent;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;
import me.maxiiiiii.skyblockdragons.mining.material.interfaces.RespawnBlock;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetExperienceEvent;
import me.maxiiiiii.skyblockdragons.player.skill.SkillXpSource;
import me.maxiiiiii.skyblockdragons.player.skill.SkillXpSourceType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class PlayerBreakBlockListener implements Listener {
    private static final Map<Location, Entry<Material, Byte>> blocks = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreakBlock(PlayerBreakBlockEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        Block block = e.getBlock();
        if (player.getGameMode() != GameMode.SURVIVAL) return;

        BlockMaterial blockMaterial = e.getMaterial();
        e.setDropItems(false);
        e.setExpToDrop((int) Math.ceil(Math.sqrt(blockMaterial.getSkillExp())));

        for (BlockDrop drop : blockMaterial.getDrops()) {
            drop.drop(player, block);
        }

        PlayerGetExperienceEvent expEvent = new PlayerGetExperienceEvent(player, blockMaterial.getExperience());
        Bukkit.getPluginManager().callEvent(expEvent);

        player.giveSkill(blockMaterial.getSkill(), blockMaterial.getSkillExp(), new SkillXpSource<>(SkillXpSourceType.BLOCK, block));

        if (blockMaterial instanceof RespawnBlock) {
            RespawnBlock respawnBlock = (RespawnBlock) blockMaterial;
            Material material = block.getType();
            byte data = block.getData();
            blocks.put(block.getLocation(), new Entry<>(block.getType(), block.getData()));
            Entry<Material, Integer> whenBreaks = respawnBlock.getBlockWhenBreaks(player, block);
            Entry<Material, Integer> respawnsTo = respawnBlock.getRespawnsTo(player, block, material, data);
            Functions.Wait(1L, () -> {
                e.getBlock().setType(whenBreaks.getA());
                e.getBlock().setData(whenBreaks.getB().byteValue());
            });

            Functions.Wait(respawnBlock.getTimeToRespawn(player, block), () -> {
                blocks.remove(block.getLocation());
                e.getBlock().setType(respawnsTo.getA());
                e.getBlock().setData(respawnsTo.getB().byteValue());
            });
        } else {
            Functions.Wait(1L, () -> e.getBlock().setType(Material.AIR));
        }
    }

    public static void resetBlocks() {
        for (Map.Entry<Location, Entry<Material, Byte>> block : blocks.entrySet()) {
            block.getKey().getBlock().setType(block.getValue().getA());
            block.getKey().getBlock().setData(block.getValue().getB());
        }
    }
}

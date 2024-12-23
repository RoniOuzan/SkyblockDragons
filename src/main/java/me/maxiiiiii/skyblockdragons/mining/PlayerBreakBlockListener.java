package me.maxiiiiii.skyblockdragons.mining;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBreakBlockListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreakBlock(PlayerBreakBlockEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        Block block = e.getBlock();
        if (player.getGameMode() != GameMode.SURVIVAL) return;
        onBlockBreak(e);
        if (e.isCancelled()) return;

        e.setDropItems(false);
        e.setExpToDrop((int) Math.ceil(Math.sqrt(e.getMaterial().getMiningXp())));
        Material brokeMaterial = e.getBlock().getType();

        for (BlockDrop drop : e.getMaterial().getDrops()) {
            drop.drop(player, block);
        }
        player.giveExp(e.getMaterial().getXp());
        if (player.getEnchantLevel(EnchantType.EXPERIENCE) > 0)
            if (Functions.chanceOf(12.5 * player.getEnchantLevel(EnchantType.EXPERIENCE)))
                player.giveExp(e.getMaterial().getXp());
        player.giveSkill(SkillType.MINING, e.getMaterial().getMiningXp());
        if (player.getWorldSD() == WorldSD.DEEPER_MINES) {
            if (e.getMaterial() == BlockMaterial.HEMATITE_ORE) {
                UpdateVoidCrystalChanceEvent event = new UpdateVoidCrystalChanceEvent(player, 1, block, e.getMaterial());
                Bukkit.getPluginManager().callEvent(event);

                if (Math.random() <= event.getMultiplier().multiply(event.getBaseChance()) / 100) {
                    Functions.Wait(1L, () -> e.getBlock().setType(Material.OBSIDIAN));
                    return;
                }
            }
        }

        Functions.Wait(1L, () -> e.getBlock().setType(Material.BEDROCK));

        Material material = e.getBlock().getType();
        byte data = e.getBlock().getData();
        if (player.getWorldSD() == WorldSD.DEEP_MINES) {
            Functions.Wait(60L, () -> e.getBlock().setType(getOre(e.getBlock())));
        } else if (player.getWorldSD() == WorldSD.DEEPER_MINES) {
            BlockMaterial blockMaterial = BlockMaterial.get(brokeMaterial);
            if (blockMaterial != null) {
                Functions.Wait(100L, () -> {
                    e.getBlock().setType(blockMaterial.getMaterial());
                    e.getBlock().setData((byte) blockMaterial.getMaterialData());
                });
            } else {
                Functions.Wait(100L, () -> e.getBlock().setType(brokeMaterial));
            }
        } else {
            Functions.Wait(100L, () -> {
                e.getBlock().setType(material);
                e.getBlock().setData(data);
            });
        }
    }

    public static Material getOre(Block block) {
        if (Functions.randomInt(1, 2) == 1) {
            return Material.STONE;
        }
        if (block.getLocation().getY() > 166) {
            return Functions.getRandom(Material.COAL_ORE, Material.GOLD_ORE, Material.IRON_ORE);
        } else if (block.getLocation().getY() > 122) {
            return Functions.getRandom(Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.EMERALD_ORE);
        } else if (block.getLocation().getY() > 71) {
            return Functions.getRandom(Material.DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.OBSIDIAN);
        }
        return Material.STONE;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (player.getGameMode() != GameMode.SURVIVAL) return;
        Material brokeMaterial = event.getBlock().getType();
        if (player.getWorldSD() == WorldSD.THE_END){
            if (brokeMaterial != Material.ENDER_STONE) {
                player.sendMessage(ChatColor.RED + "You can't break this block!");
                event.setCancelled(true);
            }
        }
    }
}

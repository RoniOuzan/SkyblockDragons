package me.maxiiiiii.skyblockdragons.mining;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.objects.Drop;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerBreakBlockListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreakBlock(PlayerBreakBlockEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        Block block = e.getBlock();
        if (player.getGameMode() != GameMode.SURVIVAL) return;
        onBlockBreak(e);
        if (e.isCancelled()) return;

        e.setDropItems(false);
        e.setExpToDrop((int) Math.ceil(Math.sqrt(e.getMaterial().miningXp)));
        Material brokeMaterial = e.getBlock().getType();

        if (player.getEnchantLevel(EnchantType.TELEKINESIS) > 0)
            for (Drop drop : e.getMaterial().drops) {
                ItemStack itemStack = drop.generate(player, block);
                if (itemStack != null) {
                    player.give(itemStack);
                }
            }
        else
            for (Drop drop : e.getMaterial().drops) {
                ItemStack itemStack = drop.generate(player, block);
                if (itemStack != null) {
                    org.bukkit.entity.Item dropped = block.getWorld().dropItem(block.getLocation().add(new Vector(
                            player.getLocation().getX() - block.getLocation().getX(),
                            player.getLocation().getY() - block.getLocation().getY(),
                            player.getLocation().getZ() - block.getLocation().getZ()
                    ).normalize()), itemStack);
                    dropped.addScoreboardTag(player.getName());
                }
            }
        player.giveExp(e.getMaterial().xp);
        if (player.getEnchantLevel(EnchantType.EXPERIENCE) > 0)
            if (Functions.chanceOf(12.5 * player.getEnchantLevel(EnchantType.EXPERIENCE)))
                player.giveExp(e.getMaterial().xp);
        player.giveSkill(SkillType.MINING, e.getMaterial().miningXp);
        if (player.getWorldSD() == WorldSD.DEEPER_MINES) {
            if (e.getMaterial() == BlockMaterial.HEMATITE_ORE) {
                if (player.chanceOf(1, e.getMaterial())) {
                    Functions.Wait(1L, () -> e.getBlock().setType(Material.OBSIDIAN));
                    return;
                }
            }
        }
        Functions.Wait(1L, () -> e.getBlock().setType(Material.BEDROCK));
        if (player.getWorldSD() == WorldSD.DEEP_MINES)
            Functions.Wait(60L, () -> e.getBlock().setType(getOre(e.getBlock())));
        else if (player.getWorldSD() == WorldSD.THE_END) {
            Functions.Wait(100L, () -> e.getBlock().setType(Material.ENDER_STONE));
        }
        else if (player.getWorldSD() == WorldSD.DEEPER_MINES) {
            BlockMaterial blockMaterial = BlockMaterial.get(brokeMaterial);
            if (blockMaterial != null) {
                Functions.Wait(100L, () -> {
                    e.getBlock().setType(blockMaterial.material);
                    e.getBlock().setData((byte) blockMaterial.materialData);
                });
            }
            else {
                Functions.Wait(100L, () -> {
                    e.getBlock().setType(brokeMaterial);
                });
            }
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
//        player.sendMessage(String.format("Break block anywhere %s", brokeMaterial));
        if (player.getWorldSD() == WorldSD.THE_END){
//            player.sendMessage(String.format("Break block at end %s", brokeMaterial));
            if (brokeMaterial != Material.ENDER_STONE) {
                player.sendMessage("§4You can't break this block!");
                event.setCancelled(true);
                return;
            }
        }
    }
}

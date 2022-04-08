package me.maxiiiiii.skyblockdragons.worlds.mining;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class PlayerBreakBlockListener implements Listener {
    @EventHandler
    public void onBreakBlock(PlayerBreakBlockEvent e) {
        if (!e.getPlayer().getWorld().getName().equals("DeepMines")) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();
        Block block = e.getBlock();
        if (player.getGameMode() != GameMode.SURVIVAL) return;

        if (!block.getType().toString().contains("_ORE") && e.getBlock().getType() != Material.STONE) return;

        e.setDropItems(false);
        e.setExpToDrop((int) Math.ceil(Math.sqrt(e.getMaterial().miningXp)));
        int amount = 1 + Functions.randomInt(0, Functions.getEnchantLevel(item, EnchantType.FORTUNE));
//        ItemStack drop = new Item(Items.get((block.getType().name().split("_ORE")[0]).replace("GLOWING_", "")), amount);
        if (player.getEnchantLevel(EnchantType.TELEKINESIS) > 0)
            for (ItemDrop drop : e.getMaterial().drops) {
                player.give(drop);
            }
        else
            for (ItemDrop drop : e.getMaterial().drops) {
                ItemStack itemToDrop = drop.generate(player);
                if (itemToDrop != null) {
                    itemToDrop.setAmount(amount);
                    org.bukkit.entity.Item dropped = block.getWorld().dropItem(block.getLocation().add(new Vector(
                            player.getLocation().getX() - block.getLocation().getX(),
                            player.getLocation().getY() - block.getLocation().getY(),
                            player.getLocation().getZ() - block.getLocation().getZ()
                    ).normalize()), itemToDrop);
                    dropped.addScoreboardTag(player.getName());
                }
            }
        player.giveSkill(SkillType.MINING, e.getMaterial().miningXp);
        Functions.Wait(1L, () -> e.getBlock().setType(Material.BEDROCK));
        Functions.Wait(60L, () -> e.getBlock().setType(getOre(e.getBlock())));
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
}

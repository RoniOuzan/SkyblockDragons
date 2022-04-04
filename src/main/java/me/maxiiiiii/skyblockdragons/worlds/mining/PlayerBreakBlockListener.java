package me.maxiiiiii.skyblockdragons.worlds.mining;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
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
        ItemStack drop = new Item(ItemMaterial.get(((String) Arrays.stream(block.getType().name().split("_ORE")).map(s -> s.contains("IRON") || s.contains("GOLD") ? s + "_INGOT" : s).toArray()[0]).replace("GLOWING_", "")), amount);
        if (Functions.getEnchantLevel(item, EnchantType.TELEKINESIS) > 0) {
            player.give(drop);
        } else {
            org.bukkit.entity.Item dropped = block.getWorld().dropItemNaturally(block.getLocation().add(0, 1, 0), drop);
            dropped.addScoreboardTag(player.getName());
        }
        player.getSkill().give(SkillType.MINING, e.getMaterial().miningXp);
        Functions.Wait(1L, () -> e.getBlock().setType(Material.BEDROCK));
        Functions.Wait(60L, () -> e.getBlock().setType(getOre(e.getBlock())));
    }

    public static Material getOre(Block block) {
        if (Functions.randomInt(1, 2) == 1) {
            return Material.STONE;
        }
        if (block.getLocation().getY() > 168) {
            return Functions.getRandom(Material.COAL_ORE, Material.GOLD_ORE, Material.IRON_ORE);
        } else if (block.getLocation().getY() > 122) {
            return Functions.getRandom(Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.EMERALD_ORE);
        } else if (block.getLocation().getY() > 71) {
            return Functions.getRandom(Material.DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.OBSIDIAN);
        }
        return Material.STONE;
    }
}

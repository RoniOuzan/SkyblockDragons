package me.maxiiiiii.skyblockdragons.worlds.deepmines;

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
import java.util.HashMap;

public class BreakBlockEvent implements Listener {
    public static final HashMap<Material, Double> blocksXp = new HashMap<>();
    static {
        blocksXp.put(Material.STONE, 1d);
        blocksXp.put(Material.COAL_ORE, 4d);
        blocksXp.put(Material.IRON_ORE, 5d);
        blocksXp.put(Material.GOLD_ORE, 5d);
        blocksXp.put(Material.LAPIS_ORE, 8d);
        blocksXp.put(Material.REDSTONE_ORE, 8d);
        blocksXp.put(Material.EMERALD_ORE, 8d);
        blocksXp.put(Material.DIAMOND_ORE, 12d);
        blocksXp.put(Material.DIAMOND_BLOCK, 16d);
        blocksXp.put(Material.OBSIDIAN, 20d);
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        if (!e.getPlayer().getWorld().getName().equals("DeepMines")) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();
        Block block = e.getBlock();
        if (player.getGameMode() != GameMode.SURVIVAL) return;

        if (!block.getType().toString().contains("_ORE") && e.getBlock().getType() != Material.STONE) return;

        e.setCancelled(true);
        Material blockType = block.getType();
        int amount = 1 + Functions.randomInt(0, Functions.getEnchantLevel(item, EnchantType.FORTUNE));
        ItemStack drop = new Item(ItemMaterial.get(((String) Arrays.stream(block.getType().name().split("_ORE")).map(s -> s.contains("IRON") || s.contains("GOLD") ? s + "_INGOT" : s).toArray()[0]).replace("GLOWING_", "")), amount);
        if (Functions.getEnchantLevel(item, EnchantType.TELEKINESIS) > 0) {
            player.give(drop);
        } else {
            block.getWorld().dropItemNaturally(block.getLocation(), drop);
        }
        player.getSkill().give(SkillType.MINING, blocksXp.get(blockType));
        e.getBlock().setType(Material.BEDROCK);
        Functions.Wait(60L, () -> e.getBlock().setType(blockType));
    }
}

package me.maxiiiiii.skyblockdragons.item.abilities;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class Builders_Wand implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("BUILDERS_WAND")) return;
        Player player = e.getPlayer();
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            openBuilderStorage(player);
            e.setCancelled(true);

            return;
        }
        if (e.getClickedBlock() == null) return;

        int blockFaceX = e.getBlockFace().getModX();
        int blockFaceY = e.getBlockFace().getModY();
        int blockFaceZ = e.getBlockFace().getModZ();

        recursiveBlocks(player, e.getClickedBlock().getLocation(), e.getClickedBlock().getType(), blockFaceX, blockFaceY, blockFaceZ);
    }

    private static void recursiveBlocks(Player player, Location location, Material material, int blockFaceX, int blockFaceY, int blockFaceZ) {
        ArrayList<Block> blocks = loopBlocks(location, 1);
        blocks.add(location.getBlock());
//
//        NBTItem nbtItem = new NBTItem(player.getEquipment().getItemInMainHand());
//        NBTCompound nbt = nbtItem.getCompound("Item");
//        NBTCompound itemsCompound = nbt.getCompound("BuildersWandItems");
//        ArrayList<ItemStack> items = new ArrayList<>();
//        boolean hasMaterial = false;
//        for (int i = 0; i < 54; i++) {
//            if (!itemsCompound.hasKey(i + "")) continue;
//
//            items.add(itemsCompound.getItemStack(i + ""));
//            if (itemsCompound.getItemStack(i + "").getType() == material) hasMaterial = true;
//        }

        for (Block block : blocks) {
            if (block.getType() == material) {
//                if ((location.getBlockX() == block.getX() && connectedX) || (location.getBlockY() == block.getY() && connectedY) || (location.getBlockZ() == block.getZ() && connectedZ)) {

                if (blockFaceX != 0)
                    if (location.getBlockX() != block.getX())
                        continue;
                if (blockFaceY != 0)
                    if (location.getBlockY() != block.getY())
                        continue;
                if (blockFaceZ != 0)
                    if (location.getBlockZ() != block.getZ())
                        continue;

                Block newBlock = block.getLocation().add(blockFaceX, blockFaceY, blockFaceZ).getBlock();
                if (newBlock.getType() == Material.AIR) {
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        if (player.getInventory().contains(material, 1)) {
                            for (int i = 0; i < 36; i++) {
                                if (player.getInventory().getItem(i) == null) continue;

                                if (player.getInventory().getItem(i).getType() == material) {
                                    if (player.getInventory().getItem(i).getAmount() > 1) {
                                        ItemStack item = player.getInventory().getItem(i).clone();
                                        item.setAmount(item.getAmount() - 1);
                                        player.getInventory().setItem(i, item);
                                    } else {
                                        player.getInventory().setItem(i, null);
                                    }
                                }
                            }
//                        } else if (hasMaterial) {
//                            for (int i = 0; i < 54; i++) {
//                                if (items.get(i) == null) continue;
//
//                                if (items.get(i).getType() == material) {
//                                    if (items.get(i).getAmount() > 1) {
//                                        ItemStack item = player.getInventory().getItem(i).clone();
//                                        item.setAmount(item.getAmount() - 1);
//                                        items.set(i, item);
//                                        itemsCompound.setItemStack(i + "", item);
//                                    } else {
//                                        itemsCompound.setItemStack(i + "", null);
//                                    }
//                                }
//                            }
//                            nbtItem.applyNBT(player.getEquipment().getItemInMainHand());
                        } else {
                            continue;
                        }
                    }
                    newBlock.setType(material);
                    newBlock.setData(block.getData());
                    recursiveBlocks(player, block.getLocation(), material, blockFaceX, blockFaceY, blockFaceZ);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getTitle().contains("Built-in Storage")) {

            Player player = (Player) e.getPlayer();
            ItemStack[] items = e.getInventory().getContents();

            NBTItem nbtItem = new NBTItem(player.getEquipment().getItemInMainHand());
            NBTCompound nbt = nbtItem.getCompound("Item");
            NBTCompound itemsCompound = nbt.addCompound("BuildersWandItems");

            for (int i = 0; i < 54; i++) {
                itemsCompound.setItemStack(i + "", items[i]);
            }

            nbtItem.applyNBT(player.getEquipment().getItemInMainHand());
        }
    }

    private static void openBuilderStorage(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "Built-in Storage");

        NBTItem nbtItem = new NBTItem(player.getEquipment().getItemInMainHand());
        NBTCompound nbt = nbtItem.getCompound("Item");
        NBTCompound itemsCompound = nbt.getCompound("BuildersWandItems");

        if (itemsCompound != null) {
            ArrayList<ItemStack> items = new ArrayList<>();

            for (int i = 0; i < 54; i++) {
                if (!itemsCompound.hasKey(i + "")) continue;

                items.add(itemsCompound.getItemStack(i + ""));
            }

            inventory.addItem(items.toArray(new ItemStack[]{}));
        }


        player.openInventory(inventory);
    }
}

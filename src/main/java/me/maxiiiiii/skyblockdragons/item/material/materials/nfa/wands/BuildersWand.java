package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.wands;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.util.Functions.loopBlocks;

public class BuildersWand extends ToolMaterial {
    public BuildersWand() {
        super("BUILDERS_WAND",
                Material.BLAZE_ROD,
                ItemFamily.BUILDERS_WAND,
                "Builder's Wand",
                ItemType.WAND,
                Rarity.LEGENDARY,
                "",
                new GrandArchitect(),
                new BuiltInStorage()
        );
    }

    public static class GrandArchitect extends ItemAbility {
        public GrandArchitect() {
            super(AbilityAction.RIGHT_CLICK,
                    "Grand Architect",
                    "Right-click the face of a block to extend all connected block faces."
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerInteractEvent event = (PlayerInteractEvent) e.getEvent();

                if (event.getClickedBlock() == null) return;

                int blockFaceX = event.getBlockFace().getModX();
                int blockFaceY = event.getBlockFace().getModY();
                int blockFaceZ = event.getBlockFace().getModZ();

                recursiveBlocks(e.getPlayer(),
                        event.getClickedBlock().getLocation(),
                        event.getClickedBlock().getType(),
                        blockFaceX,
                        blockFaceY,
                        blockFaceZ
                );
            };
        }

        private static void recursiveBlocks(Player player, Location location, Material material, int blockFaceX, int blockFaceY, int blockFaceZ) {
            ArrayList<Block> blocks = loopBlocks(location, 1);
            blocks.add(location.getBlock());

            for (Block block : blocks) {
                if (block.getType() == material) {
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
    }

    public static class BuiltInStorage extends ItemAbility implements Listener {
        public BuiltInStorage() {
            super(AbilityAction.LEFT_CLICK,
                    "Built-in Storage",
                    "Opens the wand storage. Blocks will be placed from your inventory or the wand storage."
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> openBuilderStorage(e.getPlayer());
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
}

package me.maxiiiiii.skyblockdragons.item.reforge;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ReforgeCommand implements CommandExecutor, Listener {
    private ReforgeType getRandomReforge(ItemType type) {
        ArrayList<ReforgeType> reforges = new ArrayList<>();
        for (ReforgeType reforgeType : ReforgeType.values()) {
            if (reforgeType.getTypes().contains(type) && !reforgeType.isReforgeStone()) {
                reforges.add(reforgeType);
            }
        }

        int random = Functions.randomInt(1, reforges.size());
        int a = 0;
        for (ReforgeType reforgeType : reforges) {
            a++;
            if (a == random) {
                return reforgeType;
            }
        }
        return ReforgeType.SPICY;
    }

    private void updateLines(Inventory inv, boolean red) {
        if (red) {
            ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
            int n = 0;
            for (int i = 0; i < 5; i++) {
                inv.setItem(n, glass);
                n += 9;
            }
            n = 8;
            for (int i = 0; i < 5; i++) {
                inv.setItem(n, glass);
                n += 9;
            }
        } else {
            ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            int n = 0;
            for (int i = 0; i < 5; i++) {
                inv.setItem(n, glass);
                n += 9;
            }
            n = 8;
            for (int i = 0; i < 5; i++) {
                inv.setItem(n, glass);
                n += 9;
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ReforgeMenu.openReforgeMenu((Player) sender);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getInventory().getTitle().contains("Reforge Menu")) {
                Player player = (Player) e.getWhoClicked();
                try {
                    NBTItem nbt = new NBTItem(e.getInventory().getItem(13));
                    if (e.getClickedInventory().getTitle().contains("Reforge Menu") && e.getSlot() != 13) {
                        e.setCancelled(true);
                    } else if (e.getSlot() == 13) {
                        e.setCancelled(false);
                    }

                    if (e.getSlot() == 22) {
                        ReforgeType reforgeType = getRandomReforge(Functions.getItemMaterial(e.getInventory().getItem(13)).getType());
                        for (int i = 0; i < 4; i++) {
                            if (nbt.getString("Reforge").equals(reforgeType.toString().trim())) {
                                reforgeType = getRandomReforge(Functions.getItemMaterial(e.getInventory().getItem(13)).getType());
                            }
                        }
                        player.sendMessage(ChatColor.GREEN + "You have been reforged your " + e.getInventory().getItem(13).getItemMeta().getDisplayName() + ChatColor.GREEN + " to " + Functions.getRarity(e.getInventory().getItem(13)).getColor() + reforgeType.toString() + ChatColor.GREEN + ".");
                        Reforge reforge = new Reforge(e.getInventory().getItem(13), reforgeType);
                        e.getInventory().setItem(13, reforge.apply());
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 0.5f, 1f);
                    }
                } catch (NullPointerException ignored) {}
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        updateLines(e.getInventory(), !Functions.getItemMaterial(e.getInventory().getItem(13)).getType().isReforgeable());
                    }
                }.runTaskLater(SkyblockDragons.plugin, 1);
            }
        } catch (NullPointerException ignored) {}
    }
}

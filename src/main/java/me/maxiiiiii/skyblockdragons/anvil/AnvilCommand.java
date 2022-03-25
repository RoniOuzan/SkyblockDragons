package me.maxiiiiii.skyblockdragons.anvil;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.anvil.AnvilGui.*;

public class AnvilCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            openAnvil((Player) sender, false);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getTitle().contains("Anvil")) {
            if (e.getInventory().getItem(13).getType() != Material.BARRIER && !getItemName(e.getInventory().getItem(13)).contains("Anvil")) {
                try {
                    Material material1 = e.getInventory().getItem(29).getType();
                } catch (NullPointerException ex1) {
                    try {
                        Material material2 = e.getInventory().getItem(33).getType();
                    } catch (NullPointerException ex2) {
                        e.getPlayer().getInventory().addItem(e.getInventory().getItem(13));
                    }
                }
            }
            try {
                if (e.getInventory().getItem(29).getType() != Material.AIR) {
                    e.getPlayer().getInventory().addItem(e.getInventory().getItem(29));
                }
            } catch (NullPointerException ignored) {}
            try {
                if (e.getInventory().getItem(33).getType() != Material.AIR) {
                    e.getPlayer().getInventory().addItem(e.getInventory().getItem(33));
                }
            } catch (NullPointerException ignored) {}
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getInventory().getTitle().contains("Anvil")) {
                if (e.getClick() == ClickType.DOUBLE_CLICK) {
                    e.setCancelled(true);
                    return;
                }
                if (e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT) {
                    if (!e.getClickedInventory().getTitle().contains("Anvil")) {
                        if (e.getInventory().getItem(13).isSimilar(e.getCurrentItem())) {
                            e.setCancelled(true);
                            return;
                        }
                    }
                }
                if (e.getClickedInventory().getTitle().contains("Anvil") && e.getSlot() != 29 && e.getSlot() != 33) {
                    e.setCancelled(true);
                    if (e.getSlot() == 13 && e.getCurrentItem().getType() != Material.BARRIER && !e.getCurrentItem().getItemMeta().getDisplayName().contains("Anvil")) {
                        try {
                            Material material1 = e.getClickedInventory().getItem(29).getType();
                        } catch (NullPointerException ex1) {
                            try {
                                Material material2 = e.getClickedInventory().getItem(33).getType();
                            } catch (NullPointerException ex2) {
                                e.setCancelled(false);
                            }
                        }
                    }
                }
                try {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Combine Items") && e.getSlot() == 22) {
                        try {
                            if (e.getInventory().getItem(13).getType() != Material.BARRIER && !e.getInventory().getItem(13).getItemMeta().getDisplayName().contains("Anvil")) {
                                try {
                                    Material material1 = e.getInventory().getItem(29).getType();
                                } catch (NullPointerException ex1) {
                                    try {
                                        Material material2 = e.getInventory().getItem(33).getType();
                                    } catch (NullPointerException ex2) {
                                        e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(13));
                                    }
                                }
                            }
                            if (e.getInventory().getItem(29).getType() != Material.AIR) {
                                try {
                                    if (e.getInventory().getItem(33).getType() != Material.AIR) {
                                        e.getInventory().setItem(29, new ItemStack(Material.AIR));
                                        e.getInventory().setItem(33, new ItemStack(Material.AIR));
                                        updateLine(e.getInventory(), true);
                                        updateUpgrade(e.getInventory(), true);
                                        updateSacrifice(e.getInventory(), true, e.getInventory().getTitle().contains("Advanced"));
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_USE, 0.5f, 1f);
                                        return;
                                    }
                                } catch (NullPointerException ignored) {}
                            }
                        } catch (NullPointerException ignored) {}
                    }
                } catch (NullPointerException ignored) {
                }
                new BukkitRunnable() {
                    boolean isUpgraded = false;
                    boolean isSacrificed = false;

                    @Override
                    public void run() {
                        if (getItemMaterial(e.getInventory().getItem(29)).getType() != ItemType.NULL && e.getInventory().getItem(29).getType() != Material.AIR) {
                            updateUpgrade(e.getInventory(), false);
                            isUpgraded = true;
                        } else {
                            updateUpgrade(e.getInventory(), true);
                        }
                        if (getItemMaterial(e.getInventory().getItem(33)).getType() != ItemType.NULL && e.getInventory().getItem(33).getType() != Material.AIR) {
                            updateSacrifice(e.getInventory(), false, e.getInventory().getTitle().contains("Advanced"));
                            isSacrificed = true;
                        } else {
                            updateSacrifice(e.getInventory(), true, e.getInventory().getTitle().contains("Advanced"));
                        }
                        if (!isUpgraded && !isSacrificed) {
                            updateLine(e.getInventory(), true);
                            updateUpgrade(e.getInventory(), true);
                            updateSacrifice(e.getInventory(), true, e.getInventory().getTitle().contains("Advanced"));
                        }
                        try {
                            if (e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
                                Anvil anvil = new Anvil(e.getInventory().getItem(29), e.getInventory().getItem(33));
                                e.getInventory().setItem(13, anvil.combine());
                                if (e.getInventory().getItem(13).getType() != Material.BARRIER && e.getInventory().getItem(29).getType() != Material.AIR || e.getInventory().getItem(33).getType() != Material.AIR) {
                                    updateLine(e.getInventory(), false);
                                }
                            }
                        } catch (NullPointerException ignored) {
                        }
                        if (e.getInventory().getItem(13).getType() == Material.BARRIER && getItemName(e.getInventory().getItem(13)).contains("Anvil")) {
                            updateLine(e.getInventory(), true);
                        }
                    }
                }.runTaskLater(SkyblockDragons.getInstance(), 1L);
            }
        } catch (NullPointerException ignored) {}
    }
}

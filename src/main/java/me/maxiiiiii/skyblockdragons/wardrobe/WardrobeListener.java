package me.maxiiiiii.skyblockdragons.wardrobe;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemType;
import me.maxiiiiii.skyblockdragons.stat.PlayerData;
import me.maxiiiiii.skyblockdragons.storage.StorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static me.maxiiiiii.skyblockdragons.Functions.*;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.players;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;

public class WardrobeListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getTitle().contains("Wardrobe")) {

            boolean secondPage = (e.getInventory().getTitle().contains("(2/2)"));

            int adder = 0;
            if (secondPage) adder = 9;

            Player player = (Player) e.getPlayer();
            PlayerData playerData = players.get(player.getUniqueId());
            for (int i = 0; i < 36; i++) {
                if (e.getInventory().getItem(i).getType() != Material.STAINED_GLASS_PANE) {
                    playerData.getWardrobe().getSlot((i % 9) + adder).setPeace(e.getInventory().getItem(i), i / 9);
                    StorageUtil.setVariable(player.getUniqueId(), "Wardrobe", SkyblockDragons.getSerializer().serialize(e.getInventory().getItem(i)), i + (adder * 4));
                } else {
                    playerData.getWardrobe().getSlot((i % 9) + adder).setPeace(null, i / 9);
                    StorageUtil.deleteVariable(player.getUniqueId(), "Wardrobe", i + (adder * 4));
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getClickedInventory().getType() == InventoryType.PLAYER || e.getClickedInventory().getType() == InventoryType.CREATIVE) {
                Player player = (Player) e.getWhoClicked();
                PlayerData playerData = players.get(player.getUniqueId());
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!StorageUtil.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0").equals("0")) {
                            int slot = Integer.parseInt(StorageUtil.getVariable(player.getUniqueId(), "WardrobeEquip").getValue()) - 1;

                            if (isNotAir(player.getEquipment().getHelmet())) {
                                playerData.getWardrobe().getSlot(slot).setHelmet(player.getEquipment().getHelmet());
                                StorageUtil.setVariable(player.getUniqueId(), "Wardrobe", SkyblockDragons.getSerializer().serialize(player.getEquipment().getHelmet()), slot);
                            } else {
                                StorageUtil.deleteVariable(player.getUniqueId(), "Wardrobe", slot);
                                playerData.getWardrobe().getSlot(slot).setPeace(null, 0);
                            }

                            if (isNotAir(player.getEquipment().getChestplate())) {
                                playerData.getWardrobe().getSlot(slot).setChestplate(player.getEquipment().getChestplate());
                                StorageUtil.setVariable(player.getUniqueId(), "Wardrobe", SkyblockDragons.getSerializer().serialize(player.getEquipment().getChestplate()), slot + 9);
                            } else {
                                StorageUtil.deleteVariable(player.getUniqueId(), "Wardrobe", slot+ 9);
                                playerData.getWardrobe().getSlot(slot).setPeace(null, 1);
                            }

                            if (isNotAir(player.getEquipment().getLeggings())) {
                                playerData.getWardrobe().getSlot(slot).setLeggings(player.getEquipment().getLeggings());
                                StorageUtil.setVariable(player.getUniqueId(), "Wardrobe", SkyblockDragons.getSerializer().serialize(player.getEquipment().getLeggings()), slot + 18);
                            } else {
                                StorageUtil.deleteVariable(player.getUniqueId(), "Wardrobe", slot + 18);
                                playerData.getWardrobe().getSlot(slot).setPeace(null, 2);
                            }

                            if (isNotAir(player.getEquipment().getBoots())) {
                                playerData.getWardrobe().getSlot(slot).setBoots(player.getEquipment().getBoots());
                                StorageUtil.setVariable(player.getUniqueId(), "Wardrobe", SkyblockDragons.getSerializer().serialize(player.getEquipment().getBoots()), slot + 27);
                            } else {
                                StorageUtil.deleteVariable(player.getUniqueId(), "Wardrobe", slot + 27);
                                playerData.getWardrobe().getSlot(slot).setPeace(null, 3);
                            }
                        }
                    }
                }.runTaskLater(plugin, 1L);
            }
            if (e.getInventory().getTitle().contains("Wardrobe")) {
                Player player = (Player) e.getWhoClicked();
                if (e.getClickedInventory().getTitle().contains("Wardrobe")) {

                    boolean secondPage = (e.getInventory().getTitle().contains("(2/2)"));

                    if (e.getSlot() == 53 && e.getCurrentItem().getType() == Material.ARROW) {
                        player.closeInventory();
                        WardrobeMenu.openWardrobe(player, 2);
                        return;
                    } else if (e.getSlot() == 45 && e.getCurrentItem().getType() == Material.ARROW) {
                        player.closeInventory();
                        WardrobeMenu.openWardrobe(player, 1);
                        return;
                    }

                    if (e.getSlot() > 35) e.setCancelled(true);

                    else {
                        if (StorageUtil.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0").equalsIgnoreCase(((e.getSlot() % 9) + 1) + "")) {
                            e.setCancelled(true);
                            return;
                        }

                        if (e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
                            int finalSlot = (e.getSlot() % 9) + ((secondPage) ? 9 : 0);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    e.setCurrentItem(createItem(Material.STAINED_GLASS_PANE, 1, WardrobeSlot.numberToWardrobeColor(e.getSlot()), ChatColor.GREEN + "Slot " + ((finalSlot % 9) + 1) + " " + getPeaceString(e.getSlot() / 9), ChatColor.GRAY + "Place a " + getPeaceString(e.getSlot() / 9) + " here to add", ChatColor.GRAY + "it to the armor set."));
                                }
                            }.runTaskLater(plugin, 1L);
                        } else {
                            if ((e.getSlot() / 9 == 0 && getItemMaterial(e.getCursor()).getType() == ItemType.HELMET) || (e.getSlot() / 9 == 1 && getItemMaterial(e.getCursor()).getType() == ItemType.CHESTPLATE) || (e.getSlot() / 9 == 2 && getItemMaterial(e.getCursor()).getType() == ItemType.LEGGINGS) || (e.getSlot() / 9 == 3 && getItemMaterial(e.getCursor()).getType() == ItemType.BOOTS)) {
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        e.setCursor(new ItemStack(Material.AIR));
                                    }
                                }.runTaskLater(plugin, 1L);
                            } else {
                                e.setCancelled(true);
                                return;
                            }
                        }
                    }

                    if (e.getSlot() > 35 && e.getSlot() < 45 && !e.getCurrentItem().getItemMeta().getDisplayName().contains("Empty")) {
                        int currentSlot = e.getSlot() - 35;
                        if (secondPage) currentSlot += 9;
                        if (!StorageUtil.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0").equals(currentSlot + "")) {
                            e.setCurrentItem(createItem(Material.INK_SACK, 1, 10, ChatColor.GRAY + "Slot " + currentSlot + ": " + ChatColor.GREEN + "Equipped", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));

                            updateSlots(player);

                            ItemStack helmet = (e.getInventory().getItem(e.getSlot() - 36).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 36) : new ItemStack(Material.AIR);
                            ItemStack chestplate = (e.getInventory().getItem(e.getSlot() - 27).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 27) : new ItemStack(Material.AIR);
                            ItemStack leggings = (e.getInventory().getItem(e.getSlot() - 18).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 18) : new ItemStack(Material.AIR);
                            ItemStack boots = (e.getInventory().getItem(e.getSlot() - 9).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 9) : new ItemStack(Material.AIR);
                            if (StorageUtil.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0").equals("0")) {
                                if (isNotAir(player.getEquipment().getHelmet())) player.getInventory().addItem(player.getEquipment().getHelmet());
                                if (isNotAir(player.getEquipment().getChestplate())) player.getInventory().addItem(player.getEquipment().getChestplate());
                                if (isNotAir(player.getEquipment().getLeggings())) player.getInventory().addItem(player.getEquipment().getLeggings());
                                if (isNotAir(player.getEquipment().getBoots())) player.getInventory().addItem(player.getEquipment().getBoots());
                            }
                            player.getEquipment().setHelmet(helmet);
                            player.getEquipment().setChestplate(chestplate);
                            player.getEquipment().setLeggings(leggings);
                            player.getEquipment().setBoots(boots);

                            StorageUtil.setVariable(player.getUniqueId(), "WardrobeEquip", currentSlot + "");
                        } else {
                            StorageUtil.setVariable(player.getUniqueId(), "WardrobeEquip", "0");
                            e.setCurrentItem(createItem(Material.INK_SACK, 1, 9, ChatColor.GRAY + "Slot " + currentSlot + ": " + ChatColor.GREEN + "Ready", ChatColor.GRAY + "This wardrobe slot is ready to", ChatColor.GRAY + "be equipped.", "", ChatColor.YELLOW + "Click to equip this armor set!"));
                            player.getEquipment().setHelmet(new ItemStack(Material.AIR));
                            player.getEquipment().setChestplate(new ItemStack(Material.AIR));
                            player.getEquipment().setLeggings(new ItemStack(Material.AIR));
                            player.getEquipment().setBoots(new ItemStack(Material.AIR));
                        }
                    }
                } else {
                    if (e.getClick().isShiftClick()) {
                        int startValue = -1;
                        if (getItemMaterial(e.getCurrentItem()).getType() == ItemType.HELMET) startValue = 0;
                        if (getItemMaterial(e.getCurrentItem()).getType() == ItemType.CHESTPLATE) startValue = 9;
                        if (getItemMaterial(e.getCurrentItem()).getType() == ItemType.LEGGINGS) startValue = 18;
                        if (getItemMaterial(e.getCurrentItem()).getType() == ItemType.BOOTS) startValue = 27;
                        if (startValue != -1) {
                            for (int i = startValue; i < 9 + startValue; i++) {
                                if (e.getInventory().getItem(i).getType() == Material.STAINED_GLASS_PANE && !StorageUtil.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0").equals((i - startValue + 1) + "")) {
                                    e.getInventory().setItem(i, e.getCurrentItem());
                                    e.setCurrentItem(new ItemStack(Material.AIR));
                                    break;
                                }
                            }
                        }
                        e.setCancelled(true);
                    }
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (player.getOpenInventory().getTitle().contains("Wardrobe"))
                            updateSlots(player);
                    }
                }.runTaskLater(plugin, 1L);
            }
        } catch (NullPointerException ignored) {}
    }

    private static void updateSlots(Player player) {
        boolean secondPage = (player.getOpenInventory().getTitle().contains("(2/2)"));

        int adder = (secondPage) ? 9 : 0;
        for (int i = 36; i < 45; i++) {
            if (player.getOpenInventory().getItem(i - 36).getType() == Material.STAINED_GLASS_PANE && player.getOpenInventory().getItem(i - 27).getType() == Material.STAINED_GLASS_PANE && player.getOpenInventory().getItem(i - 18).getType() == Material.STAINED_GLASS_PANE && player.getOpenInventory().getItem(i - 9).getType() == Material.STAINED_GLASS_PANE) {
                player.getOpenInventory().setItem(i, createItem(Material.INK_SACK, 1, 8, ChatColor.GRAY + "Slot " + (i - 35 + adder) + ": " + ChatColor.RED + "Empty", ChatColor.GRAY + "This wardrobe slot contains no", ChatColor.GRAY + "armor."));
            } else {
                player.getOpenInventory().setItem(i, createItem(Material.INK_SACK, 1, 9, ChatColor.GRAY + "Slot " + (i - 35 + adder) + ": " + ChatColor.GREEN + "Ready", ChatColor.GRAY + "This wardrobe slot is ready to", ChatColor.GRAY + "be equipped.", "", ChatColor.YELLOW + "Click to equip this armor set!"));
            }
        }
        int slot = Integer.parseInt(StorageUtil.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0"));
        if (slot != 0)
            if (secondPage && slot > 9) {
                player.getOpenInventory().setItem(slot + 26, createItem(Material.INK_SACK, 1, 10, ChatColor.GRAY + "Slot " + StorageUtil.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0") + ": " + ChatColor.GREEN + "Equipped", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            } else if (!secondPage && slot <= 9) {
                player.getOpenInventory().setItem(slot + 35, createItem(Material.INK_SACK, 1, 10, ChatColor.GRAY + "Slot " + StorageUtil.getVariableValue(player.getUniqueId(), "WardrobeEquip", "0") + ": " + ChatColor.GREEN + "Equipped", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            }
    }

    public static String getPeaceString(int peace) {
        if (peace == 0) return "Helmet";
        if (peace == 1) return "Chestplate";
        if (peace == 2) return "Leggings";
        if (peace == 3) return "Boots";
        return "";
    }
}

package me.maxiiiiii.skyblockdragons.player.wardrobe;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
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

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
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
            PlayerSD PlayerSD = players.get(player.getUniqueId());
            for (int i = 0; i < 36; i++) {
                if (e.getInventory().getItem(i).getType() != Material.STAINED_GLASS_PANE) {
                    PlayerSD.getWardrobe().getSlot((i % 9) + adder).setPeace(e.getInventory().getItem(i), i / 9);
                    Variables.set(player.getUniqueId(), "Wardrobe", i + (adder * 4), e.getInventory().getItem(i));
                } else {
                    PlayerSD.getWardrobe().getSlot((i % 9) + adder).setPeace(null, i / 9);
                    Variables.delete(player.getUniqueId(), "Wardrobe", i + (adder * 4));
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getClickedInventory().getType() == InventoryType.PLAYER || e.getClickedInventory().getType() == InventoryType.CREATIVE) {
                Player player = (Player) e.getWhoClicked();
                PlayerSD PlayerSD = players.get(player.getUniqueId());
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (Variables.get(player.getUniqueId(), "WardrobeEquip", 0) != 0) {
                            int slot = Variables.get(player.getUniqueId(), "WardrobeEquip", 0) - 1;

                            if (isNotAir(player.getEquipment().getHelmet())) {
                                PlayerSD.getWardrobe().getSlot(slot).setHelmet(player.getEquipment().getHelmet());
                                Variables.set(player.getUniqueId(), "Wardrobe", player.getEquipment().getHelmet(), slot);
                            } else {
                                Variables.delete(player.getUniqueId(), "Wardrobe", slot);
                                PlayerSD.getWardrobe().getSlot(slot).setPeace(null, 0);
                            }

                            if (isNotAir(player.getEquipment().getChestplate())) {
                                PlayerSD.getWardrobe().getSlot(slot).setChestplate(player.getEquipment().getChestplate());
                                Variables.set(player.getUniqueId(), "Wardrobe", player.getEquipment().getChestplate(), slot + 9);
                            } else {
                                Variables.delete(player.getUniqueId(), "Wardrobe", slot + 9);
                                PlayerSD.getWardrobe().getSlot(slot).setPeace(null, 1);
                            }

                            if (isNotAir(player.getEquipment().getLeggings())) {
                                PlayerSD.getWardrobe().getSlot(slot).setLeggings(player.getEquipment().getLeggings());
                                Variables.set(player.getUniqueId(), "Wardrobe", player.getEquipment().getLeggings(), slot + 18);
                            } else {
                                Variables.delete(player.getUniqueId(), "Wardrobe", slot + 18);
                                PlayerSD.getWardrobe().getSlot(slot).setPeace(null, 2);
                            }

                            if (isNotAir(player.getEquipment().getBoots())) {
                                PlayerSD.getWardrobe().getSlot(slot).setBoots(player.getEquipment().getBoots());
                                Variables.set(player.getUniqueId(), "Wardrobe", player.getEquipment().getBoots(), slot + 27);
                            } else {
                                Variables.delete(player.getUniqueId(), "Wardrobe", slot + 27);
                                PlayerSD.getWardrobe().getSlot(slot).setPeace(null, 3);
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
                        if (Variables.get(player.getUniqueId(), "WardrobeEquip", 0) == (e.getSlot() % 9) + 1) {
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
                        if (Variables.get(player.getUniqueId(), "WardrobeEquip", 0) != currentSlot) {
                            e.setCurrentItem(createItem(Material.INK_SACK, 1, 10, ChatColor.GRAY + "Slot " + currentSlot + ": " + ChatColor.GREEN + "Equipped", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));

                            updateSlots(player);

                            ItemStack helmet = (e.getInventory().getItem(e.getSlot() - 36).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 36) : new ItemStack(Material.AIR);
                            ItemStack chestplate = (e.getInventory().getItem(e.getSlot() - 27).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 27) : new ItemStack(Material.AIR);
                            ItemStack leggings = (e.getInventory().getItem(e.getSlot() - 18).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 18) : new ItemStack(Material.AIR);
                            ItemStack boots = (e.getInventory().getItem(e.getSlot() - 9).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 9) : new ItemStack(Material.AIR);
                            if (Variables.get(player.getUniqueId(), "WardrobeEquip", 0) == 0) {
                                if (isNotAir(player.getEquipment().getHelmet())) player.getInventory().addItem(player.getEquipment().getHelmet());
                                if (isNotAir(player.getEquipment().getChestplate())) player.getInventory().addItem(player.getEquipment().getChestplate());
                                if (isNotAir(player.getEquipment().getLeggings())) player.getInventory().addItem(player.getEquipment().getLeggings());
                                if (isNotAir(player.getEquipment().getBoots())) player.getInventory().addItem(player.getEquipment().getBoots());
                            }
                            player.getEquipment().setHelmet(helmet);
                            player.getEquipment().setChestplate(chestplate);
                            player.getEquipment().setLeggings(leggings);
                            player.getEquipment().setBoots(boots);

                            Variables.set(player.getUniqueId(), "WardrobeEquip", currentSlot);
                        } else {
                            Variables.set(player.getUniqueId(), "WardrobeEquip", 0);
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
                                if (e.getInventory().getItem(i).getType() == Material.STAINED_GLASS_PANE && Variables.get(player.getUniqueId(), "WardrobeEquip", 0) != i - startValue + 1) {
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
        int slot = Variables.get(player.getUniqueId(), "WardrobeEquip", 0);
        if (slot != 0)
            if (secondPage && slot > 9) {
                player.getOpenInventory().setItem(slot + 26, createItem(Material.INK_SACK, 1, 10, ChatColor.GRAY + "Slot " + Variables.get(player.getUniqueId(), "WardrobeEquip", 0) + ": " + ChatColor.GREEN + "Equipped", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            } else if (!secondPage && slot <= 9) {
                player.getOpenInventory().setItem(slot + 35, createItem(Material.INK_SACK, 1, 10, ChatColor.GRAY + "Slot " + Variables.get(player.getUniqueId(), "WardrobeEquip", 0) + ": " + ChatColor.GREEN + "Equipped", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
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

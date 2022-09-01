package me.maxiiiiii.skyblockdragons.player.wardrobe;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;
import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class WardrobeMenu extends Menu {
    private final int page;

    public WardrobeMenu(PlayerSD player, int page) {
        super(player, "Wardrobe", 6, InventoryGlassType.ALL, false);
        this.page = page;
    }

    @Override
    public void update() {
        int startValue = (page > 1) ? 9 : 0;
        for (int i = startValue; i < 9 + startValue; i++) {
            for (int j = 0; j < 4; j++) {
                this.setItem(Functions.numberToItemSlot(i, j), player.getWardrobe().getSlot(i).getPeace(j));
            }
        }

        int adder = (page > 1) ? 9 : 0;
        for (int i = 36; i < 45; i++) {
            if (player.getWardrobe().getSlot(i - 36 + adder).getHelmet().getType() == Material.STAINED_GLASS_PANE && player.getWardrobe().getSlot(i - 36 + adder).getChestplate().getType() == Material.STAINED_GLASS_PANE && player.getWardrobe().getSlot(i - 36 + adder).getLeggings().getType() == Material.STAINED_GLASS_PANE && player.getWardrobe().getSlot(i - 36 + adder).getBoots().getType() == Material.STAINED_GLASS_PANE) {
                inventory.setItem(i, createItem(Material.INK_SACK, 8, ChatColor.GRAY + "Slot " + ((i - 35) + ((page > 1) ? 9 : 0)) + ": " + ChatColor.RED + "Empty", "SLOT_EMPTY", ChatColor.GRAY + "This wardrobe slot contains no", ChatColor.GRAY + "armor."));
            } else {
                inventory.setItem(i, createItem(Material.INK_SACK, 9, ChatColor.GRAY + "Slot " + ((i - 35) + ((page > 1) ? 9 : 0)) + ": " + ChatColor.GREEN + "Ready", "SLOT_READY", ChatColor.GRAY + "This wardrobe slot is ready to", ChatColor.GRAY + "be equipped.", "", ChatColor.YELLOW + "Click to equip this armor set!"));
            }
        }

        int currentSlot = player.getWardrobe().getEquippedSlot();
        if (currentSlot != 0) {
            if (page > 1 && currentSlot > 9) {
                inventory.setItem(currentSlot + 26, createItem(Material.INK_SACK, 10, ChatColor.GRAY + "Slot " + (currentSlot) + ": " + ChatColor.GREEN + "Equipped", "SLOT_EQUIPPED", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            } else if (page < 2 && currentSlot <= 9) {
                inventory.setItem(currentSlot + 35, createItem(Material.INK_SACK, 10, ChatColor.GRAY + "Slot " + (currentSlot) + ": " + ChatColor.GREEN + "Equipped", "SLOT_EQUIPPED", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            }
        }

        if (page > 1) {
            ItemStack previousPage = createItem(Material.ARROW, 1, ChatColor.GREEN + "Previous Page", "PREVIOUS_PAGE", ChatColor.YELLOW + "Page 1");
            this.setItem(45, previousPage);
        } else {
            ItemStack nextPage = createItem(Material.ARROW, 1, ChatColor.GREEN + "Next Page", "NEXT_PAGE", ChatColor.YELLOW + "Page 2");
            this.setItem(53, nextPage);
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());
        if (e.getClickedInventory().getTitle().contains("Wardrobe")) {

            boolean secondPage = (e.getInventory().getTitle().contains("(2/2)"));

            if (e.getSlot() == 53 && e.getCurrentItem().getType() == Material.ARROW) {
                new WardrobeMenu(player, 2);
                return;
            } else if (e.getSlot() == 45 && e.getCurrentItem().getType() == Material.ARROW) {
                new WardrobeMenu(player, 1);
                return;
            }

            e.setCancelled(false);
            if (e.getSlot() > 35)
                e.setCancelled(true);
            else {
                if (player.getWardrobe().getEquippedSlot() == (e.getSlot() % 9) + 1) {
                    e.setCancelled(true);
                    return;
                }

                if (e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
                    int finalSlot = (e.getSlot() % 9) + ((secondPage) ? 9 : 0);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            e.setCurrentItem(createItem(Material.STAINED_GLASS_PANE, WardrobeSlot.numberToWardrobeColor(e.getSlot()), ChatColor.GREEN + "Slot " + ((finalSlot % 9) + 1) + " " + getPeaceString(e.getSlot() / 9).toUpperCase(), "SLOT_" + getPeaceString(e.getSlot() / 9), ChatColor.GRAY + "Place a " + getPeaceString(e.getSlot() / 9) + " here to add", ChatColor.GRAY + "it to the armor set."));
                        }
                    }.runTaskLater(plugin, 1L);
                } else {
                    if ((e.getSlot() / 9 == 0 && getItemMaterial(e.getCursor()).getType() == ItemType.HELMET) || (e.getSlot() / 9 == 1 && getItemMaterial(e.getCursor()).getType() == ItemType.CHESTPLATE) || (e.getSlot() / 9 == 2 && getItemMaterial(e.getCursor()).getType() == ItemType.LEGGINGS) || (e.getSlot() / 9 == 3 && getItemMaterial(e.getCursor()).getType() == ItemType.BOOTS)) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.setItemOnCursor(new ItemStack(Material.AIR));
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
                if (player.getWardrobe().getEquippedSlot() != currentSlot) {
                    e.setCurrentItem(createItem(Material.INK_SACK, 10, ChatColor.GRAY + "Slot " + currentSlot + ": " + ChatColor.GREEN + "Equipped", "SLOT_EQUIPPED", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));

                    updateSlots(player);

                    ItemStack helmet = (e.getInventory().getItem(e.getSlot() - 36).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 36) : new ItemStack(Material.AIR);
                    ItemStack chestplate = (e.getInventory().getItem(e.getSlot() - 27).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 27) : new ItemStack(Material.AIR);
                    ItemStack leggings = (e.getInventory().getItem(e.getSlot() - 18).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 18) : new ItemStack(Material.AIR);
                    ItemStack boots = (e.getInventory().getItem(e.getSlot() - 9).getType() != Material.STAINED_GLASS_PANE) ? e.getInventory().getItem(e.getSlot() - 9) : new ItemStack(Material.AIR);
                    if (player.getWardrobe().getEquippedSlot() == 0) {
                        if (isNotAir(player.getEquipment().getHelmet())) player.getInventory().addItem(player.getEquipment().getHelmet());
                        if (isNotAir(player.getEquipment().getChestplate())) player.getInventory().addItem(player.getEquipment().getChestplate());
                        if (isNotAir(player.getEquipment().getLeggings())) player.getInventory().addItem(player.getEquipment().getLeggings());
                        if (isNotAir(player.getEquipment().getBoots())) player.getInventory().addItem(player.getEquipment().getBoots());
                    }
                    player.getEquipment().setHelmet(helmet);
                    player.getEquipment().setChestplate(chestplate);
                    player.getEquipment().setLeggings(leggings);
                    player.getEquipment().setBoots(boots);

                    player.getWardrobe().setEquippedSlot(currentSlot);
                } else {
                    player.getWardrobe().setEquippedSlot(0);
                    e.setCurrentItem(createItem(Material.INK_SACK, 9, ChatColor.GRAY + "Slot " + currentSlot + ": " + ChatColor.GREEN + "Ready", "SLOT_READY", ChatColor.GRAY + "This wardrobe slot is ready to", ChatColor.GRAY + "be equipped.", "", ChatColor.YELLOW + "Click to equip this armor set!"));
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
                        if (e.getInventory().getItem(i).getType() == Material.STAINED_GLASS_PANE && player.getWardrobe().getEquippedSlot() != i - startValue + 1) {
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

    @Override
    public void onInventoryClose(InventoryCloseEvent e) {
        boolean secondPage = this.page == 2;

        int adder = 0;
        if (secondPage) adder = 9;

        PlayerSD player = SkyblockDragons.getPlayer((org.bukkit.entity.Player) e.getPlayer());
        for (int i = 0; i < 36; i++) {
            if (e.getInventory().getItem(i).getType() != Material.STAINED_GLASS_PANE) {
                player.getWardrobe().getSlot((i % 9) + adder).setPeace(e.getInventory().getItem(i), i / 9);
            } else {
                player.getWardrobe().getSlot((i % 9) + adder).setPeace(null, i / 9);
            }
        }
    }

    private static void updateSlots(PlayerSD player) {
        boolean secondPage = (player.getOpenInventory().getTitle().contains("(2/2)"));

        int adder = (secondPage) ? 9 : 0;
        for (int i = 36; i < 45; i++) {
            if (player.getOpenInventory().getItem(i - 36).getType() == Material.STAINED_GLASS_PANE && player.getOpenInventory().getItem(i - 27).getType() == Material.STAINED_GLASS_PANE && player.getOpenInventory().getItem(i - 18).getType() == Material.STAINED_GLASS_PANE && player.getOpenInventory().getItem(i - 9).getType() == Material.STAINED_GLASS_PANE) {
                player.getOpenInventory().setItem(i, createItem(Material.INK_SACK, 8, ChatColor.GRAY + "Slot " + (i - 35 + adder) + ": " + ChatColor.RED + "Empty", "SLOT_EQUIPPED", ChatColor.GRAY + "This wardrobe slot contains no", ChatColor.GRAY + "armor."));
            } else {
                player.getOpenInventory().setItem(i, createItem(Material.INK_SACK, 9, ChatColor.GRAY + "Slot " + (i - 35 + adder) + ": " + ChatColor.GREEN + "Ready", "SLOT_READY", ChatColor.GRAY + "This wardrobe slot is ready to", ChatColor.GRAY + "be equipped.", "", ChatColor.YELLOW + "Click to equip this armor set!"));
            }
        }
        int slot = player.getWardrobe().getEquippedSlot();
        if (slot != 0)
            if (secondPage && slot > 9) {
                player.getOpenInventory().setItem(slot + 26, createItem(Material.INK_SACK, 10, ChatColor.GRAY + "Slot " + player.getWardrobe().getEquippedSlot() + ": " + ChatColor.GREEN + "Equipped", "SLOT_EQUIPPED", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            } else if (!secondPage && slot <= 9) {
                player.getOpenInventory().setItem(slot + 35, createItem(Material.INK_SACK, 10, ChatColor.GRAY + "Slot " + player.getWardrobe().getEquippedSlot() + ": " + ChatColor.GREEN + "Equipped", "SLOT_EQUIPPED", ChatColor.GRAY + "This wardrobe slot contains your", ChatColor.GRAY + "current armor set", "", ChatColor.YELLOW + "Click to unequip this armor set!"));
            }
    }

    public static String getPeaceString(int peace) {
        if (peace == 0) return "Helmet";
        if (peace == 1) return "Chestplate";
        if (peace == 2) return "Leggings";
        if (peace == 3) return "Boots";
        return "";
    }

    public static class Command extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            if (args.length > 0) {
                if (isPlayerName(args[0])) {
                    player = SkyblockDragons.getPlayer(args[0]);
                } else {
                    player.sendMessage(ChatColor.RED + "There is no player with name " + args[0]);
                }
            }
            new WardrobeMenu(player, 1);
        }

        @Override
        public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
            return tabs;
        }
    }
}

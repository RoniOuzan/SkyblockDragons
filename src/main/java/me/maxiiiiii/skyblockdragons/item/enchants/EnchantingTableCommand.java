package me.maxiiiiii.skyblockdragons.item.enchants;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.EnchantModifier;
import me.maxiiiiii.skyblockdragons.item.modifiers.ItemModifiers;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnchantingTableCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            EnchantingTableMenu.openEnchantingTable((Player) sender, null);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        Player player = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals("Enchanting Table")) {
            if (e.getSlot() != 19 && e.getClickedInventory().getTitle().equals("Enchanting Table")) e.setCancelled(true);

            ItemStack itemStack = e.getInventory().getItem(19);
            Functions.Wait(1L, () -> {
                if (Functions.isNotAir(e.getInventory().getItem(19)) && !e.getInventory().getItem(19).isSimilar(itemStack))
                    updateEnchantingTable(e.getInventory(), 1);
                else if (!Functions.isNotAir(itemStack)) {
                    for (int i = 0; i < 15; i++) {
                        e.getInventory().setItem(EnchantingTableMenu.intToSlot(i), Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
                    }
                e.getInventory().setItem(26, Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
                e.getInventory().setItem(35, Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
                }
            });

            if (Functions.isNotAir(e.getCurrentItem()) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next Page")) {
                updateEnchantingTable(e.getInventory(), 2);
            }

            if (Functions.isNotAir(e.getCurrentItem()) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Previous Page")) {
                updateEnchantingTable(e.getInventory(), 1);
            }

            if (e.getCurrentItem().getType() == Material.ENCHANTED_BOOK) {
                EnchantType enchantType = EnchantType.enchants.get(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().replace(" ", "_").toUpperCase()));
                EnchantingTableMenu.openEnchantMenu(player, enchantType, new Item(SkyblockDragons.getPlayer(player), e.getInventory().getItem(19)));
            }
        } else if (e.getInventory().getTitle().equals("Enchant Item")) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType() == Material.ENCHANTED_BOOK) {
                int cost = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().stream().filter(s -> s.contains("Costs: ")).map(ChatColor::stripColor).collect(Collectors.toList()).get(0).split(" ")[1]);
                Item requirementItem = new Item(SkyblockDragons.getPlayer(player), e.getCurrentItem());
                if (player.getGameMode() != GameMode.CREATIVE) {
                    for (EnchantType enchantType : requirementItem.getModifiers().getEnchants().keySet()) {
                        if (SkyblockDragons.getPlayer(player).getSkills().getEnchantingSkill().getLevel() < enchantType.getRequirements().getRequirement(0).getLevel()) {
                            player.sendMessage(ChatColor.RED + "You don't have the requirements to apply this enchant!");
                            player.closeInventory();
                            return;
                        }
                    }
                }
                if (player.getLevel() < cost && player.getGameMode() != GameMode.CREATIVE) {
                    player.sendMessage(ChatColor.RED + "You don't have enough level to apply this enchant!");
                    player.closeInventory();
                    return;
                }
                player.setLevel(player.getLevel() - cost);
                ItemStack fromItem = e.getInventory().getItem(13);
                Map<EnchantType, Short> enchants = ItemModifiers.getModifiers(fromItem).getEnchants();
                Map<EnchantType, Short> enchantsToAdd = ItemModifiers.getModifiers(e.getCurrentItem()).getEnchants();
                for (EnchantType enchantType : enchantsToAdd.keySet()) {
                    if (Objects.equals(enchants.getOrDefault(enchantType, (short) 0), enchantsToAdd.get(enchantType)))
                        enchants.remove(enchantType);
                    else
                        enchants.put(enchantType, enchantsToAdd.get(enchantType));
                }
                Item item = new Item(SkyblockDragons.getPlayer(player), Items.get(fromItem), ItemModifiers.getModifiers(fromItem), new EnchantModifier(enchants));
                EnchantingTableMenu.openEnchantingTable(player, item);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            }
        }
    }

    public void updateEnchantingTable(Inventory inventory, int page) {
        if (!Functions.isNotAir(inventory.getItem(19))) {
            for (int i = 0; i < 15; i++) {
                inventory.setItem(EnchantingTableMenu.intToSlot(i), Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
            }
            inventory.setItem(26, Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
            inventory.setItem(35, Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
            return;
        }

        ItemMaterial material = Items.get(inventory.getItem(19));

        int i = 0;
        int slot = 0;
        for (EnchantType enchantType : EnchantType.enchants.values()) {
            if (!enchantType.getTypes().contains(material.getType()) || !enchantType.isInEnchantingTable())
                continue;
            if (i < (page - 1) * 15) {
                i++;
                continue;
            }
            if (i >= page * 15 && page == 1) {
                inventory.setItem(26, Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
                inventory.setItem(35, Functions.createItem(Material.ARROW, ChatColor.GREEN + "Next Page", ChatColor.YELLOW + "Click to move page!"));
                break;
            }

            List<String> lores =  Functions.loreBuilder(enchantType.getDescription(1));
            lores.add("");
            lores.add(ChatColor.YELLOW + "Click to view!");
            ItemStack enchantItem = Functions.createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + enchantType.toString(), lores);
            inventory.setItem(EnchantingTableMenu.intToSlot(slot), enchantItem);
            i++;
            slot++;
        }

        if (page == 2) {
            inventory.setItem(26, Functions.createItem(Material.ARROW, ChatColor.GREEN + "Previous Page", ChatColor.YELLOW + "Click to move page!"));
            inventory.setItem(35, Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
        } else {
            inventory.setItem(26, Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
        }

        for (int j = slot; j < 15; j++) {
            inventory.setItem(EnchantingTableMenu.intToSlot(j), Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + ""));
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getTitle().equals("Enchanting Table")) {
            Functions.Wait(1L, () -> {
                if (!e.getPlayer().getOpenInventory().getTopInventory().getTitle().equals("Enchant Item")) {
                    if (Functions.isNotAir(e.getInventory().getItem(19))) {
                        e.getPlayer().getInventory().addItem(e.getInventory().getItem(19));
                    }
                }
            });
        } else if (e.getInventory().getTitle().equals("Enchant Item")) {
            Functions.Wait(1L, () -> {
                if (!e.getPlayer().getOpenInventory().getTopInventory().getTitle().equals("Enchanting Table"))
                    e.getPlayer().getInventory().addItem(e.getInventory().getItem(13));
            });
        }
    }
}

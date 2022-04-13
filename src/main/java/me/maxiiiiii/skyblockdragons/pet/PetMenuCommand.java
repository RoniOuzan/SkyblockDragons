package me.maxiiiiii.skyblockdragons.pet;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.util.Functions.getId;

public class PetMenuCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PetMenu.openPetMenu(player, (args.length > 0 ? Integer.parseInt(args[0]) : 1));
        }
        return true;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getTitle().contains("Pet Menu")) {
            PlayerSD player = SkyblockDragons.players.get(e.getPlayer().getUniqueId());
            String[] title = e.getInventory().getTitle().split(" ");
            int page = Integer.parseInt(title[2]);

            ArrayList<Pet> pets = player.getPlayerPet().getPets();
            for (int i = 0; i < 28; i++) {
                if (Functions.isNotAir(e.getInventory().getItem(Functions.intToSlot(i)))) {

                    if (i + (28 * (page - 1)) < pets.size()) {
                        Pet pet = Pet.getPet(e.getInventory().getItem(Functions.intToSlot(i)), false);
                        pets.set(i + (28 * (page - 1)), pet);
                    }
                } else if (i + (28 * (page - 1)) < pets.size()) {
                    pets.remove(i + (28 * (page - 1)));
                }
            }
            player.getPlayerPet().setPets(pets);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getClickedInventory().getTitle().contains("Pet Menu")) {
                e.setCancelled(true);
                ItemStack item = e.getCurrentItem();
                PlayerSD player = SkyblockDragons.players.get(e.getWhoClicked().getUniqueId());
                String[] title = e.getClickedInventory().getTitle().split(" ");
                int page = Integer.parseInt(title[2]);

                if (item.getItemMeta().getDisplayName().contains("Next Page")) {
                    player.closeInventory();
                    PetMenu.openPetMenu(player, page + 1, e.getInventory().getItem(50).getDurability() == 10);
                } else if (item.getItemMeta().getDisplayName().contains("Previous Page")) {
                    if (page == 1) {
                        player.sendMessage(ChatColor.RED + "You can't go to the previous page!");
                    } else {
                        PetMenu.openPetMenu(player, page - 1, e.getInventory().getItem(50).getDurability() == 10);
                    }
                } else if (item.getItemMeta().getDisplayName().contains("Convert Pet to an Item")) {
                    e.getClickedInventory().setItem(e.getSlot(), item.getDurability() == 8 ? PetMenu.convertPetEnabled : PetMenu.convertPetDisabled);
                } else if (item.getItemMeta().getDisplayName().contains("Hide Pets")) {
                    player.getPlayerPet().hidePets = !player.getPlayerPet().hidePets;
                    player.sendMessage((player.getPlayerPet().hidePets ? ChatColor.GREEN : ChatColor.RED) + "Hide Pets is now " + (player.getPlayerPet().hidePets ? "Enabled!" : "Disabled!"));
                    player.closeInventory();
                }

                if (!getId(item).contains("_PET")) return;

                if (e.getInventory().getItem(50).getDurability() == 10) {
                    if (player.getPlayerPet().activePet >= 0) {
                        player.sendMessage(ChatColor.RED + "You have to despawn your pet!");
                        return;
                    }
                    player.getInventory().addItem(Pet.getBetterPet(item));
                    e.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                    player.closeInventory();
                    PetMenu.openPetMenu(player, page, true);
                } else {
                    if (player.getPlayerPet().getActivePet() >= 0 && player.getPlayerPet().getActivePet() == Functions.slotToInt(e.getSlot(), page)) {
                        player.setActivePet(-1);
                        player.sendMessage(ChatColor.GREEN + "You despawned your " + item.getItemMeta().getDisplayName() + ChatColor.GREEN + "!");
                    } else {
                        player.setActivePet(Functions.slotToInt(e.getSlot(), page));
                        player.sendMessage(ChatColor.GREEN + "You summoned your " + item.getItemMeta().getDisplayName() + ChatColor.GREEN + "!");
                    }
                    player.getPlayerPet().petArmorStand.armorStand.remove();
                    player.getPlayerPet().petArmorStand.hologram.delete();
                    player.getPlayerPet().petArmorStand.slot = -1;
                    player.closeInventory();
                }
            }
        } catch (NullPointerException ignored) {}
    }
}
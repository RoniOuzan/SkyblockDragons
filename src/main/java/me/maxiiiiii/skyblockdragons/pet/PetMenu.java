package me.maxiiiiii.skyblockdragons.pet;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class PetMenu {
    public static ItemStack convertPetDisabled = createItem(Material.INK_SACK, 1, 8, ChatColor.GREEN + "Convert Pet to an Item", ChatColor.GRAY + "Enable this setting and click", ChatColor.GRAY + "any pet to convert it to an", ChatColor.GRAY + "item.", "", ChatColor.RED + "Disabled");
    public static ItemStack convertPetEnabled = createItem(Material.INK_SACK, 1, 10, ChatColor.GREEN + "Convert Pet to an Item", ChatColor.GRAY + "Enable this setting and click", ChatColor.GRAY + "any pet to convert it to an", ChatColor.GRAY + "item.", "", ChatColor.GREEN + "Enabled");

    public static void openPetMenu(Player player, int page) {
        openPetMenu(player, page, false);
    }

    public static void openPetMenu(Player player, int page, boolean isConvertToItem) {
        PlayerSD playerSD = SkyblockDragons.players.get(player.getUniqueId());
        Inventory inventory = Bukkit.createInventory(player, 54, "Pet Menu " + page);

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta glassItemMeta = glass.getItemMeta();
        glassItemMeta.setDisplayName(ChatColor.RESET + "");
        glass.setItemMeta(glassItemMeta);

        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, glass);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        setName(close, ChatColor.RED + "Close");
        setLore(close, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to close!")));
        inventory.setItem(49, close);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(48, goBack);

//        if (isConvertToItem) {
//            inventory.setItem(50, convertPetEnabled);
//        }
//        else {
            inventory.setItem(50, convertPetDisabled);
//        }

        ItemStack hidePets = createItem(Material.STONE_BUTTON, (playerSD.getPlayerPet().hidePets ? ChatColor.RED : ChatColor.GREEN) + "Hide Pets", ChatColor.GRAY + "Hide all pets which are little", ChatColor.GRAY + "heads from being visible in the", ChatColor.GRAY + "world.", "", ChatColor.GRAY + "Pet effects remain active.", "", ChatColor.GRAY + "Currently " + (playerSD.getPlayerPet().hidePets ? ChatColor.RED + "Pets hidden!" : ChatColor.GREEN + "Pets shown!"), ChatColor.GRAY + "Selected Pet: " + (playerSD.getPlayerPet().activePet < 0 ? ChatColor.RED + "None" : playerSD.getPetActive().getName()), "", ChatColor.YELLOW + "Click to " + (playerSD.getPlayerPet().hidePets ? "show!" : "hide!"));
        inventory.setItem(51, hidePets);

        ItemStack nextPage = new ItemStack(Material.ARROW);
        Functions.setName(nextPage, ChatColor.GREEN + "Next Page");
        Functions.setLore(nextPage, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to go next page!")));
        inventory.setItem(53, nextPage);

        ItemStack previousPage = new ItemStack(Material.ARROW);
        Functions.setName(previousPage, ChatColor.GREEN + "Previous Page");
        Functions.setLore(previousPage, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to go previous page!")));
        inventory.setItem(45, previousPage);

        Collections.sort(playerSD.getPlayerPet().getPets());
        Pet[] pets = playerSD.getPlayerPet().getPets().toArray(new Pet[0]);
        for (int i = 0; i < pets.length; i++) {
            ArrayList<String> lores = (ArrayList<String>) pets[i].getItemMeta().getLore();

            if (lores.get(lores.size() - 1).contains("Click to ")) continue;

            lores.add("");
            if (playerSD.getPlayerPet().getActivePet() == i)
                lores.add(ChatColor.RED + "Click to despawn!");
            else
                lores.add(ChatColor.YELLOW + "Click to summon!");
            setLore(pets[i], lores);
        }

        int length = 28 * (page - 1);
        for (int i = 0; i < 28; i++) {
            try {
                inventory.setItem(Functions.intToSlot(i), pets[i + length]);
            } catch (IndexOutOfBoundsException e){
                inventory.setItem(Functions.intToSlot(i), new ItemStack(Material.AIR));
            }
        }

        player.openInventory(inventory);
    }
}

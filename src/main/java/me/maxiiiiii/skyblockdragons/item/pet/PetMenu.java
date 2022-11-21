package me.maxiiiiii.skyblockdragons.item.pet;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PetMenu extends PageMenu {
    private boolean isConvertToItem; // TODO: make more stats

    public PetMenu(PlayerSD player) {
        super(player,
                "Pet Menu",
                6,
                InventoryGlassType.SURROUND,
                () -> player.getPlayerPet().getPets().stream().map(pet -> {
                    Item activePet = player.getActivePet();
                    if (pet.equals(activePet)) {
                        return addLine(addNBT(pet, "ACTIVE"), "", ChatColor.RED + "Click to despawn!");
                    }
                    return addLine(pet, "", ChatColor.YELLOW + "Click to summon!");
                }).collect(Collectors.toList()),
                false);
        this.isConvertToItem = false;
    }

    @Override
    public void update() {
        super.update();
        if (this.isConvertToItem) {
            this.setItem(50, createItem(Material.INK_SACK, 10, ChatColor.GREEN + "Convert Pet to an Item", "CONVERT_DISABLED", ChatColor.GRAY + "Enable this setting and click", ChatColor.GRAY + "any pet to convert it to an", ChatColor.GRAY + "item.", "", ChatColor.GREEN + "Enabled"));
        } else {
            this.setItem(50, createItem(Material.INK_SACK, 8, ChatColor.GREEN + "Convert Pet to an Item", "CONVERT_ENABLED", ChatColor.GRAY + "Enable this setting and click", ChatColor.GRAY + "any pet to convert it to an", ChatColor.GRAY + "item.", "", ChatColor.RED + "Disabled"));
        }

        this.setItem(51, createItem(Material.STONE_BUTTON, (player.getPlayerPet().isHidePets() ? ChatColor.RED : ChatColor.GREEN) + "Hide Pets", "HIDE_PETS_" + (player.getPlayerPet().isHidePets() ? "ENABLED" : "DISABLED"), ChatColor.GRAY + "Hide all pets which are little", ChatColor.GRAY + "heads from being visible in the", ChatColor.GRAY + "world.", "", ChatColor.GRAY + "Pet effects remain active.", "", ChatColor.GRAY + "Currently " + (player.getPlayerPet().isHidePets() ? ChatColor.RED + "Pets hidden!" : ChatColor.GREEN + "Pets shown!"), ChatColor.GRAY + "Selected Pet: " + (player.getPlayerPet().getActivePetSlot() < 0 ? ChatColor.RED + "None" : player.getActivePet().getMaterial().getName()), "", ChatColor.YELLOW + "Click to " + (player.getPlayerPet().isHidePets() ? "show!" : "hide!")));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (this.getNBT(e.getCurrentItem()).contains("CONVERT_")) {
            this.isConvertToItem = !this.isConvertToItem;
        }

        if (this.getNBT(e.getCurrentItem()).contains("HIDE_PETS_")) {
            this.player.getPlayerPet().setHidePets(!this.player.getPlayerPet().isHidePets());
        }

        if (!this.isConvertToItem) {
            if (this.getNBT(e.getCurrentItem()).equals("ACTIVE")) {
                this.player.setActivePet(-1);
                this.player.getPlayerPet().getVisual().clear();
                this.player.closeInventory();
            } else if (this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM")) {
                if (this.player.getPlayerPet().getVisual() != null) {
                    this.player.getPlayerPet().getVisual().clear();
                }
                this.player.setActivePet(Functions.slotToInt(e.getSlot(), this.page));
                this.player.closeInventory();
            }
        } else {
            if (player.getPlayerPet().getActivePetSlot() < 0 && this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM")) {
                player.give(new Item(player, e.getCurrentItem()));
                this.player.getPlayerPet().removePet(new Item(player, e.getCurrentItem()));
            } else
                player.sendMessage(ChatColor.RED + "You have to despawn this pet to convert it!");
        }

        this.update();
    }

    private static ItemStack addLine(ItemStack itemStack, String... lores) {
        ItemStack item = itemStack.clone();
        ItemMeta meta = item.getItemMeta();
        List<String> newLores = meta.getLore();
        newLores.addAll(Arrays.asList(lores));
        meta.setLore(newLores);
        item.setItemMeta(meta);
        return item;
    }

    public static class Command extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            new PetMenu(player);
        }

        @Override
        public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
            return tabs;
        }
    }
}

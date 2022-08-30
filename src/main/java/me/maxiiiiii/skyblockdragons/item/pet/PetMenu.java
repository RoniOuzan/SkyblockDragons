package me.maxiiiiii.skyblockdragons.item.pet;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;
import java.util.stream.Collectors;

public class PetMenu extends PageMenu {
    private boolean isConvertToItem;

    public PetMenu(PlayerSD player) {
        super(player, "Pet Menu", 6, InventoryGlassType.SURROUND, player.getPlayerPet().getPets().stream().sorted().map(pet -> player.getPlayerPet().getPetActive().equals(pet) ? Functions.addLine(pet, "", ChatColor.YELLOW + "Click to summon!") : Functions.addLine(addNBT(pet, "ACTIVE"), "", ChatColor.RED + "Click to despawn!")).collect(Collectors.toList()), true);
        this.isConvertToItem = false;
    }

    @Override
    public void update() {
        if (this.isConvertToItem) {
            this.setItem(50, createItem(Material.INK_SACK, 10, ChatColor.GREEN + "Convert Pet to an Item", "CONVERT_DISABLED", ChatColor.GREEN + "Convert Pet to an Item", ChatColor.GRAY + "Enable this setting and click", ChatColor.GRAY + "any pet to convert it to an", ChatColor.GRAY + "item.", "", ChatColor.GREEN + "Enabled"));
        } else {
            this.setItem(50, createItem(Material.INK_SACK, 8, ChatColor.GREEN + "Convert Pet to an Item", "CONVERT_ENABLED", ChatColor.GRAY + "Enable this setting and click", ChatColor.GRAY + "any pet to convert it to an", ChatColor.GRAY + "item.", "", ChatColor.RED + "Disabled"));
        }

        this.setItem(51, createItem(Material.STONE_BUTTON, (player.getPlayerPet().hidePets ? ChatColor.RED : ChatColor.GREEN) + "Hide Pets", "HIDE_PETS_" + (player.getPlayerPet().hidePets ? "ENABLED" : "DISABLED"), ChatColor.GRAY + "Hide all pets which are little", ChatColor.GRAY + "heads from being visible in the", ChatColor.GRAY + "world.", "", ChatColor.GRAY + "Pet effects remain active.", "", ChatColor.GRAY + "Currently " + (player.getPlayerPet().hidePets ? ChatColor.RED + "Pets hidden!" : ChatColor.GREEN + "Pets shown!"), ChatColor.GRAY + "Selected Pet: " + (player.getPlayerPet().activePet < 0 ? ChatColor.RED + "None" : player.getPetActive().getName()), "", ChatColor.YELLOW + "Click to " + (player.getPlayerPet().hidePets ? "show!" : "hide!")));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (this.getNBT(e.getCurrentItem()).contains("CONVERT_")) {
            this.isConvertToItem = !this.isConvertToItem;
        }

        if (this.getNBT(e.getCurrentItem()).contains("HIDE_PETS_")) {
            this.player.getPlayerPet().hidePets = !this.player.getPlayerPet().hidePets;
        }

        if (this.getNBT(e.getCurrentItem()).equals("ACTIVE")) {
            this.player.setActivePet(-1);

            this.player.getPlayerPet().petArmorStand.armorStand.remove();
            this.player.getPlayerPet().petArmorStand.hologram.delete();
            this.player.getPlayerPet().petArmorStand.slot = -1;
        } else if (this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM")) {
            this.player.setActivePet(Functions.slotToInt(e.getSlot(), this.page));

            this.player.getPlayerPet().petArmorStand.armorStand.remove();
            this.player.getPlayerPet().petArmorStand.hologram.delete();
            this.player.getPlayerPet().petArmorStand.slot = -1;
        }
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

package me.maxiiiiii.skyblockdragons.item.pet;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PlayerPet {
    private PlayerSD player;
    private int activePetSlot;
    private List<Item> pets;
    private PetVisual visual;
    private boolean hidePets;

    public PlayerPet(PlayerSD player) {
        this.player = player;
        this.activePetSlot = Variables.getInt(player.getUniqueId(), "ActivePet", 0, -1);
        this.pets = new ArrayList<>();
        for (int i = 0; i < 112; i++) {
            ItemStack itemStack = Variables.getItemStack(player.getUniqueId(), "Pets", i);
            if (itemStack == null) {
                continue;
            }
            this.pets.add(new Item(player, itemStack));
        }
        Collections.sort(this.pets);
        this.hidePets = Variables.getBoolean(player.getUniqueId(), "HidePets", 0, false);
        
        this.updateVisual();
    }

    public void addPet(Item item) {
        Item oldPet = null;
        if (activePetSlot >= 0) {
            this.getVisual().clear();
            oldPet = this.getActivePet();
        }

        this.pets.add(item);
        this.sort();

        if (oldPet != null) {
            for (int i = 0; i < this.pets.size(); i++) {
                Item pet = this.pets.get(i);
                if (pet.equals(oldPet)) {
                    this.activePetSlot = i;
                    i = 113;
                }
            }
        }
    }

    public void removePet(Item item) {
        Item oldPet = null;
        if (activePetSlot >= 0) {
            this.getVisual().clear();
            oldPet = this.getActivePet();
        }

        this.pets.remove(item);
        this.sort();

        if (oldPet != null) {
            for (int i = 0; i < this.pets.size(); i++) {
                Item pet = this.pets.get(i);
                if (pet.equals(oldPet)) {
                    this.activePetSlot = i;
                    i = 113;
                }
            }
        }
    }

    private void sort() {
        Collections.sort(this.pets);
    }

    public void updateActivePet() {
        if (activePetSlot >= 0)
            pets.set(player.getPlayerPet().activePetSlot, player.getActivePet());
        player.updatePlayerInventory();
    }

    public void updateVisual() {
        if (this.activePetSlot < 0 || isActiveDoesNotExist())
            this.visual = null;
        else
            this.visual = new PetVisual(player, this.getActivePet(), this.activePetSlot);
    }

    public void levelUp() {
        double currentXp = this.getActivePet().getModifiers().getPet().getCurrentXp();
        int level = this.getActivePet().getModifiers().getPet().getLevel();
        double needXp = player.getActivePetMaterial().getNeedXp(level);
        int maxLevel = player.getActivePet().getPlayer().getActivePetMaterial().getMaxLevel();
        boolean did = false;
        while (currentXp >= needXp && level < maxLevel) {
            currentXp -= needXp;
            this.getActivePet().getModifiers().getPet().levelUp();

            visual.getHologram().removeLine(0);
            visual.getHologram().appendTextLine(PetVisual.getArmorStandName(player, this.getActivePet()));

            player.sendMessage(ChatColor.GREEN + "Your " + this.getActivePet().getRarity().getColor() + this.getActivePet().getMaterial().getName() + ChatColor.GREEN + " levelled up to level " + ChatColor.BLUE + level + ChatColor.GREEN + "!");

            did = true;
        }
        if (!did) {
            this.updateActivePet();
        }
    }

    public Item getActivePet() {
        if (this.activePetSlot == -1 || isActiveDoesNotExist())
            return null;

        return this.pets.get(this.activePetSlot);
    }

    private boolean isActiveDoesNotExist() {
        if (this.activePetSlot >= this.pets.size()) {
            SkyblockDragons.logger.severe("Active Pet doesn't exist");
            this.activePetSlot = -1;
            return true;
        }
        return false;
    }

    public void save() {
        Variables.set(player.getUniqueId(), "ActivePet", 0, this.activePetSlot);
        Variables.delete(player.getUniqueId(), "Pets");
        for (int i = 0; i < this.pets.size(); i++) {
            Variables.set(player.getUniqueId(), "Pets", i, this.pets.get(i));
        }
        Variables.set(player.getUniqueId(), "HidePets", 0, hidePets ? 1 : 0);
    }
}

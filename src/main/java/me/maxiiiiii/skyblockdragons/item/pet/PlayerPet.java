package me.maxiiiiii.skyblockdragons.item.pet;

import lombok.Getter;
import lombok.Setter;
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
        this.activePetSlot = Variables.get(player.getUniqueId(), "ActivePet", 0, -1);
        this.pets = new ArrayList<>();
        for (int i = 0; i < 112; i++) {
            ItemStack itemStack = Variables.get(player.getUniqueId(), "Pets", i);
            if (itemStack == null) {
                break;
            }
            this.pets.add(new Item(player, itemStack));
        }
        Collections.sort(this.pets);
        this.hidePets = Variables.get(player.getUniqueId(), "HidePets", 0, 0) == 1;
        
        this.updateVisual();
    }

    public void updateActivePet() {
        pets.set(player.getPlayerPet().activePetSlot, player.getActivePet());
        player.updatePlayerInventory();
    }

    public void updateVisual() {
        if (this.activePetSlot < 0)
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
        if (this.activePetSlot == -1)
            return null;
        return this.pets.get(this.activePetSlot);
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

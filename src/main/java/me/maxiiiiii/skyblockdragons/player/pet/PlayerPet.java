package me.maxiiiiii.skyblockdragons.player.pet;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PlayerPet {
    public PlayerSD player;
    public int activePet;
    public ArrayList<Pet> pets;
    public Pet.ArmorStand petArmorStand;
    public boolean hidePets;

    public PlayerPet(PlayerSD player) {
        this.player = player;
        this.activePet = Variables.get(player.getUniqueId(), "ActivePet", 0, -1);
        this.pets = new ArrayList<>();
        for (int i = 0; i < 112; i++) {
            ItemStack itemStack = Variables.get(player.getUniqueId(), "Pets", i);
            if (itemStack == null) {
                break;
            }
            this.pets.add(Pet.getPet(itemStack, false));
        }
        Collections.sort(this.pets);
        this.hidePets = Variables.get(player.getUniqueId(), "HidePets", 0, 0) == 1;

        if (this.activePet < 0)
            this.petArmorStand = null;
        else
            this.petArmorStand = new Pet.ArmorStand(player, this.getPetActive(), Pet.spawnPet(player, this.getPetActive()), this.activePet);
    }

    public Pet getPetActive() {
        return this.pets.get(this.activePet);
    }

    public void save() {
        Variables.set(player.getUniqueId(), "ActivePet", 0, this.activePet);
        Variables.delete(player.getUniqueId(), "Pets");
        for (int i = 0; i < this.pets.size(); i++) {
            Variables.set(player.getUniqueId(), "Pets", i, this.pets.get(i).getAsItem());
        }
        Variables.set(player.getUniqueId(), "HidePets", 0, hidePets ? 1 : 0);
    }
}

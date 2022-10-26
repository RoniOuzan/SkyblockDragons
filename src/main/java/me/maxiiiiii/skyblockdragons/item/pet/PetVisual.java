package me.maxiiiiii.skyblockdragons.item.pet;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import static me.maxiiiiii.skyblockdragons.util.Functions.createHologram;
import static me.maxiiiiii.skyblockdragons.util.Functions.randomDouble;

@Getter
public class PetVisual {
    private final ArmorStand armorStand;
    private int slot;
    private final Hologram hologram;

    public PetVisual(PlayerSD player, Item pet, int slot) {
        this.slot = slot;
        this.armorStand = spawnPet(player, pet);
        this.hologram = createHologram(armorStand.getLocation(), getArmorStandName(player, pet) + ChatColor.GREEN + "" + ChatColor.DARK_GREEN + "" + ChatColor.GREEN + "" + ChatColor.DARK_GREEN);
    }

    public void clear() {
        this.remove();
        this.slot = -1;
    }

    public void remove() {
        this.armorStand.remove();
        this.hologram.delete();
    }

    public static String getArmorStandName(PlayerSD player, Item pet) {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + pet.getModifiers().getPet().getLevel() + ChatColor.DARK_GRAY + "] " + pet.getRarity().getColor() + player.getName() + "'s " + pet.getMaterial().getName();
    }

    public static ArmorStand spawnPet(PlayerSD player, Item pet) {
        org.bukkit.entity.ArmorStand stand = (org.bukkit.entity.ArmorStand) player.getWorld().spawnEntity(player.getLocation().clone().add(randomDouble(-100, 100) / 100, 1, randomDouble(-100, 100) / 100), EntityType.ARMOR_STAND);
        stand.setSmall(true);
        stand.setHelmet(pet);
        stand.setMarker(true);
        stand.setGravity(false);
        stand.setInvulnerable(true);
        stand.setVisible(false);
        stand.setCustomName(player.getName());
        // ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + pet.level + ChatColor.DARK_GRAY + "] " + pet.rarity.getColor() + player.getName() + "'s " + pet.getPetMaterial().getName()
        stand.addScoreboardTag("Pet");

        return stand;
    }
}

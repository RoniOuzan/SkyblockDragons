package me.maxiiiiii.skyblockdragons.pet;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import me.maxiiiiii.skyblockdragons.itemcreator.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static me.maxiiiiii.skyblockdragons.Functions.*;

@Getter
@Setter
public class Pet {
    private PetMaterial petMaterial;
    private Rarity rarity;
    private int level;
    private double currentXp;
    private ArmorStand armorStand;

    public Pet(PetMaterial petMaterial, Rarity rarity, int level, double currentXp) {
        this.petMaterial = petMaterial;
        this.rarity = rarity;
        this.level = level;
        this.currentXp = currentXp;
        armorStand = null;
    }

    public ItemStack toItem(boolean rightClickToAdd) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        ArrayList<String> lores = new ArrayList<>();
        int[] stats = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        lores.add(ChatColor.DARK_GRAY + setTitleCase(this.petMaterial.getSkill().name()) + " Pet");
        lores.add("");

        for (int i = 0; i < 10; i++) {
            String statSymbol = "+";

            String percent = "";
            if (i == 2 || i == 3 || i == 4) percent = "%";

            int stat = this.petMaterial.getStats().get(i).intValue();
            stats[i] = stat;
            if (this.petMaterial.getStats().get(i) != 0d) {
                if (this.petMaterial.getStats().get(i) < 0) statSymbol = "-";
                lores.add(ChatColor.GRAY + Item.getStat(i).toString() + ": " + ChatColor.GREEN + statSymbol + Math.abs(stat) + percent);
            }
        }

        for (PetAbility ability : this.petMaterial.getAbilities()) {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GOLD + ability.getName());
            lores.addAll(Functions.loreBuilder(ability.getDescription()));
            if (!ability.getOther().isEmpty()) {
                lores.add(ChatColor.DARK_GRAY + ability.getOther());
            }
        }

        if (rightClickToAdd) {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.YELLOW + "Right-click to add this pet to");
            lores.add(ChatColor.YELLOW + "your pet menu!");
        }

        item = Functions.getSkull(item, this.petMaterial.getId(), this.petMaterial.getNbt());

        NBTItem nbtItem = new NBTItem(item);
        NBTCompound nbt = nbtItem.addCompound("Item");
        nbt.setString("id", this.petMaterial.name() + "_PET");
        nbt.setIntArray("Stats", stats);
        int random = Functions.randomInt(1, 10000);
        nbt.setString("Stack", random + "");
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        nbt.setString("Date", format.format(now));
        nbt.setInteger("Rarity", rarity.getLevel());
        nbt.setInteger("Level", this.level);
        nbt.setDouble("CurrentXp", this.currentXp);

        nbtItem.applyNBT(item);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "[Level " + this.level + "] " + this.rarity.getColor() + this.petMaterial.getName());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (lores.size() > 0) lores.add("");
        lores.add(rarity + " PET");
        meta.setLore(lores);

        item.setItemMeta(meta);

        return item;
    }

    private static boolean isNotLastEmpty(ArrayList<String> lores) {
        try {
            if (lores.get(lores.size() - 1).equals("")) {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
        return true;
    }

    public static Pet getPet(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            String petMaterial = nbt.getString("id");
            petMaterial = petMaterial.replaceAll("_PET", "");
            Rarity rarity = Functions.getRarity(nbt.getInteger("Rarity"));
            int level = nbt.getInteger("Level");
            double currentXp = nbt.getDouble("CurrentXp");
            return new Pet(PetMaterial.valueOf(petMaterial), rarity, level, currentXp);
        } catch (NullPointerException ignored) {}
        return new Pet(PetMaterial.NULL, Rarity.SPECIAL, 1, 0);
    }

    public static ArmorStand spawnPet(Player player, Pet pet) {
        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().clone().add(randomDouble(-100, 100) / 100, 1, randomDouble(-100, 100) / 100), EntityType.ARMOR_STAND);
        stand.setSmall(true);
        stand.setHelmet(pet.toItem(false));
        stand.setMarker(true);
        stand.setGravity(false);
        stand.setInvulnerable(true);
        stand.setVisible(false);
        stand.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + pet.level + ChatColor.DARK_GRAY + "] " + pet.rarity.getColor() + player.getName() + "'s " + pet.getPetMaterial().getName());
        stand.setCustomNameVisible(true);
        stand.addScoreboardTag("Pet");
        pet.armorStand = stand;
        return stand;
    }
}

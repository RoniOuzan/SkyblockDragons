package me.maxiiiiii.skyblockdragons.pet;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import de.tr7zw.changeme.nbtapi.*;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Rarity;
import me.maxiiiiii.skyblockdragons.stat.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
@Setter
public class Pet extends ItemStack {
    public PetMaterial petMaterial;
    public Rarity rarity;
    public int level;
    public double currentXp;

    public static final double[] needXp = {0, 100, 110, 120, 130, 145, 160, 175, 190, 210, 230, 250, 275, 300, 330, 360, 400, 440, 490, 540, 600, 660, 730, 800, 880, 960, 1050, 1150, 1260, 1380, 1510, 1650, 1800, 1960, 2130, 2310, 2500, 2700, 2920, 3160, 3420, 3700, 4000, 4350, 4750, 5200, 5700, 6300, 7000, 7800, 8700, 9700, 10800, 12000, 13300, 14700, 16200, 17800, 19500, 21300, 23200, 25200, 27400, 29800, 32400, 35200, 38200, 38200, 41400, 44800, 48400, 52200, 56200, 60400, 64800, 69400, 74200, 79200, 84700, 90700, 97200, 104200, 111700, 119700, 128200, 137200, 146700, 156700, 167700, 179700, 192700, 206700, 221700, 237700, 254700, 272700, 291700, 311700, 333700, 357700, 383700, 411700, 476700, 516700, 561700, 611700, 666700, 726700, 791700, 861700, 936700, 1016700, 1101700, 1191700, 1286700, 1386700, 1496700, 1616700, 1746700, 1886700};

    public Pet(PetMaterial petMaterial, Rarity rarity, int level, double currentXp, boolean rightClickToAdd) {
        super(Material.SKULL_ITEM, 1, (short) 3);
        this.petMaterial = petMaterial;
        this.rarity = rarity;
        this.level = level;
        this.currentXp = currentXp;

        this.toItem(rightClickToAdd);
    }

    public String getName() {
        return this.rarity.getColor() + this.petMaterial.getName();
    }

    public void update() {
        this.toItem(false);
    }

    public ItemStack getAsItem() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ArrayList<String> lores = new ArrayList<>();
        double[] stats = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        lores.add(ChatColor.DARK_GRAY + setTitleCase(this.petMaterial.getSkill().name()) + " Pet");
        lores.add("");

        for (int i = 0; i < 10; i++) {
            String statSymbol = "+";

            String percent = "";
            if (i == 2 || i == 3 || i == 4) percent = "%";

            double stat = this.petMaterial.getStats().get(i) * this.level;
            stats[i] = stat;
            if (this.petMaterial.getStats().get(i) != 0d) {
                if (this.petMaterial.getStats().get(i) < 0)
                    statSymbol = "-";

                lores.add(ChatColor.GRAY + Item.getStat(i).toString() + " " + ChatColor.GREEN + statSymbol + Math.abs(stat) + percent);
            }
        }

        for (PetAbility ability : this.petMaterial.getAbilities()) {
            if (!ability.getRarities().contains(this.rarity)) continue;
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GOLD + ability.getName());
            lores.addAll(Functions.loreBuilder(PetMaterial.stringWithMath(ability.getDescription(), this.level)));
            if (!ability.getOther().isEmpty()) {
                lores.add(ChatColor.DARK_GRAY + ability.getOther());
            }
        }

//        if (rightClickToAdd) {
//            if (isNotLastEmpty(lores)) lores.add("");
//
//            lores.add(ChatColor.YELLOW + "Right-click to add this pet to");
//            lores.add(ChatColor.YELLOW + "your pet menu!");
//        }

        if (this.level < this.petMaterial.getMaxLevel()) {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GRAY + "Progress to Level " + (this.level + 1) + ": " + ChatColor.YELLOW + Math.round((this.currentXp / this.getNeedXp() * 100) * 100d) / 100d + "%");
            lores.add(Functions.progressBar(this.currentXp, this.getNeedXp(), 20) + " " + ChatColor.YELLOW + Functions.getNumberFormat(this.currentXp) + ChatColor.GOLD + "/" + ChatColor.YELLOW + Functions.getShortNumber(this.getNeedXp()));
        } else {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GRAY + "Pet XP: " + ChatColor.GREEN + Functions.getNumberFormat(this.currentXp));
        }

        NBTItem nbtItem = new NBTItem(item, true);

        NBTCompound skull = nbtItem.addCompound("SkullOwner");
        skull.setString("Id", this.petMaterial.getId());
        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Value", this.petMaterial.getNbt());

        NBTCompound nbt = nbtItem.addCompound("Item");
        nbt.setString("id", this.petMaterial.name() + "_PET");
        NBTList<Double> statList = nbt.getDoubleList("Stats");
        for (double stat : stats) {
            statList.add(stat);
        }
        int random = Functions.randomInt(1, 10000);
        nbt.setString("Stack", random + "");
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        nbt.setString("Date", format.format(now));
        nbt.setInteger("Rarity", rarity.getLevel());
        nbt.setInteger("Level", this.level);
        nbt.setDouble("CurrentXp", this.currentXp);

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

    private void toItem(boolean rightClickToAdd) {
        ArrayList<String> lores = new ArrayList<>();
        double[] stats = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        lores.add(ChatColor.DARK_GRAY + setTitleCase(this.petMaterial.getSkill().name()) + " Pet");
        lores.add("");

        for (int i = 0; i < 10; i++) {
            String statSymbol = "+";

            String percent = "";
            if (i == 2 || i == 3 || i == 4) percent = "%";

            double stat = this.petMaterial.getStats().get(i) * this.level;
            stats[i] = stat;
            if (this.petMaterial.getStats().get(i) != 0d) {
                if (this.petMaterial.getStats().get(i) < 0)
                    statSymbol = "-";

                lores.add(ChatColor.GRAY + Item.getStat(i).toString() + " " + ChatColor.GREEN + statSymbol + Functions.getInt(Math.abs(Math.floor(stat * 100d) / 100d) + "") + percent);
            }
        }

        for (PetAbility ability : this.petMaterial.getAbilities()) {
            if (!ability.getRarities().contains(this.rarity)) continue;
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GOLD + ability.getName());
            lores.addAll(Functions.loreBuilder(PetMaterial.stringWithMath(ability.getDescription(), this.level)));
            if (!ability.getOther().isEmpty()) {
                lores.add(ChatColor.DARK_GRAY + ability.getOther());
            }
        }

        if (rightClickToAdd) {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.YELLOW + "Right-click to add this pet to");
            lores.add(ChatColor.YELLOW + "your pet menu!");
        }

        if (this.level < this.petMaterial.getMaxLevel()) {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GRAY + "Progress to Level " + (this.level + 1) + ": " + ChatColor.YELLOW + Math.round((this.currentXp / this.getNeedXp() * 100) * 100d) / 100d + "%");
            lores.add(Functions.progressBar(this.currentXp, this.getNeedXp(), 20) + " " + ChatColor.YELLOW + Functions.getNumberFormat(this.currentXp) + ChatColor.GOLD + "/" + ChatColor.YELLOW + Functions.getShortNumber(this.getNeedXp()));
        } else {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GRAY + "Pet XP: " + ChatColor.GREEN + Functions.getNumberFormat(this.currentXp));
        }

        NBTItem nbtItem = new NBTItem(this, true);

        NBTCompound skull = nbtItem.addCompound("SkullOwner");
        skull.setString("Id", this.petMaterial.getId());
        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Value", this.petMaterial.getNbt());

        NBTCompound nbt = nbtItem.addCompound("Item");
        nbt.setString("id", this.petMaterial.name() + "_PET");
//        nbt.setIntArray("Stats", stats);
        NBTList<Double> statList = nbt.getDoubleList("Stats");
        for (double stat : stats) {
            statList.add(stat);
        }
        int random = Functions.randomInt(1, 10000);
        nbt.setString("Stack", random + "");
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        nbt.setString("Date", format.format(now));
        nbt.setInteger("Rarity", rarity.getLevel());
        nbt.setInteger("Level", this.level);
        nbt.setDouble("CurrentXp", this.currentXp);

        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "[Level " + this.level + "] " + this.rarity.getColor() + this.petMaterial.getName());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (lores.size() > 0) lores.add("");
        lores.add(rarity + " PET");
        meta.setLore(lores);

        this.setItemMeta(meta);
    }

    public static ItemStack getBetterPet(ItemStack pet) {
        ArrayList<String> lores = (ArrayList<String>) pet.getItemMeta().getLore();
        if (lores.get(lores.size() - 1).contains("Click to ")) {
            lores.remove(lores.size() - 1);
            lores.remove(lores.size() - 1);
        }
        setLore(pet, lores);
        return pet;
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

    public static Pet getPet(ItemStack item, boolean rightClickToAdd) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            String petMaterial = nbt.getString("id");
            petMaterial = petMaterial.replaceAll("_PET", "");
            Rarity rarity = Functions.getRarity(nbt.getInteger("Rarity"));
            int level = nbt.getInteger("Level");
            double currentXp = nbt.getDouble("CurrentXp");
            return new Pet(PetMaterial.Pets.get(petMaterial), rarity, level, currentXp, rightClickToAdd);
        } catch (NullPointerException ignored) {}
        return new Pet(PetMaterial.NULL, Rarity.SPECIAL, 1, 0, rightClickToAdd);
    }

    public static org.bukkit.entity.ArmorStand spawnPet(PlayerSD player, Pet pet) {
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

    public double getNeedXp() {
        int index = this.level + this.rarityToNeedXp();
        if (this.level >= this.petMaterial.getMaxLevel())
            return 0;
        return needXp[index];
    }

    private int rarityToNeedXp() {
        switch (this.rarity) {
            case COMMON:
                return 0;
            case UNCOMMON:
                return 6;
            case RARE:
                return 11;
            case EPIC:
                return 16;
            case LEGENDARY:
            case MYTHIC:
            case DIVINE:
            case SPECIAL:
                return 20;
        }
        return 0;
    }

    public boolean levelUp(PlayerSD player) {
        boolean levelledUp = this.currentXp >= this.getNeedXp() && this.level < this.petMaterial.getMaxLevel();
        while (this.currentXp >= this.getNeedXp() && this.level < this.petMaterial.getMaxLevel()) {
            this.currentXp -= this.getNeedXp();
            this.level++;
            this.update();

            player.petArmorStand.hologram.removeLine(0);
            player.petArmorStand.hologram.appendTextLine(ArmorStand.getArmorStandName(player, player.getPetActive()));

            player.sendMessage(ChatColor.GREEN + "Your " + this.rarity.getColor() + this.petMaterial.getName() + ChatColor.GREEN + " levelled up to level " + ChatColor.BLUE + this.level + ChatColor.GREEN + "!");
        }
        return levelledUp;
    }

    public static class ArmorStand {
        public org.bukkit.entity.ArmorStand armorStand;
        public int slot;
        public Hologram hologram;

        public ArmorStand(Player player, Pet pet, org.bukkit.entity.ArmorStand armorStand, int slot) {
            this.armorStand = armorStand;
            this.slot = slot;

            hologram = createHologram(armorStand.getLocation(), getArmorStandName(player, pet) + ChatColor.GREEN + "" + ChatColor.DARK_GREEN + "" + ChatColor.GREEN + "" + ChatColor.DARK_GREEN);
        }

        public static String getArmorStandName(Player player, Pet pet) {
            return ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + pet.level + ChatColor.DARK_GRAY + "] " + pet.rarity.getColor() + player.getName() + "'s " + pet.getPetMaterial().getName();
        }
    }
}

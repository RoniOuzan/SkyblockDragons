package me.maxiiiiii.skyblockdragons.player.wardrobe;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static me.maxiiiiii.skyblockdragons.util.Functions.createItem;

@Getter
@Setter
@SerializableAs("WardrobeSlot")
public class WardrobeSlot implements ConfigurationSerializable {
    private int slot;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    public WardrobeSlot(int slot, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        slot = slot % 18;
        this.slot = slot;
        if (helmet == null) helmet = createItem(Material.STAINED_GLASS_PANE, 1, numberToWardrobeColor(slot), ChatColor.GREEN + "Slot " + (slot + 1) + " Helmet");
        this.helmet = helmet;
        if (chestplate == null) chestplate = createItem(Material.STAINED_GLASS_PANE, 1, numberToWardrobeColor(slot), ChatColor.GREEN + "Slot " + (slot + 1) + " Chestplate");
        this.chestplate = chestplate;
        if (leggings == null) leggings = createItem(Material.STAINED_GLASS_PANE, 1, numberToWardrobeColor(slot), ChatColor.GREEN + "Slot " + (slot + 1) + " Leggings");
        this.leggings = leggings;
        if (boots == null) boots = createItem(Material.STAINED_GLASS_PANE, 1, numberToWardrobeColor(slot), ChatColor.GREEN + "Slot " + (slot + 1) + " Boots");
        this.boots = boots;
    }

    public ItemStack getPeace(int peace) {
        if (peace == 0) return this.helmet;
        if (peace == 1) return this.chestplate;
        if (peace == 2) return this.leggings;
        if (peace == 3) return this.boots;
        return helmet;
    }

    public void setPeace(ItemStack item, int peace) {
        if (peace == 0) if (item == null) this.helmet = createItem(Material.STAINED_GLASS_PANE, 1, numberToWardrobeColor(this.slot), ChatColor.GREEN + "Slot " + (this.slot + 1) + " Helmet", ChatColor.GRAY + "Place a helmet here to add", ChatColor.GRAY + "it to the armor set."); else this.helmet = item;
        if (peace == 1) if (item == null) this.chestplate = createItem(Material.STAINED_GLASS_PANE, 1, numberToWardrobeColor(this.slot), ChatColor.GREEN + "Slot " + (this.slot + 1) + " Chestplate", ChatColor.GRAY + "Place a chestplate here to add", ChatColor.GRAY + "it to the armor set."); else this.chestplate = item;
        if (peace == 2) if (item == null) this.leggings = createItem(Material.STAINED_GLASS_PANE, 1, numberToWardrobeColor(this.slot), ChatColor.GREEN + "Slot " + (this.slot + 1) + " Leggings", ChatColor.GRAY + "Place a leggings here to add", ChatColor.GRAY + "it to the armor set."); else this.leggings = item;
        if (peace == 3) if (item == null) this.boots = createItem(Material.STAINED_GLASS_PANE, 1, numberToWardrobeColor(this.slot), ChatColor.GREEN + "Slot " + (this.slot + 1) + " Boots", ChatColor.GRAY + "Place a boots here to add", ChatColor.GRAY + "it to the armor set."); else this.boots = item;
    }

    public static int numberToWardrobeColor(int slot) {
        slot = slot % 9;

        if (slot == 0) return 14;
        if (slot == 1) return 1;
        if (slot == 2) return 4;
        if (slot == 3) return 5;
        if (slot == 4) return 13;
        if (slot == 5) return 9;
        if (slot == 6) return 11;
        if (slot == 7) return 10;
        if (slot == 8) return 6;
        return 15;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> args = new HashMap<>();
        args.put("slot", slot);
        if (helmet != null)
            args.put("helmet", helmet);
        if (chestplate != null)
            args.put("chestplate", chestplate);
        if (leggings != null)
            args.put("leggings", leggings);
        if (boots != null)
            args.put("boots", boots);
        return args;
    }

    public static WardrobeSlot deserialize(Map<String, Object> args) {
        return new WardrobeSlot((int) args.get("slot"), (ItemStack) args.get("helmet"), (ItemStack) args.get("chestplate"), (ItemStack) args.get("leggings"), (ItemStack) args.get("boots"));
    }
}

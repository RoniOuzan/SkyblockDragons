package me.maxiiiiii.skyblockdragons.entity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import me.maxiiiiii.skyblockdragons.itemcreator.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.stat.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class RareDrop extends ItemDrop {
    @Getter
    public enum Rarity {
        RARE(ChatColor.BLUE), EXTRAORDINARY(ChatColor.DARK_PURPLE), PRAY_RNGESUS(ChatColor.LIGHT_PURPLE), RNGESUS_INCARNATE(ChatColor.RED);

        public ChatColor color;

        Rarity(ChatColor color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return this.name().replaceAll("_", " ").replaceAll("Rngesus", "RNGesus");
        }
    }

    public Rarity rarity;

    public RareDrop(ItemMaterial material, int minAmount, int maxAmount, double chance, Rarity rarity) {
        super(material, minAmount, maxAmount, chance);
        this.rarity = rarity;
    }

    public RareDrop(ItemMaterial material, int minAmount, int maxAmount, Rarity rarity) {
        super(material, minAmount, maxAmount);
        this.rarity = rarity;
    }

    public RareDrop(ItemMaterial material, int amount, double chance, Rarity rarity) {
        super(material, amount, chance);
        this.rarity = rarity;
    }

    public RareDrop(ItemMaterial material, int amount, Rarity rarity) {
        super(material, amount);
        this.rarity = rarity;
    }

    public ItemStack generate(PlayerSD player) {
        ItemStack item = super.generate();
        if (item != null) {
            player.sendMessage(rarity.getColor() + rarity.toString() + " DROP! " + ChatColor.GRAY + "(" + ChatColor.RESET + item.getItemMeta().getDisplayName() + ChatColor.GRAY + ")");
        }
        return item;
    }
}

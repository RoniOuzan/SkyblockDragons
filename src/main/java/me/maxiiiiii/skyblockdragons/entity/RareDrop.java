package me.maxiiiiii.skyblockdragons.entity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class RareDrop extends ItemDrop {
    @Getter
    public enum Rarity {
        RARE(ChatColor.BLUE),
        VERY_RARE(ChatColor.DARK_PURPLE),
        CRAZY_RARE(ChatColor.LIGHT_PURPLE),
        INSANE_DROP(ChatColor.RED);

        public ChatColor color;

        Rarity(ChatColor color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return this.name().replaceAll("_", " ").replace("Rngesus", "RNGesus");
        }
    }

    public Rarity rarity;

    public RareDrop(ItemMaterial material, int minAmount, int maxAmount, double chance, Rarity rarity) {
        super(material, minAmount, maxAmount, chance);
        this.rarity = rarity;
    }

    public RareDrop(ItemMaterial material, int minAmount, int maxAmount, double chance) {
        this(material, minAmount, maxAmount, chance, getRarity(chance));
    }

    public RareDrop(ItemMaterial material, int amount, double chance) {
        this(material, amount, amount, chance, getRarity(chance));
    }

    private static Rarity getRarity(double chance) {
        if (chance <= 0.01)
            return Rarity.INSANE_DROP;
        else if (chance <= 0.2)
            return Rarity.CRAZY_RARE;
        else if (chance <= 1)
            return Rarity.VERY_RARE;
        return Rarity.RARE;
    }

    @Override
    public ItemStack generate(PlayerSD player) {
        ItemStack item = super.generate(player);
        if (item != null) {
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            player.sendMessage(rarity.getColor() + "" + ChatColor.BOLD + rarity.toString() + " DROP! " + ChatColor.GRAY + "(" + ChatColor.RESET + item.getItemMeta().getDisplayName() + ChatColor.GRAY + ")");
        }
        return item;
    }
}

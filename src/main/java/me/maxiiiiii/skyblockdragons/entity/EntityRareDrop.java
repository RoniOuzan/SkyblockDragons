package me.maxiiiiii.skyblockdragons.entity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class EntityRareDrop extends EntityDrop {
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

    public EntityRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chance, Rarity rarity) {
        super(material, minAmount, maxAmount, chance);
        this.rarity = rarity;
    }

    public EntityRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chance) {
        this(material, minAmount, maxAmount, chance, getRarity(chance));
    }

    public EntityRareDrop(ItemMaterial material, int amount, double chance) {
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
        double chance = this.chance * player.getEnchantLevel(EnchantType.LUCK) * 0.05;
        if (Functions.randomDouble(0, 100) > chance)
            return null;
        int amount = Functions.randomInt(this.minAmount, this.maxAmount);
        ItemStack item = new Item(this.getMaterial(), amount);

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
        player.sendMessage(rarity.getColor() + "" + ChatColor.BOLD + rarity.toString() + " DROP! " + ChatColor.GRAY + "(" + ChatColor.RESET + item.getItemMeta().getDisplayName() + ChatColor.GRAY + ")");

        return item;
    }
}

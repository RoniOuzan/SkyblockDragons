package me.maxiiiiii.skyblockdragons.item.objects;

import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class RareDrop extends Drop {
    private final DropRarity rarity;

    public RareDrop(ItemMaterial material, int minAmount, int maxAmount, double chance, DropRarity rarity) {
        super(material, minAmount, maxAmount, chance);
        this.rarity = rarity;
    }

    public RareDrop(ItemMaterial material, int minAmount, int maxAmount, double chance) {
        this(material, minAmount, maxAmount, chance, getDropRarity(chance));
    }

    public RareDrop(ItemMaterial material, int minAmount, int maxAmount, DropRarity rarity) {
        this(material, minAmount, maxAmount, 100, rarity);
    }

    public RareDrop(ItemMaterial material, int amount, double chance, DropRarity rarity) {
        this(material, amount, amount, chance, rarity);
    }

    public RareDrop(ItemMaterial material, int amount, double chance) {
        this(material, amount, amount, chance, getDropRarity(chance));
    }

    protected static DropRarity getDropRarity(double chance) {
        if (chance <= 0.01)
            return DropRarity.INSANE_DROP;
        else if (chance <= 0.2)
            return DropRarity.CRAZY_RARE;
        else if (chance <= 1)
            return DropRarity.VERY_RARE;
        return DropRarity.RARE;
    }

    @Override
    public ItemStack generate(PlayerSD player, Object source) {
        double chanceMultiplier = 1;
        chanceMultiplier += player.getEnchantLevel(EnchantType.LUCK) * 0.05;
        double[] multipliers = this.calculateMultiplayers(player, source);

        ItemStack item = this.calculateChances(this.chance, multipliers[0] + chanceMultiplier, (int) multipliers[1]);
        if (item == null)
            return null;

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
        player.sendMessage(rarity.getColor() + "" + ChatColor.BOLD + rarity + " DROP! " + ChatColor.GRAY + "(" + ChatColor.RESET + item.getItemMeta().getDisplayName() + ChatColor.GRAY + ")");

        return item;
    }
}

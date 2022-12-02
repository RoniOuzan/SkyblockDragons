package me.maxiiiiii.skyblockdragons.item.drops.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.drops.DropRarity;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;

@Getter
public abstract class ItemRareDrop extends ItemDrop {
    private final DropRarity rarity;

    public ItemRareDrop(ItemMaterial material, int amount, double chances, DropRarity rarity) {
        super(material, amount, chances);
        this.rarity = rarity == DropRarity.AUTO ? DropRarity.getRarity(chances) : rarity;
    }

    public ItemRareDrop(ItemMaterial material, int amount, double chances) {
        this(material, amount, chances, DropRarity.AUTO);
    }

    public ItemRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chances, DropRarity rarity) {
        this(material, Functions.randomInt(minAmount, maxAmount), chances, rarity);
    }

    public ItemRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chances) {
        this(material, Functions.randomInt(minAmount, maxAmount), chances, DropRarity.AUTO);
    }

    public ItemRareDrop(ItemMaterial material, DropRarity rarity) {
        this(material, 1, 1, rarity);
    }

    @Override
    public void give(PlayerSD player) {
        super.give(player);
        this.send(player);
    }

    @Override
    public void dropItem(PlayerSD player, Location location) {
        super.dropItem(player, location);
        this.send(player);
    }

    private void send(PlayerSD player) {
        player.sendMessage(this.rarity.toString() + " " + ChatColor.GRAY + "(" + ChatColor.RESET + material.getName() + ChatColor.GRAY + ")");
    }
}

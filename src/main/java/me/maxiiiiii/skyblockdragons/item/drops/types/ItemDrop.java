package me.maxiiiiii.skyblockdragons.item.drops.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Location;

@Getter
public abstract class ItemDrop {
    protected final ItemMaterial material;
    protected final int amount;
    protected final double chances;

    public ItemDrop(ItemMaterial material, int amount, double chances) {
        this.material = material;
        this.amount = amount;
        this.chances = chances;
    }

    public ItemDrop(ItemMaterial material, int minAmount, int maxAmount, double chances) {
        this(material, Functions.randomInt(minAmount, maxAmount), chances);
    }

    public ItemDrop(ItemMaterial material, int amount) {
        this(material, amount, 100);
    }

    public ItemDrop(ItemMaterial material) {
        this(material, 1, 100);
    }

    public double getChances() {
        return this.chances;
    }

    public int getAmount() {
        return this.amount;
    }

    public void give(PlayerSD player) {
        for (int i = 0; i < this.amount; i++) {
            player.give(new Item(player, this.material));
        }
    }

    public void dropItem(PlayerSD player, Location location) {
        for (int i = 0; i < this.amount; i++) {
            org.bukkit.entity.Item droppedItem = player.getWorld().dropItem(location, new Item(player, this.material));
            droppedItem.addScoreboardTag(player.getName());
        }
    }
}

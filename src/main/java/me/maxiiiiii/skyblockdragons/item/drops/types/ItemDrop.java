package me.maxiiiiii.skyblockdragons.item.drops.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.PetModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.pet.PetSupplier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class ItemDrop {
    protected final ItemMaterial material;
    protected int amount;
    protected final double chances;

    protected List<Entry<Rarity, Double>> petRarityChances = new ArrayList<>();
    protected PetSupplier petSupplier = new PetSupplier();

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

    public ItemDrop setPetRarityChances(double common, double uncommon, double rare, double epic, double legendary) {
        if (legendary > 0) petRarityChances.add(new Entry<>(Rarity.LEGENDARY, legendary));
        if (epic > 0) petRarityChances.add(new Entry<>(Rarity.EPIC, epic));
        if (rare > 0) petRarityChances.add(new Entry<>(Rarity.RARE, rare));
        if (uncommon > 0) petRarityChances.add(new Entry<>(Rarity.UNCOMMON, uncommon));
        if (common > 0) petRarityChances.add(new Entry<>(Rarity.COMMON, common));
        return this;
    }

    public ItemDrop setPetSupplier(PetSupplier petSupplier) {
        this.petSupplier = petSupplier;
        return this;
    }

    public double getChances() {
        return this.chances;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void give(PlayerSD player) {
        for (int i = 0; i < this.amount; i++) {
            player.give(getItem(player));
        }
    }

    public void dropItem(PlayerSD player, Location location) {
        for (int i = 0; i < this.amount; i++) {
            org.bukkit.entity.Item droppedItem = player.getWorld().dropItem(location, getItem(player));
            droppedItem.addScoreboardTag(player.getName());
        }
    }

    protected Item getItem(PlayerSD player) {
        if (this.petRarityChances.size() > 0) {
            List<Entry<Rarity, Double>> chances = new ArrayList<>();

            double lastChance = 0;
            for (Entry<Rarity, Double> chance : this.petRarityChances) {
                double b = chance.getB() * (1 + (player.getStats().getPetLuck().get() / 100));
                if (b + lastChance > 100) {
                    chances.add(new Entry<>(chance.getA(), 100d));
                    break;
                }

                lastChance += b;
                chances.add(new Entry<>(chance.getA(), lastChance));
            }

            double random = Math.random();
            Rarity rarity = chances.stream().filter(e -> random <= e.getB()).map(Entry::getA).findFirst().orElse(this.petRarityChances.get(0).getA());
            PetSupplier supplier = this.getPetSupplier();
            supplier.setRarity(rarity);

            return new Item(player, this.material, new PetModifier(supplier));
        }

        return new Item(player, this.material);
    }
}

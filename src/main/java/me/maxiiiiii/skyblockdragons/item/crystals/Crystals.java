package me.maxiiiiii.skyblockdragons.item.crystals;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Crystals {
    private final List<Crystal> crystals;

    public Crystals(List<Crystal> crystals) {
        this.crystals = crystals;
    }

    public Crystals(Crystal... crystals) {
        this(Arrays.asList(crystals));
    }

    public Crystals() {
        this(new ArrayList<>());
    }

    public Crystal get(int slot) {
        return this.crystals.get(slot);
    }

    public void add(Crystal crystal) {
        this.crystals.add(crystal);
    }

    public void add(CrystalType crystal, int level) {
        this.add(new Crystal(crystal, level));
    }

    public void remove(int slot) {
        this.crystals.remove(slot);
    }

    public int size() {
        return this.crystals.size();
    }

    public List<ItemStack> getCrystalsAsItems(PlayerSD player) {
        return crystals.stream().map(c -> new Item(player, c.getCrystal().getMaterial(c.getLevel()))).collect(Collectors.toList());
    }
}

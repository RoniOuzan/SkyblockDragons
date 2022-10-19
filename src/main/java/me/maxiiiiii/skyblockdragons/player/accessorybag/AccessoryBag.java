package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class AccessoryBag implements Iterable<Item> {
    private final PlayerSD player;
    private List<Item> items;

    public AccessoryBag(PlayerSD player) {
        this.player = player;
        this.items = new ArrayList<>();

        if (player == null) return;

        for (int i = 0; i < 45; i++) {
            ItemStack itemStack = Variables.get(player.getUniqueId(), "AccessoryBag", i);
            if (itemStack == null) break;
            this.items.add(new Item(player, itemStack));
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void save() {
        Variables.delete(player.getUniqueId(), "AccessoryBag");
        for (int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            if (Functions.getItemMaterial(item) instanceof AccessoryMaterial) {
                Variables.set(player.getUniqueId(), "AccessoryBag", i, item);
            }
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return this.items.iterator();
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        Objects.requireNonNull(action);
        for (Item e : this.items) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<Item> spliterator() {
        return Spliterators.spliterator(this.items, Spliterator.ORDERED);
    }

    public Stream<Item> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}

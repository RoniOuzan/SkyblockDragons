package me.maxiiiiii.skyblockdragons.entity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.EntityEquipment;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Getter
public class Equipment implements Iterable<Item> {
    private final EntitySD entitySD;
    private ItemMaterial toolMaterial;
    private ArmorMaterial helmetMaterial;
    private ArmorMaterial chestplateMaterial;
    private ArmorMaterial leggingsMaterial;
    private ArmorMaterial bootsMaterial;

    private Item tool;
    private Item helmet;
    private Item chestplate;
    private Item leggings;
    private Item boots;

    public Equipment(EntitySD entitySD) {
        this.entitySD = entitySD;
    }

    public void save() {
    }

    public void update() {
        EntityEquipment equipment = entitySD.getEquipment();
        this.toolMaterial = Items.get(equipment.getItemInMainHand());
        this.helmetMaterial = Items.getArmor(equipment.getHelmet());
        this.chestplateMaterial = Items.getArmor(equipment.getChestplate());
        this.leggingsMaterial = Items.getArmor(equipment.getLeggings());
        this.bootsMaterial = Items.getArmor(equipment.getBoots());

        this.tool = new Item((entitySD instanceof PlayerSD) ? (PlayerSD) entitySD : null,
                Functions.isNotAir(equipment.getItemInMainHand()) ? equipment.getItemInMainHand() : new Item(Items.NULL));
        this.helmet = new Item((entitySD instanceof PlayerSD) ? (PlayerSD) entitySD : null,
                Functions.isNotAir(equipment.getHelmet()) ? equipment.getHelmet() : new Item(Items.NULL));
        this.chestplate = new Item((entitySD instanceof PlayerSD) ? (PlayerSD) entitySD : null,
                Functions.isNotAir(equipment.getChestplate()) ? equipment.getChestplate() : new Item(Items.NULL));
        this.leggings = new Item((entitySD instanceof PlayerSD) ? (PlayerSD) entitySD : null,
                Functions.isNotAir(equipment.getLeggings()) ? equipment.getLeggings() : new Item(Items.NULL));
        this.boots = new Item((entitySD instanceof PlayerSD) ? (PlayerSD) entitySD : null,
                Functions.isNotAir(equipment.getBoots()) ? equipment.getBoots() : new Item(Items.NULL));
    }

    public List<Item> getArmor() {
        return Arrays.asList(helmet, chestplate, leggings, boots);
    }

    public List<Item> toList() {
        return new ArrayList<>(Arrays.asList(tool, helmet, chestplate, leggings, boots));
    }

    @Override
    public Iterator<Item> iterator() {
        return this.toList().iterator();
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        Objects.requireNonNull(action);
        for (Item e : this.toList()) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<Item> spliterator() {
        return Spliterators.spliterator(this.toList(), Spliterator.ORDERED);
    }

    public Stream<Item> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}

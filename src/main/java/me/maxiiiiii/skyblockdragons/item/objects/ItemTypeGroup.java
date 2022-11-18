package me.maxiiiiii.skyblockdragons.item.objects;

import java.util.ArrayList;
import java.util.Arrays;

public enum ItemTypeGroup {
    ARMOR(new ArrayList<>(Arrays.asList(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS))),
    WEAPON(new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.BOW))),
    TOOL(new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ITEM, ItemType.BOW, ItemType.WAND, ItemType.AXE, ItemType.PICKAXE, ItemType.ROD))),
    MINING(new ArrayList<>(Arrays.asList(ItemType.PICKAXE, ItemType.DRILL, ItemType.AXE, ItemType.HOE))),
    FARMING(new ArrayList<>(Arrays.asList(ItemType.AXE, ItemType.HOE))),
    BOWS(new ArrayList<>(Arrays.asList(ItemType.BOW, ItemType.RANGE_WEAPON, ItemType.SHORT_BOW))),
    OTHER(new ArrayList<>(Arrays.asList(ItemType.BOOK, ItemType.REFORGE_STONE, ItemType.SKIN)));

    private final ArrayList<ItemType> types;

    ItemTypeGroup(ArrayList<ItemType> types) {
        this.types = types;
    }

    public ArrayList<ItemType> getTypes() {
        return types;
    }

    public ArrayList<ItemType> toType() {
        return types;
    }
}

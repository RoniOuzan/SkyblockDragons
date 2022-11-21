package me.maxiiiiii.skyblockdragons.item.drops.types.entity;

import me.maxiiiiii.skyblockdragons.item.drops.types.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;

public class EntityItemDrop extends ItemDrop implements EntityDrop {
    public EntityItemDrop(ItemMaterial material, int amount, double chances) {
        super(material, amount, chances);
    }

    public EntityItemDrop(ItemMaterial material, int minAmount, int maxAmount, double chances) {
        super(material, minAmount, maxAmount, chances);
    }

    public EntityItemDrop(ItemMaterial material, int amount) {
        super(material, amount);
    }

    public EntityItemDrop(ItemMaterial material) {
        super(material);
    }
}

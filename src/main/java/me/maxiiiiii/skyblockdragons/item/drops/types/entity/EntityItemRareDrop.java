package me.maxiiiiii.skyblockdragons.item.drops.types.entity;

import me.maxiiiiii.skyblockdragons.item.drops.DropRarity;
import me.maxiiiiii.skyblockdragons.item.drops.types.ItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;

public class EntityItemRareDrop extends ItemRareDrop implements EntityDrop {
    public EntityItemRareDrop(ItemMaterial material, int amount, double chances, DropRarity rarity) {
        super(material, amount, chances, rarity);
    }

    public EntityItemRareDrop(ItemMaterial material, int amount, double chances) {
        super(material, amount, chances);
    }

    public EntityItemRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chances, DropRarity rarity) {
        super(material, minAmount, maxAmount, chances, rarity);
    }

    public EntityItemRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chances) {
        super(material, minAmount, maxAmount, chances);
    }

    public EntityItemRareDrop(ItemMaterial material, DropRarity rarity) {
        super(material, rarity);
    }
}

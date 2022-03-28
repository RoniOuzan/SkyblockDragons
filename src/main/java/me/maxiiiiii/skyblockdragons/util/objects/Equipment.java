package me.maxiiiiii.skyblockdragons.util.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Equipment {
    public ItemStack helmet;
    public ItemStack chestplate;
    public ItemStack leggings;
    public ItemStack boots;
    public ItemStack hand;
    public ItemStack offHand;

    public Equipment(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack hand, ItemStack offHand) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.hand = hand;
        this.offHand = offHand;
    }

    public Equipment(Material helmet, Material chestplate, Material leggings, Material boots, Material hand, Material offHand) {
        this.helmet = helmet == null ? null : new ItemStack(helmet);
        this.chestplate = chestplate == null ? null : new ItemStack(chestplate);
        this.leggings = leggings == null ? null : new ItemStack(leggings);
        this.boots = boots == null ? null : new ItemStack(boots);
        this.hand = hand == null ? null : new ItemStack(hand);
        this.offHand = offHand == null ? null : new ItemStack(offHand);
    }

    public Equipment() {
        this((Material) null, null, null, null, null, null);
    }
}

package me.maxiiiiii.skyblockdragons.util;

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
}

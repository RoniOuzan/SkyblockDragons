package me.maxiiiiii.skyblockdragons.item.modifiers;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemModifier {
    private static final List<Class<? extends ItemModifier>> classes = new ArrayList<>();

    public ItemModifier(Class<? extends ItemModifier> clazz) {
        classes.add(clazz);
    }

    public static List<Class<? extends ItemModifier>> getClasses() {
        return classes;
    }

    public static ItemModifier getModifier(ItemStack item) {
        return null;
    }
}

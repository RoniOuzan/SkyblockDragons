package me.maxiiiiii.skyblockdragons.item.modifiers;

import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public abstract class ItemModifier {
    private static final Set<Class<? extends ItemModifier>> classes = new HashSet<>();

    public ItemModifier(Class<? extends ItemModifier> clazz) {
        classes.add(clazz);
    }

    public static Set<Class<? extends ItemModifier>> getClasses() {
        return classes;
    }

    public static ItemModifier getModifier(ItemStack item) {
        return null;
    }
}

package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NecronBladeScrollsModifier extends ItemModifier {
    private final List<NecronBladeMaterial.NecronBladeAbility> abilities;

    public NecronBladeScrollsModifier(List<NecronBladeMaterial.NecronBladeAbility> abilities) {
        super(NecronBladeScrollsModifier.class);
        this.abilities = abilities;
    }

    public NecronBladeScrollsModifier() {
        this(new ArrayList<>());
    }

    public List<NecronBladeMaterial.NecronBladeAbility> get() {
        return this.abilities;
    }

    public static NecronBladeScrollsModifier getModifier(ItemStack item) {
        return Functions.getNecronScrolls(item);
    }
}

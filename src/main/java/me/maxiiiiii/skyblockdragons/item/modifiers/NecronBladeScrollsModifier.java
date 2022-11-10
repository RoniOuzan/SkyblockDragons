package me.maxiiiiii.skyblockdragons.item.modifiers;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import me.maxiiiiii.skyblockdragons.item.Item;
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

    @Override
    public void applyNBT(Item item, NBTCompound nbt) {
        if (item.getMaterial() instanceof NecronBladeMaterial) {
            NBTCompound necronScrolls = nbt.addCompound("NecronScrolls");
            necronScrolls.setBoolean("IMPLOSION", abilities.contains(NecronBladeMaterial.NecronBladeAbility.IMPLOSION));
            necronScrolls.setBoolean("WITHER_SHIELD", abilities.contains(NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD));
            necronScrolls.setBoolean("SHADOW_WARP", abilities.contains(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP));
        }
    }

    public static NecronBladeScrollsModifier getModifier(ItemStack item) {
        return Functions.getNecronScrolls(item);
    }
}

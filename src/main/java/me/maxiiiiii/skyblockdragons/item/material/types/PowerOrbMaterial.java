package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public abstract class PowerOrbMaterial extends ToolMaterial implements ItemAbilityAble {
    private final String powerOrbName;

    public PowerOrbMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, String powerOrbName, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity, description, modifiers);
        this.powerOrbName = powerOrbName;
    }

    @Override
    public List<ItemAbility> getAbilities() {
        return new ArrayList<>(Collections.singletonList(ability));
    }
}

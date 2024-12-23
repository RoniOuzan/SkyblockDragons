package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

@Getter
public abstract class PowerOrbMaterial extends ToolMaterial implements ItemAbilityAble {

    public PowerOrbMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity, "", modifiers);
    }
}

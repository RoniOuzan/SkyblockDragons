package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemEnchantAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.NullItemAbility;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.Material;

import java.util.List;

@Getter
@Setter
public abstract class ToolMaterial extends ItemMaterial implements ItemDescriptionAble, ItemAbilityAble, ItemEnchantAble, ItemRequirementAble {
    private String description;
    private List<Requirement> requirements;
    private List<ItemAbility> abilities;

    public ToolMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, MaterialModifier... objects) {
        super(itemID, material, family, name, type, rarity);
        this.description = description;
        this.requirements = Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", objects);
        this.abilities = Functions.splitList("me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility", objects);
        if (this.abilities.size() == 0)
            this.abilities.add(new NullItemAbility());
    }
}

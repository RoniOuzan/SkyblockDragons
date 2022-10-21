package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.*;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.NullItemAbility;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

@Getter
@Setter
public abstract class ArmorMaterial extends ItemMaterial implements ItemStatsAble, ItemDescriptionAble, ItemEnchantAble, ItemAbilityAble, ItemRequirementAble {
    private Stats stats;
    private String description;
    private ItemFullSet fullSet;
    private List<ItemAbility> abilities;
    private List<Requirement> requirements;

    public ArmorMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, ItemFullSet fullSet, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity);
        this.stats = stats;
        this.description = description;
        this.fullSet = fullSet == null ? ItemFullSet.NULL : fullSet;
        this.requirements = Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", modifiers);
        this.abilities = Functions.splitList("me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility", modifiers);
        if (this.abilities.size() == 0)
            this.abilities.add(new NullItemAbility());
    }

    public Color getColor() {
        return null;
    }
}

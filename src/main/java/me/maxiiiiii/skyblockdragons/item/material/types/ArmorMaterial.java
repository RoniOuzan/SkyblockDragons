package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.*;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.NullItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public abstract class ArmorMaterial extends ItemMaterial implements ItemStatsAble, ItemDescriptionAble, ItemEnchantAble, ItemAbilityAble, ItemRequirementAble {
    private Stats stats;
    private Function<PlayerSD, String> description;
    private List<ItemAbility> abilities;
    private List<Requirement> requirements;

    public ArmorMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, Function<PlayerSD, String> description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity);
        this.stats = stats;
        this.description = description;
        this.requirements = Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", modifiers);
        this.abilities = Functions.splitList("me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility", modifiers);
        if (this.abilities.size() == 0)
            this.abilities.add(new NullItemAbility());
    }

    public ArmorMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, MaterialModifier... modifiers) {
        this(itemID, material, family, name, type, rarity, stats, p -> description, modifiers);
    }

    public Color getColor() {
        return null;
    }

    public ItemFullSetBonus getFullSet() {
        return (ItemFullSetBonus) this.abilities.stream().filter(a -> a instanceof ItemFullSetBonus).findFirst().orElse(ItemFullSetBonus.NULL);
    }

    @Override
    public String getDescription(PlayerSD player) {
        return description.apply(player);
    }
}

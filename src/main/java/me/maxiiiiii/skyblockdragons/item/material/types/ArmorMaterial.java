package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.*;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirements;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public abstract class ArmorMaterial extends ItemMaterial implements ItemStatsAble, ItemDescriptionAble, ItemEnchantAble, ItemAbilityAble, ItemRequirementAble {
    public static final ArmorMaterial NULL = new NullArmor();

    private Stats stats;
    private Function<PlayerSD, String> description;
    private List<ItemAbility> abilities;
    private Requirements requirements;

    public ArmorMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, Function<PlayerSD, String> description, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity);
        this.stats = stats;
        this.description = description;
        this.requirements = new Requirements(Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", modifiers));
        this.abilities = Functions.splitList("me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility", modifiers);
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

    @MaterialUnregisters
    private static class NullArmor extends ArmorMaterial {
        public NullArmor() {
            super("NULL_ARMOR",
                    Material.BARRIER,
                    ItemFamily.NULL,
                    "Null",
                    ItemType.NULL,
                    Rarity.SPECIAL,
                    new Stats(),
                    ""
            );
        }
    }
}

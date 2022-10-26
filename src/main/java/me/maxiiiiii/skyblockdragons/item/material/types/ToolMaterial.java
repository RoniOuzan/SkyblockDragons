package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemEnchantAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.Material;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public abstract class ToolMaterial extends ItemMaterial implements ItemDescriptionAble, ItemAbilityAble, ItemEnchantAble, ItemRequirementAble {
    private Function<PlayerSD, String> description;
    private List<Requirement> requirements;
    private List<ItemAbility> abilities;

    public ToolMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Function<PlayerSD, String> description, MaterialModifier... objects) {
        super(itemID, material, family, name, type, rarity);
        this.description = description;
        this.requirements = Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", objects);
        this.abilities = Functions.splitList("me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility", objects);
    }

    public ToolMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, MaterialModifier... objects) {
        this(itemID, material, family, name, type, rarity, p -> description, objects);
    }

    public String getDescription(PlayerSD player) {
        if (player == null) return "HAVE TO BE A PLAYER!";

        return description.apply(player);
    }
}

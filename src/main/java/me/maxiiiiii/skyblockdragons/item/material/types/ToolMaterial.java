package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemEnchantAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

import java.util.List;

@Getter
@Setter
public class ToolMaterial extends ItemMaterial implements ItemDescriptionAble, ItemAbilityAble, ItemEnchantAble, ItemRequirementAble {
    public static final ToolMaterial NULL = new ToolMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", "", new ItemAbility(AbilityAction.NONE, "", ""));

    private String description;
    private List<Requirement> requirements;
    private List<ItemAbility> abilities;

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, double sellPrice, String description, Object... objects) {
        super(material, family, name, type, rarity, id, nbt, sellPrice);
        this.description = description;
        this.requirements = Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", objects);
        this.abilities = Functions.splitList("me.maxiiiiii.skyblockdragons.item.objects.ItemAbility", objects);
        if (this.abilities.size() == 0)
            this.abilities.add(new ItemAbility(AbilityAction.NULL, "", ""));
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description, Object... objects) {
        this(material, family, name, type, rarity, id, nbt, 0, description, objects);
    }

    public ToolMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, Object... objects) {
        this(material, family, name, type, rarity, "", "", 0, description, objects);
    }
}

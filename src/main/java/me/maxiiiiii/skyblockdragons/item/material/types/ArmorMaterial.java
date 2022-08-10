package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.*;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

@Getter
@Setter
public class ArmorMaterial extends ItemMaterial implements ItemStatsAble, ItemDescriptionAble, ItemEnchantAble, ItemAbilityAble, ItemRequirementAble {
    public static final ArmorMaterial NULL = new ArmorMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", ItemFullSet.NULL, Color.RED);

    private Stats stats;
    private String description;
    private ItemFullSet fullSet;
    private Color color;
    private List<ItemAbility> abilities;
    private List<Requirement> requirements;

    public ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, String description, ItemFullSet fullSet, Color color, Object... objects) {
        super(material, family, name, type, rarity, id, nbt, 0);
        this.stats = stats;
        this.description = description;
        this.fullSet = fullSet == null ? ItemFullSet.NULL : fullSet;
        this.color = color;
        this.requirements = Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", objects);
        this.abilities = Functions.splitList("me.maxiiiiii.skyblockdragons.item.objects.ItemAbility", objects);
        if (this.abilities.size() == 0)
            this.abilities.add(new ItemAbility(AbilityAction.NULL, "", ""));
    }

    public ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, String description, ItemFullSet fullSet, Object... objects) {
        this(material, family, name, type, rarity, id, nbt, stats, description, fullSet, Color.BLACK, objects);
    }

    public ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, ItemFullSet fullSet, Color color, Object... objects) {
        this(material, family, name, type, rarity, "", "", stats, description, fullSet, color, objects);
    }

    public ArmorMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, ItemFullSet fullSet, Object... objects) {
        this(material, family, name, type, rarity, "", "", stats, description, fullSet, Color.BLACK, objects);
    }
}

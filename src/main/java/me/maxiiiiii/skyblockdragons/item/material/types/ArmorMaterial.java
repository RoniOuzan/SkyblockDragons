package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.*;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

@Getter
@Setter
public abstract class ArmorMaterial extends ItemMaterial implements ItemStatsAble, ItemDescriptionAble, ItemEnchantAble, ItemAbilityAble, ItemRequirementAble {
    public static final ArmorMaterial NULL = new ArmorMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", ItemFullSet.NULL, Color.RED);

    private Stats stats;
    private String description;
    private ItemFullSet fullSet;
    private Color color;
    private List<ItemAbility> abilities;
    private List<Requirement> requirements;

    public ArmorMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, String description, ItemFullSet fullSet, Color color, MaterialModifier... modifiers) {
        super(itemID, material, family, name, type, rarity, id, nbt, 0);
        this.stats = stats;
        this.description = description;
        this.fullSet = fullSet == null ? ItemFullSet.NULL : fullSet;
        this.color = color;
        this.requirements = Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", modifiers);
        this.abilities = Functions.splitList("me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility", modifiers);
        if (this.abilities.size() == 0)
            this.abilities.add(new ItemAbility(AbilityAction.NULL, "", ""));
    }

    public ArmorMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, Stats stats, String description, ItemFullSet fullSet, MaterialModifier... modifiers) {
        this(material, family, name, type, rarity, id, nbt, stats, description, fullSet, Color.BLACK, modifiers);
    }

    public ArmorMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, ItemFullSet fullSet, Color color, MaterialModifier... modifiers) {
        this(material, family, name, type, rarity, "", "", stats, description, fullSet, color, modifiers);
    }

    public ArmorMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Stats stats, String description, ItemFullSet fullSet, MaterialModifier... modifiers) {
        this(material, family, name, type, rarity, "", "", stats, description, fullSet, Color.BLACK, modifiers);
    }
}

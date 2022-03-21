package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemFamily;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemType;
import me.maxiiiiii.skyblockdragons.itemcreator.Rarity;
import org.bukkit.Material;

@Getter
public class NormalMaterial extends ItemMaterial {
    private final String description;
    private final boolean isEnchanted;
    private final boolean showRecipe;
    private final boolean stackAble;

    NormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        super(material, family, name, type, rarity, id, nbt);
        this.description = description;
        this.isEnchanted = isEnchanted;
        this.showRecipe = showRecipe;
        this.stackAble = stackAble;
    }

    NormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, boolean isEnchanted, boolean showRecipe) {
        this(material, family, name, type, rarity, id, nbt, "", isEnchanted, showRecipe, true);
    }

    NormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(material, family, name, type, rarity, id, nbt, "", isEnchanted, showRecipe, stackAble);
    }
}

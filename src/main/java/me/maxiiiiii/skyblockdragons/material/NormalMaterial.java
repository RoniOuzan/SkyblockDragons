package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemType;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Rarity;
import org.bukkit.Material;

@Getter
public class NormalMaterial extends ItemMaterial {
    private final String description;
    private final boolean isEnchanted;
    private final boolean showRecipe;
    private final boolean stackAble;

    public NormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, String description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        super(material, family, name, type, rarity, id, nbt);
        this.description = description;
        this.isEnchanted = isEnchanted;
        this.showRecipe = showRecipe;
        this.stackAble = stackAble;
    }

    public NormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, boolean isEnchanted, boolean showRecipe) {
        this(material, family, name, type, rarity, id, nbt, "", isEnchanted, showRecipe, true);
    }

    public NormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(material, family, name, type, rarity, id, nbt, "", isEnchanted, showRecipe, stackAble);
    }
}

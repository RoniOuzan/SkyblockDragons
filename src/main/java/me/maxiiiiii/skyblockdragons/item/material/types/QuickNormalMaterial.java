package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class QuickNormalMaterial extends NormalMaterial implements QuickMaterial {
    private String id = "";
    private String value = "";

    public QuickNormalMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String value, String description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        super(itemID, material, family, name, type, rarity, description, isEnchanted, showRecipe, stackAble);
        this.id = id;
        this.value = value;
    }

    public QuickNormalMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String value, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(itemID, material, family, name, type, rarity, id, value, "", isEnchanted, showRecipe, stackAble);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String value, String description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(QuickMaterial.itemID, material, family, name, type, rarity, id, value, description, isEnchanted, showRecipe, stackAble);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String value, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(material, family, name, type, rarity, id, value, "", isEnchanted, showRecipe, stackAble);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(material, family, name, type, rarity, "", "", description, isEnchanted, showRecipe, stackAble);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String value, boolean isEnchanted, boolean showRecipe) {
        this(material, family, name, type, rarity, id, value, "", isEnchanted, showRecipe, true);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description) {
        this(material, family, name, type, rarity, "", "", description, false, false, true);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity) {
        this(material, family, name, type, rarity, "", "", "", false, false, true);
    }

    public QuickNormalMaterial setData(int data) {
        this.data = data;
        return this;
    }

    @Override
    public ItemSkull getItemSkull() {
        if (id.isEmpty() && value.isEmpty())
            return super.getItemSkull();

        return new ItemSkull(id, value);
    }
}

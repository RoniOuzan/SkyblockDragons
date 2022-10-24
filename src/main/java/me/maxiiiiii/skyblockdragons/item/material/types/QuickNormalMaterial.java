package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class QuickNormalMaterial extends NormalMaterial implements QuickMaterial {
    private String id = "";
    private String nbt = "";

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, String id, String nbt, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        super(QuickMaterial.itemID, material, family, name, type, rarity, description, isEnchanted, showRecipe, stackAble);
        this.id = id;
        this.nbt = nbt;
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(material, family, name, type, rarity, "", id, nbt, isEnchanted, showRecipe, stackAble);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(material, family, name, type, rarity, description, "", "", isEnchanted, showRecipe, stackAble);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, boolean isEnchanted, boolean showRecipe) {
        this(material, family, name, type, rarity, "", id, nbt, isEnchanted, showRecipe, true);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description) {
        this(material, family, name, type, rarity, description, "", "", false, false, true);
    }

    public QuickNormalMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity) {
        this(material, family, name, type, rarity, "", "", "", false, false, true);
    }

    @Override
    public ItemSkull getItemSkull() {
        if (nbt.isEmpty())
            return null;

        return new ItemSkull(id, nbt);
    }

    @Override
    public void updateStats(PlayerStats stats) {
    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {
    }
}

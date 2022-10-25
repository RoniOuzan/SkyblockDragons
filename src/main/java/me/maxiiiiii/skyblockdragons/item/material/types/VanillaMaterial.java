package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class VanillaMaterial extends NormalMaterial {
    public VanillaMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        super(itemID, material, family, name, type, rarity, description, isEnchanted, showRecipe, stackAble);
    }

    public VanillaMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, boolean isEnchanted, boolean showRecipe) {
        super(itemID, material, family, name, type, rarity, isEnchanted, showRecipe);
    }

    public VanillaMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        super(itemID, material, family, name, type, rarity, isEnchanted, showRecipe, stackAble);
    }

    public VanillaMaterial setData(int data) {
        this.data = data;
        return this;
    }

    @Override
    public void updateStats(PlayerStats stats) {
    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {
    }
}

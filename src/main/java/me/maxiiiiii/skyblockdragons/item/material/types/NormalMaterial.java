package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Material;

import java.util.function.Function;

@Getter
public abstract class NormalMaterial extends ItemMaterial implements ItemDescriptionAble {
    private final Function<PlayerSD, String> description;
    private final boolean isEnchanted;
    private final boolean showRecipe;
    private final boolean stackAble;

    public NormalMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, Function<PlayerSD, String> description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        super(itemID, material, family, name, type, rarity);
        this.description = description;
        this.isEnchanted = isEnchanted;
        this.showRecipe = showRecipe;
        this.stackAble = stackAble;
    }

    public NormalMaterial(String itemID, Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String description, boolean isEnchanted, boolean showRecipe, boolean stackAble) {
        this(itemID, material, family, name, type, rarity, p -> description, isEnchanted, showRecipe, stackAble);
    }

    @Override
    public String getDescription(PlayerSD player) {
        return description.apply(player);
    }
}

package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Material;

import java.util.function.Function;

@Getter
public abstract class AccessoryMaterial extends ItemMaterial implements ItemStatsAble, ItemDescriptionAble {
    private final Stats stats;
    private final Function<PlayerSD, String> description;

    public AccessoryMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, Function<PlayerSD, String> description) {
        super(itemID, material, family, name, ItemType.ACCESSORY, rarity);
        this.stats = stats;
        this.description = description;
    }

    public AccessoryMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description) {
        this(itemID, material, family, name, rarity, stats, p -> description);
    }

    @Override
    public String getDescription(PlayerSD player) {
        return description.apply(player);
    }
}

package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.accessorybag.PowerStone;
import org.bukkit.Material;

@Getter
public abstract class PowerStoneMaterial extends ItemMaterial {
    private final PowerStone powerStone;

    public PowerStoneMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, PowerStone powerStone) {
        super(itemID, material, family, name, ItemType.POWER_STONE, rarity);
        this.powerStone = powerStone;
    }
}

package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

@Getter
@Setter
public abstract class ReforgeMaterial extends ItemMaterial {
    public static final ReforgeMaterial NULL = new ReforgeMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "", "");

    private String reforgeName;

    public ReforgeMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, String reforgeName) {
        super(itemID, material, family, name, ItemType.REFORGE_STONE, rarity, id, nbt, 0);
        this.reforgeName = reforgeName;
    }
}

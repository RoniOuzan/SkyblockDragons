package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

@Getter
@Setter
public class SkinMaterial extends ItemMaterial {
    public static final SkinMaterial NULL = new SkinMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "");

    public SkinMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt) {
        super(material, family, name, ItemType.SKIN, rarity, id, nbt, 0);
    }
}

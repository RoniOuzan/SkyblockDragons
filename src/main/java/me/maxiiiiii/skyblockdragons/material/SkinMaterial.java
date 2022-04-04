package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

@Getter
@Setter
public class SkinMaterial extends ItemMaterial implements ConfigurationSerializable {
    public static final SkinMaterial NULL = new SkinMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "");

    public SkinMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt) {
        super(material, family, name, ItemType.SKIN, rarity, id, nbt, 0);
    }
}

package me.maxiiiiii.skyblockdragons.itemcreator.material;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemType;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.Rarity;
import org.bukkit.Material;

@Getter
@Setter
public class ReforgeMaterial extends ItemMaterial {
    public static final ReforgeMaterial NULL = new ReforgeMaterial(Material.BARRIER, ItemFamily.NULL,"Null", Rarity.SPECIAL, "", "", "");

    private String reforgeName;

    public ReforgeMaterial(Material material, ItemFamily family, String name, Rarity rarity, String id, String nbt, String reforgeName) {
        super(material, family, name, ItemType.REFORGE_STONE, rarity, id, nbt);
        this.reforgeName = reforgeName;
    }
}

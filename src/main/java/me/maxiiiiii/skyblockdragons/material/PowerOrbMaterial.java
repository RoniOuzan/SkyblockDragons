package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemAbility;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemFamily;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemType;
import me.maxiiiiii.skyblockdragons.itemcreator.Rarity;
import org.bukkit.Material;

@Getter
public class PowerOrbMaterial extends ItemMaterial {
    private final ItemAbility ability;
    private final String powerOrbName;
    private final String powerOrbDescription;

    PowerOrbMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ItemAbility ability, String powerOrbDescription) {
        super(material, family, name, type, rarity, id, nbt);
        this.ability = ability;
        this.powerOrbName = name.split(" Power ")[0];
        this.powerOrbDescription = powerOrbDescription;
    }
}

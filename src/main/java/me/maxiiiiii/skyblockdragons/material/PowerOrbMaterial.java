package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

@Getter
public class PowerOrbMaterial extends ItemMaterial {
    private final ItemAbility ability;
    private final String powerOrbName;
    private final String powerOrbDescription;

    public PowerOrbMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ItemAbility ability, String powerOrbDescription) {
        super(material, family, name, type, rarity, id, nbt);
        this.ability = ability;
        this.powerOrbName = name.split(" Power ")[0];
        this.powerOrbDescription = powerOrbDescription;
    }
}

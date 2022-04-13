package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class PowerOrbMaterial extends ItemMaterial implements ItemAbilityAble {
    private final ItemAbility ability;
    private final String powerOrbName;
    private final String powerOrbDescription;

    public PowerOrbMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt, ItemAbility ability, String powerOrbDescription) {
        super(material, family, name, type, rarity, id, nbt, 0);
        this.ability = ability;
        this.powerOrbName = name.split(" Power ")[0];
        this.powerOrbDescription = powerOrbDescription;
    }

    @Override
    public List<ItemAbility> getAbilities() {
        return new ArrayList<>(Collections.singletonList(ability));
    }
}
package me.maxiiiiii.skyblockdragons.item.material.materials.theend.accessories.enderman;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class EndermanTalismanUncommon extends AccessoryMaterial {
    public EndermanTalismanUncommon() {
        super("ENDERMAN_TALISMAN_UNCOMMON",
                Material.SKULL_ITEM,
                ItemFamily.ENDERMAN,
                "Enderman Talisman",
                Rarity.UNCOMMON,
                new Stats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("4cc3ce0e-15ac-4c8c-974d-149b4f0014b4",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM0NGRmY2QzNDM4OTg3MGNjZDRkZjEyYzZjODdjMzI1Nzg3OTdhZmQ0NmU2YzFkZTI4ZDY3YjFkZGY3YjllMSJ9fX0="
        );
    }
}

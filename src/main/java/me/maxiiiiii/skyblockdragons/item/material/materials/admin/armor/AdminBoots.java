package me.maxiiiiii.skyblockdragons.item.material.materials.admin.armor;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.CombatStats;
import org.bukkit.Material;

public class AdminBoots extends ArmorMaterial {
    public AdminBoots() {
        super("ADMIN_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.ADMIN,
                "Admin Boots",
                ItemType.BOOTS,
                Rarity.SPECIAL,
                new CombatStats(10000000, 10000000, 10000000, 10000000, 10000000, 10000000, 10000000, 10000000, 10000000, 10000000, 5000, 1000000, 1000000),
                ""
        );
    }
}

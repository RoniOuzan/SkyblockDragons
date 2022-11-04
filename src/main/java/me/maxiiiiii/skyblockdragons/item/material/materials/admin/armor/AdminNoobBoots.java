package me.maxiiiiii.skyblockdragons.item.material.materials.admin.armor;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class AdminNoobBoots extends ArmorMaterial {
    public AdminNoobBoots() {
        super("ADMIN_NOOB_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.ADMIN,
                "Admin N00B Boots",
                ItemType.BOOTS,
                Rarity.SPECIAL,
                new Stats(1000, 1000, 1000, 1000, 1000, 0, 1000, 1000, 100, 1000),
                ""
        );
    }
}

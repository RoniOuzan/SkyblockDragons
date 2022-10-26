package me.maxiiiiii.skyblockdragons.item.material.materials.admin.armor;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Material;

public class AdminBoots extends ArmorMaterial {
    public AdminBoots() {
        super("ADMIN_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.ADMIN,
                "Admin Boots",
                ItemType.BOOTS,
                Rarity.SPECIAL,
                new Stats(10000000, 10000000, 10000000, 10000000, 10000000, 0, 10000000, 10000000, 5000, 10000000),
                ""
        );
    }
}

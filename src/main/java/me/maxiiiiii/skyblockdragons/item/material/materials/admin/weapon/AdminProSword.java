package me.maxiiiiii.skyblockdragons.item.material.materials.admin.weapon;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.CombatStats;
import org.bukkit.Material;

public class AdminProSword extends SwordMaterial {
    public AdminProSword() {
        super("ADMIN_PRO_SWORD",
                Material.IRON_SWORD,
                ItemFamily.ADMIN,
                "Admin PRO Sword",
                Rarity.SPECIAL,
                new CombatStats(100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 5000, 1000000, 1000000),
                ""
        );
    }
}

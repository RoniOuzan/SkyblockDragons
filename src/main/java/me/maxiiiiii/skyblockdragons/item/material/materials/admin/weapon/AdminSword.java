package me.maxiiiiii.skyblockdragons.item.material.materials.admin.weapon;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.CombatStats;
import org.bukkit.Material;

public class AdminSword extends SwordMaterial {
    public AdminSword() {
        super("ADMIN_SWORD",
                Material.DIAMOND_SWORD,
                ItemFamily.ADMIN,
                "Admin Sword",
                Rarity.SPECIAL,
                new CombatStats(10000000, 10000000, 10000000, 0, 0,10000000 ,0, 10000000, 10000000, 10000000, 10000000, 10000000, 0),
                ""
        );
    }
}

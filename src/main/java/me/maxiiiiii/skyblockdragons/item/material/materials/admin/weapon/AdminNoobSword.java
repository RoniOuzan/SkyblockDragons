package me.maxiiiiii.skyblockdragons.item.material.materials.admin.weapon;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.CombatStats;
import org.bukkit.Material;

public class AdminNoobSword extends SwordMaterial {
    public AdminNoobSword() {
        super("ADMIN_NOOB_SWORD",
                Material.WOOD_SWORD,
                ItemFamily.ADMIN,
                "Admin N00B Sword",
                Rarity.SPECIAL,
                new CombatStats(1000, 1000, 1000, 0, 0,1000 ,0, 1000, 1000, 1000, 1000, 1000, 0),
                ""
        );
    }
}

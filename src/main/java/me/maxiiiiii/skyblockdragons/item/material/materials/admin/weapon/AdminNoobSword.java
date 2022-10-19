package me.maxiiiiii.skyblockdragons.item.material.materials.admin.weapon;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class AdminNoobSword extends SwordMaterial {
    public AdminNoobSword() {
        super("ADMIN_NOOB_SWORD",
                Material.WOOD_SWORD,
                ItemFamily.ADMIN,
                "Admin N00B Sword",
                Rarity.SPECIAL,
                new Stats(1000, 1000, 1000, 1000, 1000,0 ,0, 1000, 0, 1000),
                ""
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

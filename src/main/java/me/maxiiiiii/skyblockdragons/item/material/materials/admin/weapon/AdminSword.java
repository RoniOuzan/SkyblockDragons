package me.maxiiiiii.skyblockdragons.item.material.materials.admin.weapon;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class AdminSword extends SwordMaterial {
    public AdminSword() {
        super("ADMIN_SWORD",
                Material.DIAMOND_SWORD,
                ItemFamily.ADMIN,
                "Admin Sword",
                Rarity.SPECIAL,
                new Stats(10000000, 10000000, 10000000, 10000000, 10000000,0 ,0, 10000000, 0, 10000000),
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

package me.maxiiiiii.skyblockdragons.item.material.materials.admin.armor;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSet;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Color;
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

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

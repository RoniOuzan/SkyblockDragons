package me.maxiiiiii.skyblockdragons.item.material.materials.admin.armor;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Color;
import org.bukkit.Material;

public class AdminSurvivalChestplate extends ArmorMaterial {
    public AdminSurvivalChestplate() {
        super("ADMIN_SURVIVAL_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.ADMIN,
                "Admin N00B Boots",
                ItemType.CHESTPLATE,
                Rarity.SPECIAL,
                new Stats(0, 0, 0, 0, 0, 0, 100000, 10000000, 100, 0),
                "",
                ItemFullSet.NULL,
                (Color) null
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

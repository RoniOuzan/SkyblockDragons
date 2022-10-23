package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.superior;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.witherarmors.WitherArmorFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Color;
import org.bukkit.Material;

public class SuperiorDragonChestplate extends ArmorMaterial {
    public SuperiorDragonChestplate() {
        super("SUPERIOR_DRAGON_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.SUPERIOR_DRAGON,
                "Superior Dragon Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new Stats(0, 20, 10, 2, 0, 0, 150, 190, 3, 25),
                "",
                new SuperiorDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 21)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(242,223,17);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

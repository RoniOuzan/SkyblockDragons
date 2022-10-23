package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.unstable;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
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

public class UnstableDragonBoots extends ArmorMaterial {
    public UnstableDragonBoots() {
        super("UNSTABLE_DRAGON_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.UNSTABLE_DRAGON,
                "Unstable Dragon Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 15, 5, 0, 0, 60, 90, 0, 0),
                "",
                new UnstableDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(178,18,227);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.strong;

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

public class StrongDragonBoots extends ArmorMaterial {
    public StrongDragonBoots() {
        super("STRONG_DRAGON_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.STRONG_DRAGON,
                "Strong Dragon Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 25, 0, 0, 0, 0, 60, 90, 0, 0),
                "",
                new StrongDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(240,209,36);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

package me.maxiiiiii.skyblockdragons.item.material.materials.other.bows;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.BowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.general.MultiShot;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class RunaansBow extends BowMaterial {
    public RunaansBow() {
        super("RUNAANS_BOW",
                Material.BOW,
                ItemFamily.NULL,
                "Runaan's Bow",
                Rarity.LEGENDARY,
                new Stats(160, 50, 0, 0, 0, 0),
                "",
                new MultiShot(3),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

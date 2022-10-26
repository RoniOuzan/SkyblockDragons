package me.maxiiiiii.skyblockdragons.item.material.materials.theend.bows;

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

public class StrongBow extends BowMaterial {
    public StrongBow() {
        super("STRONG_BOW",
                Material.BOW,
                ItemFamily.NULL,
                "Strong Bow",
                Rarity.LEGENDARY,
                new Stats(180, 80, 0, 0, 0, 0),
                "",
                new MultiShot(3, 1.1),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }
}

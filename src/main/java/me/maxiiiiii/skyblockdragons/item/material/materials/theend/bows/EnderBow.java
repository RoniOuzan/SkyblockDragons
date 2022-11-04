package me.maxiiiiii.skyblockdragons.item.material.materials.theend.bows;

import me.maxiiiiii.skyblockdragons.item.material.types.BowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class EnderBow extends BowMaterial {
    public EnderBow() {
        super("ENDER_BOW",
                Material.BOW,
                ItemFamily.ENDER,
                "Ender Bow",
                Rarity.EPIC,
                new Stats(125, 80, 5, 10, 0, 0),
                "Deals double damage to Ender Dragons.",
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }
}

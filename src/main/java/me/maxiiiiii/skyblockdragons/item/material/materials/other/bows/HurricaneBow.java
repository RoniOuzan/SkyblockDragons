package me.maxiiiiii.skyblockdragons.item.material.materials.other.bows;

import me.maxiiiiii.skyblockdragons.item.material.types.BowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.general.MultiShot;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class HurricaneBow extends BowMaterial {
    public HurricaneBow() {
        super("HURRICANE_BOW",
                Material.BOW,
                ItemFamily.NULL,
                "Hurricane Bow",
                Rarity.EPIC,
                new Stats(120, 50, 0, 0, 0, 0),
                "",
                new MultiShot("HURRICANE_BOW", 5),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }
}

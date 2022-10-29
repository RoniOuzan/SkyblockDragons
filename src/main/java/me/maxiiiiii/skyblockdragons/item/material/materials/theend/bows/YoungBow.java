package me.maxiiiiii.skyblockdragons.item.material.materials.theend.bows;

import me.maxiiiiii.skyblockdragons.item.material.types.BowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.general.MultiShot;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class YoungBow extends BowMaterial {
    public YoungBow() {
        super("YOUNG_BOW",
                Material.BOW,
                ItemFamily.NULL,
                "Young Bow",
                Rarity.LEGENDARY,
                new Stats(160, 50, 0, 0, 0, 0),
                "",
                new MultiShot("YOUNG_BOW", 3, 1, 0.6),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }
}

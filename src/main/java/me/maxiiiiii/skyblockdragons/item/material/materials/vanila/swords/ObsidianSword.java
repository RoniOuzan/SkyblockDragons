package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class ObsidianSword extends SwordMaterial {
    public ObsidianSword() {
        super("OBSIDIAN_SWORD",
                Material.STONE_SWORD,
                ItemFamily.OBSIDIAN,
                "Obsidian Sword",
                Rarity.RARE,
                new Stats(45, 20, 10, 2, 0,0 ,0, 0, -10, 0),
                "",
                new SkillRequirement(SkillType.COMBAT, 5)
        );
    }
}

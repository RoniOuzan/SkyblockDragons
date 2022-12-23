package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.obsidian;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.CombatStats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Color;
import org.bukkit.Material;

public class ObsidianLeggings extends ArmorMaterial {
    public ObsidianLeggings() {
        super("OBSIDIAN_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.OBSIDIAN,
                "Obsidian Leggings",
                ItemType.LEGGINGS,
                Rarity.RARE,
                new CombatStats(0, 8, 8, 1, 0, 0, 95, 45, -5, 5),
                "",
                new SkillRequirement(SkillType.COMBAT, 5)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(30, 0, 30);
    }
}

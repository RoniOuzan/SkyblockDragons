package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.obsidian;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSet;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
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
                new Stats(0, 8, 12, 0, 0, 0, 95, 45, -5, 5),
                "",
                new SkillRequirement(SkillType.COMBAT, 3)
        );
    }

    @Override
    public Color getColor() {
        return super.getColor(); // TODO
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

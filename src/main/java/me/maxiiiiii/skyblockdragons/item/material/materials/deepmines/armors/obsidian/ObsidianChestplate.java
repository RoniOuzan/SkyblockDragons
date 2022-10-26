package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.obsidian;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Color;
import org.bukkit.Material;

public class ObsidianChestplate extends ArmorMaterial {
    public ObsidianChestplate() {
        super("OBSIDIAN_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.OBSIDIAN,
                "Obsidian Chestplate",
                ItemType.CHESTPLATE,
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
}

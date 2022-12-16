package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.polar;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Color;
import org.bukkit.Material;

public class PolarBearBoots extends ArmorMaterial {
    public PolarBearBoots() {
        super("POLAR_BEAR_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.PANDA_BEAR,
                "Polar Bear Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 20, 15, 10, 0, 5, 120, 100, 0, 50),
                "",
                ItemFullSetBonus.POLAR_BEAR_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(187,191,189);
    }
}
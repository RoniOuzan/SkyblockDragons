package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.unstable;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Color;
import org.bukkit.Material;

public class UnstableDragonLeggings extends ArmorMaterial {
    public UnstableDragonLeggings() {
        super("UNSTABLE_DRAGON_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.UNSTABLE_DRAGON,
                "Unstable Dragon Leggings",
                ItemType.LEGGINGS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 15, 5, 0, 0, 100, 140, 0, 0),
                "",
                ItemFullSetBonus.UNSTABLE_DRAGON_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(178,18,227);
    }
}

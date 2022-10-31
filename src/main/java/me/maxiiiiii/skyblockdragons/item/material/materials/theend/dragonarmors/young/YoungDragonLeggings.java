package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.young;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Color;
import org.bukkit.Material;

public class YoungDragonLeggings extends ArmorMaterial {
    public YoungDragonLeggings() {
        super("YOUNG_DRAGON_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.YOUNG_DRAGON,
                "Young Dragon Leggings",
                ItemType.LEGGINGS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 100, 140, 20, 0),
                "",
                ItemFullSetBonus.YOUNG_DRAGON_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(221,228,240);
    }
}

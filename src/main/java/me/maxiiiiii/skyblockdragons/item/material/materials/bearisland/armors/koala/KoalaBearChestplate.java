package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.koala;

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

public class KoalaBearChestplate extends ArmorMaterial {
    public KoalaBearChestplate() {
        super("KOALA_BEAR_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.KOALA_BEAR,
                "Koala Bear Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new Stats(0, 20, 15, 10, 0, 5, 120, 100, 0, 50),
                "",
                ItemFullSetBonus.KOALA_BEAR_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(146,147,142);
    }
}
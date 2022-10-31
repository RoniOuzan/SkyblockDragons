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

public class YoungDragonBoots extends ArmorMaterial {
    public YoungDragonBoots() {
        super("YOUNG_DRAGON_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.YOUNG_DRAGON,
                "Young Dragon Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 60, 90, 20, 0),
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

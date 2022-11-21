package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.old;

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

public class OldDragonChestplate extends ArmorMaterial {
    public OldDragonChestplate() {
        super("OLD_DRAGON_CHESTPLATE",
                Material.LEATHER_CHESTPLATE,
                ItemFamily.OLD_DRAGON,
                "Old Dragon Chestplate",
                ItemType.CHESTPLATE,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 150, 160, 0, 0),
                "",
                ItemFullSetBonus.OLD_DRAGON_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(240,230,170);
    }
}

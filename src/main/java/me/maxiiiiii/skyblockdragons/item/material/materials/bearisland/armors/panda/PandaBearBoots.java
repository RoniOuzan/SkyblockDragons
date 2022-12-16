package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.panda;

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

public class PandaBearBoots extends ArmorMaterial {
    public PandaBearBoots() {
        super("PANDA_BEAR_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.PANDA_BEAR,
                "Panda Bear Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 20, 15, 10, 0, 5, 120, 100, 0, 50),
                "",
                ItemFullSetBonus.PANDA_BEAR_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(197,201,198);
    }
}
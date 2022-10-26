package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.old;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Color;
import org.bukkit.Material;

public class OldDragonBoots extends ArmorMaterial {
    public OldDragonBoots() {
        super("OLD_DRAGON_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.OLD_DRAGON,
                "Old Dragon Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 80, 90, 0, 0),
                "",
                new OldDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(240,230,170);
    }
}

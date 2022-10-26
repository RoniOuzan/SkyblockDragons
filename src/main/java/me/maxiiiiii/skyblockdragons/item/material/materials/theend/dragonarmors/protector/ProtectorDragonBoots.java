package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.protector;

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

public class ProtectorDragonBoots extends ArmorMaterial {
    public ProtectorDragonBoots() {
        super("PROTECTOR_DRAGON_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.PROTECTOR_DRAGON,
                "Protector Dragon Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 60, 115, 0, 0),
                "",
                new ProtectorDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(153,151,139);
    }
}

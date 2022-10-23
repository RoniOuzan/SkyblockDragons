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

public class ProtectorDragonLeggings extends ArmorMaterial {
    public ProtectorDragonLeggings() {
        super("PROTECTOR_DRAGON_LEGGINGS",
                Material.LEATHER_LEGGINGS,
                ItemFamily.PROTECTOR_DRAGON,
                "Protector Dragon Leggings",
                ItemType.LEGGINGS,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 100, 165, 0, 0),
                "",
                new ProtectorDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(153,151,139);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

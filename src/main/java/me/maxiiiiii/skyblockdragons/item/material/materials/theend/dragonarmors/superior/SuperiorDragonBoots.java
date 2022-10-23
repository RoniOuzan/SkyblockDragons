package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.superior;

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

public class SuperiorDragonBoots extends ArmorMaterial {
    public SuperiorDragonBoots() {
        super("SUPERIOR_DRAGON_BOOTS",
                Material.LEATHER_BOOTS,
                ItemFamily.SUPERIOR_DRAGON,
                "Superior Dragon Boots",
                ItemType.BOOTS,
                Rarity.LEGENDARY,
                new Stats(0, 20, 10, 2, 0, 0, 80, 110, 3, 25),
                "",
                new SuperiorDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 21)
        );
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(242,93,24);
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

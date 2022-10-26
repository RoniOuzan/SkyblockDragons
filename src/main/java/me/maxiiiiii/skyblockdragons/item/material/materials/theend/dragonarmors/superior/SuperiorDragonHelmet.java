package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.superior;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class SuperiorDragonHelmet extends ArmorMaterial {
    public SuperiorDragonHelmet() {
        super("SUPERIOR_DRAGON_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.SUPERIOR_DRAGON,
                "Superior Dragon Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 20, 10, 2, 0, 0, 90, 130, 3, 25),
                "",
                new SuperiorDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 21)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("7fd17d6f-9e42-478c-8cce-68aec1d52eec",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0="
        );
    }
}

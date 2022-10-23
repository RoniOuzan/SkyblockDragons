package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.protector;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class ProtectorDragonHelmet extends ArmorMaterial {
    public ProtectorDragonHelmet() {
        super("PROTECTOR_DRAGON_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.PROTECTOR_DRAGON,
                "Protector Dragon Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 70, 135, 0, 0),
                "",
                new ProtectorDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("15d988d6-db86-4fa4-ae92-025f6fdad9f5",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjM3YTU5NmNkYzRiMTFhOTk0OGZmYTM4YzJhYTNjNjk0MmVmNDQ5ZWIwYTM5ODIyODFkM2E1YjVhMTRlZjZhZSJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.strong;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class StrongDragonHelmet extends ArmorMaterial {
    public StrongDragonHelmet() {
        super("STRONG_DRAGON_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.STRONG_DRAGON,
                "Strong Dragon Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 25, 0, 0, 0, 0, 70, 110, 0, 0),
                "",
                ItemFullSetBonus.STRONG_DRAGON_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("007b09db-936a-4ee1-8e60-6f61172bfe4e",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWZkZTA5NjAzYjAyMjViOWQyNGE3M2EwZDNmM2UzYWYyOTA1OGQ0NDhjY2Q3Y2U1YzY3Y2QwMmZhYjBmZjY4MiJ9fX0="
        );
    }
}

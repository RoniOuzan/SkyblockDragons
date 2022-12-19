package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.pigman;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class PigmanHelmet extends ArmorMaterial {
    public PigmanHelmet() {
        super("PIGMAN_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.PIGMAN,
                "Pigman Helmet",
                ItemType.HELMET,
                Rarity.UNCOMMON,
                new Stats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10),
                "",
                ItemFullSetBonus.PIGMAN_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 3)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("a94086e2-ae98-42c2-a96b-d7b548e3ae2a",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg1Mzg2Zjc4MDMzNDNlNWU2YjdlOGVlMDYxNjc3ZmYxN2U0ZjU2MTAwNTQ3OWQzOTQ3MmIyNjU3ZTA4ODQyZSJ9fX0="
        );
    }
}

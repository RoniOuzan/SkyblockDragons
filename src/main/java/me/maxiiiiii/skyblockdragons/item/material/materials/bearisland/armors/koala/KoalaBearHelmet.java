package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.koala;

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

public class KoalaBearHelmet extends ArmorMaterial {
    public KoalaBearHelmet() {
        super("KOALA_BEAR_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.KOALA_BEAR,
                "Koala Bear Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 20, 15, 10, 0, 5, 120, 100, 0, 50),
                "",
                ItemFullSetBonus.KOALA_BEAR_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("719e39f7-5ca2-460a-8364-8e0624ebf8b9",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZjOWQ1MWU5OGJlM2U2ODlmNmM2OWRjN2ZkNDU0ZDk1OTYyOWE1MDU1OWJlZDE2ZWFiY2Q3YTM4Zjk4MWYifX19"
        );
    }
}
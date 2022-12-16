package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.grizzly;

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
public class GrizzlyBearHelmet extends ArmorMaterial {
    public GrizzlyBearHelmet() {
        super("GRIZZLY_BEAR_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.GRIZZLY_BEAR,
                "Grizzly Bear Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 20, 15, 10, 0, 5, 120, 100, 0, 10),
                "",
                ItemFullSetBonus.GRIZZLY_BEAR_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("b83764ef-0575-44f4-a2dd-5b3929a2dd86",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmVhNTVkNGM3Zjg1ODc3ZGU2MDg3MTdlOGY0NDIyYTJhYjg0ZGU2OTY3ZjEyOTIxZDU4YmRmZmRlYzk5In19fQ=="
        );
    }
}

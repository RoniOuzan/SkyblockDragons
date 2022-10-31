package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.young;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class YoungDragonHelmet extends ArmorMaterial {
    public YoungDragonHelmet() {
        super("YOUNG_DRAGON_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.YOUNG_DRAGON,
                "Young Dragon Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 70, 110, 20, 0),
                "",
                ItemFullSetBonus.YOUNG_DRAGON_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("12e3458b-42ba-49ba-bdee-0a746f16b143",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWNmNmFlNGQ4MjdiNzdlMzNlNDMxMGM3ZmJjODY4M2E4MWZlYjJiNTJhZDFmMjYwNGM2MWQzMzZjNTdhMTljMCJ9fX0="
        );
    }
}

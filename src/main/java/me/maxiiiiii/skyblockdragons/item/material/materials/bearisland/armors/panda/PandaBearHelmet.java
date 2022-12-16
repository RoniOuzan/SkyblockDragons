package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.panda;

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

public class PandaBearHelmet extends ArmorMaterial {
    public PandaBearHelmet() {
        super("PANDA_BEAR_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.PANDA_BEAR,
                "Panda Bear Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 20, 15, 10, 0, 5, 120, 100, 0, 10),
                "",
                ItemFullSetBonus.PANDA_BEAR_FULL_SET,
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("0dfc11a8-0e87-41f9-a8a9-f73c22f984eb",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDI2ZjJmYThiNWI0YjRkNzBjNDEzMmE3MTMzNmQ4ZjRkYTU0MTkyNThkZmNhZDE4Y2NhODQ1OWNmNTYyMzkwMyJ9fX0="
        );
    }
}

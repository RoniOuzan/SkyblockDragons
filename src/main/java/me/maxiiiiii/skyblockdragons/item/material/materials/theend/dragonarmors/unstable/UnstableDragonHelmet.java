package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.unstable;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class UnstableDragonHelmet extends ArmorMaterial {
    public UnstableDragonHelmet() {
        super("UNSTABLE_DRAGON_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.UNSTABLE_DRAGON,
                "Unstable Dragon Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 0, 15, 5, 0, 0, 70, 110, 0, 0),
                "",
                new UnstableDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("787379c1-f850-49e1-9ffc-d5311b61e762",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjkyMmI1ZjhkNTU0Y2E5MjNmOTY4MzJhNWE0ZTkxNjliYzJjZGIzNjBhMmIwNmViZWMwOWI2YTZhZjQ2MThlMyJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}

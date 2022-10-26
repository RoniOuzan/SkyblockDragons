package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.old;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.Material;

public class OldDragonHelmet extends ArmorMaterial {
    public OldDragonHelmet() {
        super("OLD_DRAGON_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.OLD_DRAGON,
                "Old Dragon Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 90, 110, 0, 0),
                "",
                new OldDragonFullSet(),
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("59919a61-9e44-4e2b-99fc-84b54f8e6f5c",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTllOWU1NjAwNDEwYzFkMDI1NDQ3NGE4MWZlY2ZiMzg4NWMxY2Y2ZjUwNDE5MGQ4NTZmMGVjN2M5ZjA1NWM0MiJ9fX0="
        );
    }
}

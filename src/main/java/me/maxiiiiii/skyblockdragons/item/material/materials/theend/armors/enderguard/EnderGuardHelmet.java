package me.maxiiiiii.skyblockdragons.item.material.materials.theend.armors.enderguard;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class EnderGuardHelmet extends ArmorMaterial {
    public EnderGuardHelmet() {
        super("ENDER_GUARD_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.ENDER,
                "Ender Guard Helmet",
                ItemType.HELMET,
                Rarity.EPIC,
                new Stats(0, 6, 6, 2, 0, 0, 135, 60, 0, 10),
                "Increases all damage stats by " + ChatColor.GREEN + "10% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".",
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("0dc3174c-0fac-4cb1-8c5f-3dba5c1d335b",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQ4YjZmODc4OTAxNWVhNjdiMWQ3ZmE1N2E1OWQ3YTRhZDJhZTc0Y2U3ZDk5ZTY1ZmZhZDExNDg5ZTdkYzY1In19fQ=="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {
        if (stats.getPlayer().getWorldSD() instanceof TheEnd) {
            stats.addDamageMultipliers(10, 0);
        }
    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
package me.maxiiiiii.skyblockdragons.item.material.materials.theend.armors.ender;

import me.maxiiiiii.skyblockdragons.events.events.update.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class EnderHelmet extends ArmorMaterial {
    public EnderHelmet() {
        super("ENDER_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.ENDER,
                "Ender Helmet",
                ItemType.HELMET,
                Rarity.RARE,
                new Stats(0, 5, 5, 2, 0, 0, 135, 60, 0, 10),
                "Increases all damage stats by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".",
                new SkillRequirement(SkillType.COMBAT, 12)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("7ddb45e0-6b48-4e42-943e-b0a6dcec34ee",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjcxNWNhMGY3NDI1NDRhZTNjYTEwNDI5NzU3OGMyZWQ3MDBlYTNhNTQ5ODA0MTM1MTJmNWU3YTBiYzA2NzI5YSJ9fX0="
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.isNotThisItem(this)) return;
        
        if (e.getPlayer().getWorldSD() instanceof TheEnd) {
            e.getStats().addDamageMultipliers(5, 0);
        }
    }
}

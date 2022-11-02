package me.maxiiiiii.skyblockdragons.item.material.materials.theend.armors.enderguard;

import me.maxiiiiii.skyblockdragons.item.events.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class EnderGuardBoots extends ArmorMaterial {
    public EnderGuardBoots() {
        super("ENDER_GUARD_BOOTS",
                Material.CHAINMAIL_BOOTS,
                ItemFamily.ENDER,
                "Ender Guard Boots",
                ItemType.BOOTS,
                Rarity.EPIC,
                new Stats(0, 6, 6, 2, 0, 0, 135, 60, 0, 10),
                "Increases all damage stats by " + ChatColor.GREEN + "10% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".",
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.isNotThisItem(this)) return;
        
        if (e.getPlayer().getWorldSD() instanceof TheEnd) {
            e.getStats().addDamageMultipliers(10, 0);
        }
    }
}

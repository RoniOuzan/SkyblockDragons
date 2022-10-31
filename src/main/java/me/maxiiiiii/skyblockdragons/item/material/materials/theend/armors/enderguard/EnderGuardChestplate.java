package me.maxiiiiii.skyblockdragons.item.material.materials.theend.armors.enderguard;

import me.maxiiiiii.skyblockdragons.events.events.update.PlayerUpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class EnderGuardChestplate extends ArmorMaterial {
    public EnderGuardChestplate() {
        super("ENDER_GUARD_CHESTPLATE",
                Material.CHAINMAIL_CHESTPLATE,
                ItemFamily.ENDER,
                "Ender Guard Chestplate",
                ItemType.CHESTPLATE,
                Rarity.EPIC,
                new Stats(0, 6, 6, 2, 0, 0, 135, 60, 0, 10),
                "Increases all damage stats by " + ChatColor.GREEN + "10% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".",
                new SkillRequirement(SkillType.COMBAT, 15)
        );
    }

    @EventHandler
    public void updateStats(PlayerUpdateStatsEvent e) {
        if (e.isNotThisItem(this)) return;
        
        if (e.getPlayer().getWorldSD() instanceof TheEnd) {
            e.getStats().addDamageMultipliers(10, 0);
        }
    }
}

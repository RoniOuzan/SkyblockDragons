package me.maxiiiiii.skyblockdragons.item.material.materials.theend.armors.ender;

import me.maxiiiiii.skyblockdragons.events.events.update.UpdateStatsEvent;
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

public class EnderChestplate extends ArmorMaterial {
    public EnderChestplate() {
        super("ENDER_CHESTPLATE",
                Material.CHAINMAIL_CHESTPLATE,
                ItemFamily.ENDER,
                "Ender Chestplate",
                ItemType.CHESTPLATE,
                Rarity.RARE,
                new Stats(0, 5, 5, 2, 0, 0, 135, 60, 0, 10),
                "Increases all damage stats by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".",
                new SkillRequirement(SkillType.COMBAT, 12)
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

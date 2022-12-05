package me.maxiiiiii.skyblockdragons.item.material.materials.theend.armors.ender;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.StatAdderType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class EnderLeggings extends ArmorMaterial {
    public EnderLeggings() {
        super("ENDER_LEGGINGS",
                Material.CHAINMAIL_LEGGINGS,
                ItemFamily.ENDER,
                "Ender Leggings",
                ItemType.LEGGINGS,
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
            StatTypes.STATS.stream().filter(s -> s instanceof CombatStat).forEach(s -> e.getStats().addBaseMultiplier(s, 5, StatAdderType.ITEM, e.getPlayer().getItems().getLeggings()));
        }
    }
}

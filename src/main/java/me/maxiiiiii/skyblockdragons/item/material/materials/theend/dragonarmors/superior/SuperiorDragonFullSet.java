package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.superior;

import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords.AspectOfTheDragons;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.StatAdderType;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class SuperiorDragonFullSet extends ItemFullSetBonus {
    public SuperiorDragonFullSet() {
        super("Superior blood",
                "All of your stats are increased by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "and " + ChatColor.GOLD + "Aspect of the " + ChatColor.GOLD + "Dragons " + ChatColor.GRAY + "ability deals " + ChatColor.GREEN + "50% " + ChatColor.GRAY + "more damage."
        );
    }

    @EventHandler()
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof SuperiorDragonFullSet) {
            for (StatType statTypes : StatTypes.STATS) {
                e.getStats().addBaseMultiplier(statTypes, 5, StatAdderType.FULL_SET_BONUS, this);
            }
        }
    }

    @EventHandler
    public void updateDamage(UpdateEntityDamageEntityEvent e) {
        if (!(e.getPlayerAttacker().getItems().getFullSet() instanceof SuperiorDragonFullSet)) return;

        if (e.getPlayerAttacker().getItems().getToolMaterial() instanceof AspectOfTheDragons && e.getDamage() instanceof MagicEntityDamageEntity) {
            e.getDamage().getMultiplier().addBase(50);
        }
    }
}

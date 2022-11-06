package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.superior;

import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords.AspectOfTheDragons;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
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
            e.getStats().addAllStatsMultipliers(5, 0);
        }
    }

    @EventHandler
    public void updateDamage(UpdateEntityDamageEntityEvent e) {
        if (!(e.getAttacker().getItems().getFullSet() instanceof SuperiorDragonFullSet)) return;

        if (e.getAttacker().getItems().getToolMaterial() instanceof AspectOfTheDragons && e.getDamage() instanceof MagicEntityDamageEntity) {
            e.getDamage().getMultiplier().addBase(50);
        }
    }
}

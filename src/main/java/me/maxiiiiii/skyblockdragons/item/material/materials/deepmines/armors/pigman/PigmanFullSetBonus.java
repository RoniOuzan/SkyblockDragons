package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.pigman;

import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.swords.PigmanDagger;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class PigmanFullSetBonus extends ItemFullSetBonus {
    public PigmanFullSetBonus() {
        super("Burning",
                "Deal " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.GREEN + "Pigman Dagger" + ChatColor.GRAY + "."
        );
    }

    @EventHandler
    public void updateDamage(UpdateEntityDamageEntityEvent e) {
        if (e.getPlayerAttacker() == null || e.getPlayerAttacker().getItems().getFullSet() != this) return;

        if (e.getPlayerAttacker().getItems().getToolMaterial() instanceof PigmanDagger) {
            e.getDamage().getMultiplier().addBase(20);
        }
    }
}

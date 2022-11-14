package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.strong;

import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords.AspectOfTheEnd;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class StrongDragonFullSet extends ItemFullSetBonus {
    public StrongDragonFullSet() {
        super("Strong blood",
                "Deals " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.BLUE + "Aspect of The End" + ChatColor.GRAY + "."
        );
    }

    @EventHandler
    public void updateDamage(UpdateEntityDamageEntityEvent e) {
        if (e.getPlayerAttacker() == null || !(e.getPlayerAttacker().getItems().getFullSet() instanceof StrongDragonFullSet)) return;

        if (e.getPlayerAttacker().getItems().getToolMaterial() instanceof AspectOfTheEnd) {
            e.getDamage().getMultiplier().addBase(20);
        }
    }
}

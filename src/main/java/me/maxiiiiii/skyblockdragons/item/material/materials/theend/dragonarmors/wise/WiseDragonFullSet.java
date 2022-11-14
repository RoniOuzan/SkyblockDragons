package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.wise;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.UpdateManaCostEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class WiseDragonFullSet extends ItemFullSetBonus {
    public WiseDragonFullSet() {
        super("Wise Blood",
                "Reduces mana cost of abilities by " + ChatColor.GREEN + "40%" + ChatColor.GRAY + "."
        );
    }

    @EventHandler
    public void updateManaCost(UpdateManaCostEvent e) {
        if (!(e.getPlayer().getItems().getFullSet() instanceof WiseDragonFullSet)) return;

        e.getMultiplier().addBase(-40);
    }
}

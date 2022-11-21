package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors;

import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.UpdateCooldownEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class DeeperMinesFullSet extends ItemFullSetBonus {
    private final double amount;

    public DeeperMinesFullSet(double amount) {
        super("Deep Miner",
                "Reduces the cooldown of drill abilities by " + ChatColor.GREEN + "" + amount + "%" + ChatColor.GRAY + "."
        );
        this.amount = amount;
    }

    @EventHandler
    public void updateCooldown(UpdateCooldownEvent e) {
        if (!(e.getPlayer().getItems().getFullSet() instanceof DeeperMinesFullSet)) return;

        if (e.getItem().getMaterial() instanceof DrillMaterial) {
            e.getMultiplier().addBase(-amount);
        }
    }
}

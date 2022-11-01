package me.maxiiiiii.skyblockdragons.damagenew;

import me.maxiiiiii.skyblockdragons.damagenew.interfaces.DamageCritable;
import me.maxiiiiii.skyblockdragons.damagenew.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class Damage implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent e) {
        double damage = e.getDamage().getFinalDamage();

        String damageDisplay = ChatColor.GRAY + "" + Functions.getNumberFormat(damage);
        if (e.getDamage() instanceof DamageCritable && e.getDamage() instanceof EntityDamageEntity && ((EntityDamageEntity) e.getDamage()).isCritHit()) {
            damageDisplay = Functions.getNumberFormat(damage);
            damageDisplay = rainbowText(damageDisplay);
            damageDisplay = ChatColor.WHITE + "✧" + damageDisplay + ChatColor.WHITE + "✧";
        } else if (e.getDamage() instanceof MagicEntityDamageEntity)
            damageDisplay = ChatColor.DARK_AQUA + "" + Functions.getNumberFormat(damage);

        Location hologram = e.getVictim().getLocation();
        double random = randomDouble(1, 1.5);
        hologram.add(e.getVictim().getLocation().getDirection().multiply(random));
        hologram.setY(e.getVictim().getLocation().getY() + random);

        createHologram(hologram, damageDisplay, 20);
    }
}

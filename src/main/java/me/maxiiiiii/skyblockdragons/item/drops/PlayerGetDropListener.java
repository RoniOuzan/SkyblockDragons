package me.maxiiiiii.skyblockdragons.item.drops;

import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class PlayerGetDropListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerGetDrop(PlayerGetDropEvent e) {
        UpdateDropChanceEvent event = new UpdateDropChanceEvent(e.getPlayer(), e.getDrop(), e.getSource());
        Bukkit.getPluginManager().callEvent(event);

        double chances = event.getMultiplier().multiply(e.getDrop().getChances());
        if (Math.random() <= chances) {
            if (e.getPlayer().getItems().getTool().getModifiers().getEnchants().getOrDefault(EnchantType.TELEKINESIS, (short) 0) > 1) {
                e.getDrop().give(e.getPlayer());
            } else {
                Location location = e.getPlayer().getLocation();
                if (e.getSource() instanceof LivingEntity && e.getDrop().getSourceType() == DropSourceType.ENTITY) {
                    Entity source = (Entity) e.getSource();
                    location = source.getLocation();
                } else if (e.getSource() instanceof Block && e.getDrop().getSourceType() == DropSourceType.BLOCK) {
                    Block source = (Block) e.getSource();
                    location = source.getLocation().add(new Vector(
                            location.getX() - source.getLocation().getX(),
                            location.getY() - source.getLocation().getY(),
                            location.getZ() - source.getLocation().getZ()
                    ).normalize());
                }

                e.getDrop().dropItem(e.getPlayer(), location);
            }
        }
    }
}
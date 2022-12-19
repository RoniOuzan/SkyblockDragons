package me.maxiiiiii.skyblockdragons.item.drops;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Comparator;
import java.util.List;

public class PlayerGetDropListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerGetDrop(PlayerGetDropEvent e) {
        UpdateDropEvent event = new UpdateDropEvent(e.getPlayer(), e.getDrop(), e.getSource());
        Bukkit.getPluginManager().callEvent(event);

        event.applyAmountMultipliers();
        double chances = event.getChanceMultiplier().multiply(e.getDrop().getChances()) / 100;
        double random = Math.random();
        if (random <= chances) {
            if (e.isTelekinesis()) {
                e.getDrop().give(e.getPlayer());
            } else {
                Location location = e.getPlayer().getLocation();
                if (e.getSource() instanceof LivingEntity && e.getDrop().getSourceType() == DropSourceType.ENTITY) {
                    Entity source = (Entity) e.getSource();
                    location = source.getLocation();
                } else if (e.getSource() instanceof Block && e.getDrop().getSourceType() == DropSourceType.BLOCK) {
                    Block source = (Block) e.getSource();

                    List<Block> around = Functions.getAirAround(source);
                    if (around.size() > 0) {
                        Location finalLocation = location;
                        around.sort(Comparator.comparing(b -> b.getLocation().distance(finalLocation)));

                        location = around.get(0).getLocation();
                        location.add(0.5, 0.5, 0.5);
                    }
                }

                e.getDrop().dropItem(e.getPlayer(), location);
            }
        }
    }
}

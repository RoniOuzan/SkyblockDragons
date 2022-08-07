package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceHeadListener implements Listener {
    @EventHandler
    public void onPlaceHead(BlockPlaceEvent e) {
        if (e.getItemInHand().getType() == Material.SKULL_ITEM)
            if (!Functions.getId(e.getItemInHand()).equals(""))
                e.setCancelled(true);
    }
}

package me.maxiiiiii.skyblockdragons.damage.listeners;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.SlimeSplitEvent;

public class OtherDamageListeners implements Listener {
    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent e) {
        EntitySD entity = EntitySD.get(e.getEntity());
        entity.hologram.remove();
    }
}

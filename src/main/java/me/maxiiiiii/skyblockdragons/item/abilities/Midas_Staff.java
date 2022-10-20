package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Midas_Staff implements Listener {
    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("MIDAS_STAFF")) return;


    }


}

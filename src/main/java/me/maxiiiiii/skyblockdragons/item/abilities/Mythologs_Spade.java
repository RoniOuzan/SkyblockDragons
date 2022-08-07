package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mythologs_Spade implements Listener {
    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial().getFamily() != ItemFamily.MYTHOLOGS_SPADE) return;

        e.getPlayer().getGriffin().showNext();
        e.getPlayer().sendMessage(e.getPlayer().getGriffin().getBurrow().toString());
    }
}

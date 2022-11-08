package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;

@Getter
@Setter
public class PlayerDeathEvent extends PlayerSDEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final EntitySD killer;
    private String message;

    public PlayerDeathEvent(PlayerSD player, EntitySD killer) {
        super(player);
        this.killer = killer;
        player.setAttacker(killer);

        if (killer == null)
            message = player.getDisplayName() + ChatColor.GRAY + " died!";
        else
            message = player.getDisplayName() + " has been killed by " + killer.getName();
    }

    public PlayerDeathEvent(PlayerSD player) {
        this(player, player.getAttacker());
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}

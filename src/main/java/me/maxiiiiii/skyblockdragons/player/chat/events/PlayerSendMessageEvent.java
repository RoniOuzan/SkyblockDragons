package me.maxiiiiii.skyblockdragons.player.chat.events;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.chat.ChatChannel;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

@Getter
@Setter
public class PlayerSendMessageEvent extends PlayerSDEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private String message;
    private ChatChannel channel;

    private boolean isCancelled;

    public PlayerSendMessageEvent(PlayerSD player, String message, ChatChannel channel) {
        super(player);
        this.message = message;
        this.channel = channel;

        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {return handlers;}
    public static HandlerList getHandlerList() {return handlers;}
}

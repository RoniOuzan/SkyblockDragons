package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PlayerUseAbilityEvent extends PlayerSDEvent {
    public static final HandlerList handlers = new HandlerList();
    private static final List<PlayerUseAbilityEvent> instances = new ArrayList<>();

    private Item item;
    private ItemAbility ability;

    public PlayerUseAbilityEvent(PlayerSD player, Item item, ItemAbility ability) {
        super(player);
        this.item = item;
        this.ability = ability;

        instances.add(this);
    }

    public static PlayerUseAbilityEvent getInstance(PlayerSD player, Item item, ItemAbility ability) {
        if (instances.stream().anyMatch(e -> e.getPlayer().equals(player))) {
            for (PlayerUseAbilityEvent instance : instances) {
                if (instance.player.equals(player)) {
                    instance.item = item;
                    instance.ability = ability;
                    return instance;
                }
            }
        }
        return new PlayerUseAbilityEvent(player, item, ability);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

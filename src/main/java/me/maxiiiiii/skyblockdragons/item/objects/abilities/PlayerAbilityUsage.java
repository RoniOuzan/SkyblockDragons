package me.maxiiiiii.skyblockdragons.item.objects.abilities;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.player.PlayerInteractEvent;

@Getter
public class PlayerAbilityUsage {
    private final PlayerSD player;
    private final Item item;
    private final ItemAbility ability;
    private final PlayerInteractEvent event;

    public PlayerAbilityUsage(PlayerSD player, Item item, ItemAbility ability, PlayerInteractEvent event) {
        this.player = player;
        this.item = item;
        this.ability = ability;
        this.event = event;
    }
}

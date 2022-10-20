package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

@Getter
public class PlayerAbilityUsage {
    private final PlayerSD player;
    private final Item item;
    private final ItemAbility ability;

    public PlayerAbilityUsage(PlayerSD player, Item item, ItemAbility ability) {
        this.player = player;
        this.item = item;
        this.ability = ability;
    }
}

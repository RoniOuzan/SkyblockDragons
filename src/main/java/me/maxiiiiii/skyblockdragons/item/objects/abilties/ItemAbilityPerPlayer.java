package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.SingleCooldown;

public class ItemAbilityPerPlayer {
    private final PlayerSD player;
    private final PlayerAbilityRunnable runnable;
    private final SingleCooldown cooldown;

    public ItemAbilityPerPlayer(PlayerSD player, ItemAbility itemAbility, PlayerAbilityRunnable runnable) {
        this.player = player;
        this.runnable = runnable;
        if (itemAbility instanceof ItemAbilityCooldown) {
            this.cooldown = new SingleCooldown(((ItemAbilityCooldown) itemAbility).getBaseCooldown(player));
        } else {
            this.cooldown = null;
        }
    }

    public void run(PlayerAbilityUsage e) {
        if (this.cooldown == null || this.cooldown.isExpired())
            runnable.run(e);
    }

    public PlayerAbilityRunnable getRunnable() {
        return runnable;
    }

    public PlayerSD getPlayer() {
        return player;
    }
}

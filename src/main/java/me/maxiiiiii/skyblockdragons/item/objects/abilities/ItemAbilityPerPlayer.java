package me.maxiiiiii.skyblockdragons.item.objects.abilities;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilitySilentCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.SingleCooldown;

public class ItemAbilityPerPlayer {
    private final PlayerSD player;
    private final ItemAbility itemAbility;
    private final PlayerAbilityRunnable runnable;
    private final SingleCooldown cooldown;

    public ItemAbilityPerPlayer(PlayerSD player, ItemAbility itemAbility, PlayerAbilityRunnable runnable) {
        this.player = player;
        this.itemAbility = itemAbility;
        this.runnable = runnable;
        if (itemAbility instanceof ItemAbilitySilentCooldown) {
            this.cooldown = new SingleCooldown();
        } else {
            this.cooldown = null;
        }
    }

    public void run(PlayerAbilityUsage e) {
        if (isCooldownExpired()) {
            if (cooldown != null) cooldown.reset();
            runnable.run(e);
        }
    }

    private boolean isCooldownExpired() {
        return this.cooldown == null || this.cooldown.isExpired(((ItemAbilitySilentCooldown) itemAbility).getFinalCooldown(player));
    }

    public PlayerAbilityRunnable getRunnable() {
        return runnable;
    }

    public PlayerSD getPlayer() {
        return player;
    }
}

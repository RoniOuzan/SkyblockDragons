package me.maxiiiiii.skyblockdragons.item.objects.abilities;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilitySilentCooldown;
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
        runnable.run(e);
    }

    public boolean isCooldownExpired(double cooldown) {
        return this.cooldown == null || this.cooldown.isExpired(cooldown);
    }

    public double getLastTimeUsed() {
        if (cooldown == null) return 0;

        return this.cooldown.getStartedAt();
    }

    public void resetCooldown() {
        if (this.cooldown != null)
            this.cooldown.reset();
    }

    public PlayerAbilityRunnable getRunnable() {
        return runnable;
    }

    public PlayerSD getPlayer() {
        return player;
    }
}

package me.maxiiiiii.skyblockdragons.item.objects.abilties;

public class ItemAbilityPerPlayer {
    private final PlayerAbilityRunnable runnable;

    public ItemAbilityPerPlayer(PlayerAbilityRunnable runnable) {
        this.runnable = runnable;
    }

    public void run(PlayerAbilityUsage e) {
        runnable.run(e);
    }
}

package me.maxiiiiii.skyblockdragons.util.interfaces;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class LoopTask {
    private final BukkitRunnable bukkitRunnable;
    
    public LoopTask(BukkitRunnable bukkitRunnable) {
        this.bukkitRunnable = bukkitRunnable;
    }
    
    public abstract void run(int i);
    
    public void cancel() {
        this.bukkitRunnable.cancel();
    }
}

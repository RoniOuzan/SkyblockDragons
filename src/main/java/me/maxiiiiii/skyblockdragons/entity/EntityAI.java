package me.maxiiiiii.skyblockdragons.entity;

public abstract class EntityAI {
    protected final EntitySD entity;
    private final long period;

    public EntityAI(EntitySD entity, long period) {
        this.entity = entity;
        this.period = period;
    }

    public long getPeriod() {
        return this.period;
    }

    public abstract void initialize();

    public abstract void run();
}

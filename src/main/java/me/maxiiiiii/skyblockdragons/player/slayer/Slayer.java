package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;

@Getter
public abstract class Slayer {
    private static final int[] COSTS_TO_START = {1_000, 3_750, 10_000, 25_000, 50_000};
    private static final int[] XP = {5, 25, 100, 500, 1500};
    private static final int[] NEED_XP = {5, 15, 200, 1_000, 5_000, 20_000, 100_000, 400_000, 1_000_000};

    private final SlayerType type;

    private int level;
    private int totalXp;

    protected Slayer(SlayerType type, int level, int totalXp) {
        this.type = type;

        this.level = level;
        this.totalXp = totalXp;
    }

    protected Slayer(SlayerType type, PlayerSD player) {
        this(type,
                Variables.getInt(player.getUniqueId(), "Slayer", type + ".level", 0),
                Variables.getInt(player.getUniqueId(), "Slayer", type + ".xp", 0)
        );
    }

    public void giveXp(double amount) {
        this.totalXp += amount;

        while (this.totalXp >= this.getNeedXP()) {
            this.level++;
        }
    }

    public void save(PlayerSD player) {
        Variables.set(player.getUniqueId(), "Slayer", this.type + ".level", this.level);
        Variables.set(player.getUniqueId(), "Slayer", this.type + ".xp", this.totalXp);
    }

    public int getNeedXP() {
        if (level <= 0)
            return NEED_XP[0];
        else if (level > NEED_XP.length)
            return NEED_XP[NEED_XP.length - 1];

        return NEED_XP[level - 1];
    }

    public int getRewardXP(int tier) {
        if (tier <= 0)
            return XP[0];
        else if (tier > XP.length)
            return XP[XP.length - 1];

        return XP[tier - 1];
    }

    @Override
    public String toString() {
        return "Slayer{" +
                "type=" + type +
                ", level=" + level +
                ", totalXp=" + totalXp +
                '}';
    }

    public static int getCostToStart(int tier) {
        if (tier <= 0)
            return COSTS_TO_START[0];
        else if (tier > COSTS_TO_START.length)
            return COSTS_TO_START[COSTS_TO_START.length - 1];

        return COSTS_TO_START[tier - 1];
    }
}
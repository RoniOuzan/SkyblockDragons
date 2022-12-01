package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.Location;

@Getter
public class SlayerQuest {
    private final SlayerType type;
    private final int tier;
    private double currentXp;
    private final double needXp;
    private boolean isBossSpawned;

    public SlayerQuest(SlayerType type, int tier) {
        this.type = type;
        this.tier = tier;
        this.currentXp = 0;
        this.needXp = this.type.getNeedXp(this.tier);
        this.isBossSpawned = false;
    }

    public void giveXp(double amount, EntitySD source) {
        this.currentXp += amount;

        if (this.currentXp >= this.needXp && !this.isBossSpawned) {
            this.isBossSpawned = true;
            this.spawn(source.getLocation());
        }
    }

    public void spawn(Location location) {
        new EntitySD(location, EntityMaterial.get(this.type.getEntity() + "_TIER_" + this.tier));
    }

    @Override
    public String toString() {
        return "SlayerQuest{" +
                "type=" + type +
                ", tier=" + tier +
                ", currentXp=" + currentXp +
                ", needXp=" + needXp +
                '}';
    }
}

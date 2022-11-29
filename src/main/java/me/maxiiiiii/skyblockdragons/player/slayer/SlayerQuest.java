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

    public SlayerQuest(SlayerType type, int tier) {
        this.type = type;
        this.tier = tier;
        this.currentXp = 0;
        this.needXp = this.type.getNeedXp(this.tier);
    }

    public void giveXp(double amount) {
        this.currentXp += amount;
    }

    public void spawn(Location location) {
        new EntitySD(location, EntityMaterial.get(this.type.getEntity() + "_" + this.tier));
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

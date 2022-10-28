package me.maxiiiiii.skyblockdragons.util.objects.cooldowns;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;

@Getter
public class SingleCooldown {
    private double startedAt;

    public SingleCooldown() {
        this.startedAt = 0;
    }

    public void reset() {
        this.startedAt = SkyblockDragons.getCurrentTimeInSeconds();
    }

    public boolean isExpired(double seconds) {
        return SkyblockDragons.getCurrentTimeInSeconds() - this.startedAt >= seconds;
    }
}

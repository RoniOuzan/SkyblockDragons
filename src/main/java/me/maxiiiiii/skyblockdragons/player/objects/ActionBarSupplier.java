package me.maxiiiiii.skyblockdragons.player.objects;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;

@Getter
public class ActionBarSupplier {
    private final String text;
    private final double duration;
    private final double startedAt;

    public ActionBarSupplier(String text, double duration) {
        this.text = text;
        this.duration = duration;
        this.startedAt = SkyblockDragons.getCurrentTimeInSeconds();
    }

    @Override
    public String toString() {
        return this.text.replace(".0", "");
    }
}

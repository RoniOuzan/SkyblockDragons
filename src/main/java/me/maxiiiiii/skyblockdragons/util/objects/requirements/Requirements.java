package me.maxiiiiii.skyblockdragons.util.objects.requirements;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.Arrays;
import java.util.List;

@Getter
public class Requirements {
    private final List<Requirement> requirements;

    public Requirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public Requirements(Requirement... requirements) {
        this(Arrays.asList(requirements));
    }

    public boolean hasRequirements(PlayerSD player) {
        return player.ignoreItemRequirements() ||
                this.requirements.stream().allMatch(r -> r.hasRequirement(player));
    }
}

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
        return player.ignoreRequirements() ||
                this.requirements.stream().allMatch(r -> r.hasRequirement(player));
    }

    public Requirement getRequirement(int index) {
        return this.requirements.get(index);
    }

    @Override
    public String toString() {
        return this.requirements.toString();
    }
}

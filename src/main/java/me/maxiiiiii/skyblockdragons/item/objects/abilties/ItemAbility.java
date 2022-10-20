package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ItemAbility implements MaterialModifier {
    private final AbilityAction action;
    private final String name;
    private final String description;

    private final Map<PlayerSD, ItemAbilityPerPlayer> abilities = new HashMap<>();
    private final Cooldown<PlayerSD> cooldown = new Cooldown<>();

    protected ItemAbility(AbilityAction action, String name, String description) {
        this.action = action;
        this.name = name;
        this.description = description;
    }

    public abstract PlayerAbilityRunnable setupAbility();

    public void onPlayerUse(PlayerAbilityUsage abilityUsage) {
        if (!abilities.containsKey(abilityUsage.getPlayer())) {
            abilities.put(abilityUsage.getPlayer(), new ItemAbilityPerPlayer(setupAbility()));
        }
        abilities.get(abilityUsage.getPlayer()).run(abilityUsage);
    }

    public abstract boolean isPlayerCanUseAbility(PlayerSD player);
}

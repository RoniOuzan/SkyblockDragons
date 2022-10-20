package me.maxiiiiii.skyblockdragons.item.objects.abilties.newsystem;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ItemAbilityNew implements MaterialModifier {
    private final AbilityAction action;
    private final String name;
    private final String description;

    private final Map<PlayerSD, PlayerAbilityRunnable> abilities = new HashMap<>();

    protected ItemAbilityNew(AbilityAction action, String name, String description) {
        this.action = action;
        this.name = name;
        this.description = description;
    }

    public abstract PlayerAbilityRunnable setupAbility();

    public void onPlayerUse(PlayerAbilityUsage abilityUsage) {
        if (!abilities.containsKey(abilityUsage.getPlayer())) {
            abilities.put(abilityUsage.getPlayer(), setupAbility());
        }
        abilities.get(abilityUsage.getPlayer()).run(abilityUsage);
    }

    public abstract boolean isPlayerCanUseAbility(PlayerSD player);
}

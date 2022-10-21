package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers.costs.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers.costs.ItemAbilityManaCostPercentage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class ItemAbility implements MaterialModifier {
    private final AbilityAction action;
    private final String name;
    private final String description;

    protected final Map<PlayerSD, ItemAbilityPerPlayer> abilities = new HashMap<>();

    protected ItemAbility(AbilityAction action, String name, String description) {
        this.action = action;
        this.name = name;
        this.description = description;
    }

    public boolean hasCosts(PlayerSD player) {
        boolean output = true;
        if (this instanceof ItemAbilityManaCost) output = ((ItemAbilityManaCost) this).get(player);
        if (this instanceof ItemAbilityManaCostPercentage) output &= ((ItemAbilityManaCostPercentage) this).get(player);
        return output;
    }

    public void applyCosts(PlayerSD player) {
        if (this instanceof ItemAbilityManaCost) ((ItemAbilityManaCost) this).applyCost(player);
        if (this instanceof ItemAbilityManaCostPercentage) ((ItemAbilityManaCostPercentage) this).applyCost(player);
    }

    public abstract PlayerAbilityRunnable setupAbility();

    public void onPlayerUse(PlayerAbilityUsage abilityUsage) {
        if (!abilities.containsKey(abilityUsage.getPlayer())) {
            abilities.put(abilityUsage.getPlayer(), new ItemAbilityPerPlayer(abilityUsage.getPlayer(), this, setupAbility()));
        }
        abilities.get(abilityUsage.getPlayer()).run(abilityUsage);
    }

    public List<String> getModifiersLore(PlayerSD player) {
        List<String> lores = new ArrayList<>();
        if (this instanceof ItemAbilityCooldown) ItemAbilityCooldown.getLine((ItemAbilityCooldown) this, player);
        if (this instanceof ItemAbilityManaCost) ItemAbilityManaCost.getLine((ItemAbilityManaCost) this, player);
        if (this instanceof ItemAbilityManaCostPercentage) ItemAbilityManaCostPercentage.getLine((ItemAbilityManaCostPercentage) this, player);
        return lores;
    }

    public ItemAbilityPerPlayer getAbilityOfPlayer(PlayerSD player) {
        return abilities.get(player);
    }
}

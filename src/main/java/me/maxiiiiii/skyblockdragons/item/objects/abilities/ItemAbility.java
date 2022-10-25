package me.maxiiiiii.skyblockdragons.item.objects.abilities;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCostPercentage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class ItemAbility implements MaterialModifier {
    @Getter(AccessLevel.NONE) private final String abilityTitle;
    private final AbilityAction action;
    private final String name;
    private final String description;

    protected final Map<PlayerSD, ItemAbilityPerPlayer> users = new HashMap<>();

    protected ItemAbility(String abilityTitle, AbilityAction action, String name, String description) {
        this.abilityTitle = abilityTitle;
        this.action = action;
        this.name = name;
        this.description = description;

        if (this instanceof Listener) {
            Bukkit.getPluginManager().registerEvents((Listener) this, SkyblockDragons.plugin);
        }
    }

    protected ItemAbility(AbilityAction action, String name, String description) {
        this("Item Ability:", action, name, description);
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
        if (!users.containsKey(abilityUsage.getPlayer())) {
            users.put(abilityUsage.getPlayer(), new ItemAbilityPerPlayer(abilityUsage.getPlayer(), this, setupAbility()));
        }
        users.get(abilityUsage.getPlayer()).run(abilityUsage);
    }

    public List<String> getLore(PlayerSD player) {
        List<String> lores = new ArrayList<>();
        lores.add(getAbilityFullTitle());
        lores.addAll(Functions.loreBuilder(this.description));
        lores.addAll(getModifiersLore(player));
        return lores;
    }

    protected String getAbilityFullTitle() {
        return ChatColor.GOLD + abilityTitle + " " + name + " " + action.toString();
    }

    protected List<String> getModifiersLore(PlayerSD player) {
        List<String> lores = new ArrayList<>();
        if (this instanceof ItemAbilityCooldown) lores.add(ItemAbilityCooldown.getLine((ItemAbilityCooldown) this, player));
        if (this instanceof ItemAbilityManaCost) lores.add(ItemAbilityManaCost.getLine((ItemAbilityManaCost) this, player));
        if (this instanceof ItemAbilityManaCostPercentage) lores.add(ItemAbilityManaCostPercentage.getLine((ItemAbilityManaCostPercentage) this, player));
        return lores;
    }

    protected ItemAbilityPerPlayer getAbilityOfPlayer(PlayerSD player) {
        return users.get(player);
    }
}

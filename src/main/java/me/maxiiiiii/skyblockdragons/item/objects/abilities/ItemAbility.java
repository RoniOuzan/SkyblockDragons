package me.maxiiiiii.skyblockdragons.item.objects.abilities;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilitySilentCooldown;
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
import java.util.function.Function;

@Getter
public abstract class ItemAbility implements MaterialModifier {
    @Getter(AccessLevel.NONE) private final String abilityTitle;
    private final AbilityAction action;
    private final String name;
    private final Function<PlayerSD, String> description;

    protected final Map<PlayerSD, ItemAbilityPerPlayer> users = new HashMap<>();

    protected ItemAbility(String abilityTitle, AbilityAction action, String name, Function<PlayerSD, String> description) {
        this.abilityTitle = abilityTitle;
        this.action = action;
        this.name = name;
        this.description = description;

        if (this instanceof Listener) {
            Bukkit.getPluginManager().registerEvents((Listener) this, SkyblockDragons.plugin);
        }
    }

    protected ItemAbility(AbilityAction action, String name, Function<PlayerSD, String> description) {
        this("Item Ability:", action, name, description);
    }

    protected ItemAbility(AbilityAction action, String name, String description) {
        this("Item Ability:", action, name, p -> description);
    }

    public String getDescription(PlayerSD player) {
        return this.description.apply(player);
    }

    public boolean hasCosts(PlayerSD player) {
        boolean output = true;
        if (this instanceof ItemAbilitySilentCooldown) output = ((ItemAbilitySilentCooldown) this).get(player, getAbilityOfPlayer(player));
        if (output && this instanceof ItemAbilityManaCost) output = ((ItemAbilityManaCost) this).get(player);
        if (output && this instanceof ItemAbilityManaCostPercentage) output = ((ItemAbilityManaCostPercentage) this).get(player);
        return output;
    }

    public void applyCosts(PlayerSD player) {
        if (this instanceof ItemAbilitySilentCooldown) ((ItemAbilitySilentCooldown) this).applyCost(getAbilityOfPlayer(player));
        if (this instanceof ItemAbilityManaCost) ((ItemAbilityManaCost) this).applyCost(player);
        if (this instanceof ItemAbilityManaCostPercentage) ((ItemAbilityManaCostPercentage) this).applyCost(player);
    }

    protected abstract PlayerAbilityRunnable setupAbility();

    public void onPlayerUse(PlayerAbilityUsage abilityUsage) {
        users.get(abilityUsage.getPlayer()).run(abilityUsage);
    }

    public void setupAbilityPerPlayer(PlayerSD player) {
        if (!users.containsKey(player)) {
            PlayerAbilityRunnable runnable = setupAbility();
            if (runnable == null) runnable = e -> {};
            users.put(player, new ItemAbilityPerPlayer(player, this, runnable));
        }
    }

    public List<String> getLore(PlayerSD player) {
        List<String> lores = new ArrayList<>();
        lores.add(getAbilityFullTitle());
        lores.addAll(Functions.loreBuilder(this.description.apply(player)));
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

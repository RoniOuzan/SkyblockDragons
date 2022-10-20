package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class ItemAbility implements MaterialModifier {
    private AbilityAction action;
    private String name;
    private String description;
    @Getter(AccessLevel.NONE)
    private int manaCost;
    private int cooldown;
    private double abilityDamage;
    private double abilityScaling;

    private final Map<PlayerSD, PlayerAbilityRunnable> abilities = new HashMap<>();

    public ItemAbility(AbilityAction action, String name, String description, int manaCost, int cooldown, double abilityDamage, double abilityScaling) {
        this.action = action;
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.abilityDamage = abilityDamage;
        this.abilityScaling = abilityScaling;

        if (this instanceof Listener) {
            Bukkit.getPluginManager().registerEvents((Listener) this, SkyblockDragons.plugin);
        }
    }

    public ItemAbility(AbilityAction action, String name, String description, int manaCost, int cooldown) {
        this(action, name, description, manaCost, cooldown, 0, 0);
    }

    public ItemAbility(AbilityAction action, String name, String description) {
        this(action, name, description, 0, 0, 0, 0);
    }

    public PlayerAbilityRunnable getAbilityForPlayer(PlayerSD player) {
        return abilities.get(player);
    }

    public abstract PlayerAbilityRunnable setupAbility();

    public void onPlayerUse(PlayerAbilityUsage abilityUsage) {
        if (!abilities.containsKey(abilityUsage.getPlayer())) {
            abilities.put(abilityUsage.getPlayer(), setupAbility());
        }
        abilities.get(abilityUsage.getPlayer()).run(abilityUsage);
    }

    public boolean isPlayerHasEnoughMana(PlayerSD player) {
        return player.getStats().getMana().get() >= this.manaCost;
    }

    @Override
    public String toString() {
        return "ItemAbility{" +
                "action=" + action +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manaCost=" + manaCost +
                ", cooldown=" + cooldown +
                ", abilityDamage=" + abilityDamage +
                ", abilityScaling=" + abilityScaling +
                '}';
    }
}

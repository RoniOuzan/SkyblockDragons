package me.maxiiiiii.skyblockdragons.item.objects.abilties;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;

@Getter
@Setter
public abstract class ItemAbility implements MaterialModifier {
    private AbilityAction action;
    private String name;
    private String description;
    private int manaCost;
    private int cooldown;
    private double abilityDamage;
    private double abilityScaling;

    public ItemAbility(AbilityAction action, String name, String description, int manaCost, int cooldown, double abilityDamage, double abilityScaling) {
        this.action = action;
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.abilityDamage = abilityDamage;
        this.abilityScaling = abilityScaling;
    }

    public ItemAbility(AbilityAction action, String name, String description, int manaCost, int cooldown) {
        this(action, name, description, manaCost, cooldown, 0, 0);
    }

    public ItemAbility(AbilityAction action, String name, String description) {
        this(action, name, description, 0, 0, 0, 0);
    }

    public abstract Runnable onAbilityUse(PlayerUseAbilityEvent e);

    public int getManaCost(PlayerSD player) { // TODO: change it to return boolean
        return this.manaCost;
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

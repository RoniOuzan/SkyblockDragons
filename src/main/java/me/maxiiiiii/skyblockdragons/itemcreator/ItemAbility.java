package me.maxiiiiii.skyblockdragons.itemcreator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAbility {
    private AbilityAction action;
    private String name;
    private String description;
    private int manaCost;
    private int cooldown;

    public ItemAbility(AbilityAction action, String name, String description, int manaCost, int cooldown) {
        this.action = action;
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
    }
}

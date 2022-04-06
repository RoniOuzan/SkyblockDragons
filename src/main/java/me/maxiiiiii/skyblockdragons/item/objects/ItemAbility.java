package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ItemAbility {
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
    }

    public ItemAbility(AbilityAction action, String name, String description, int manaCost, int cooldown) {
        this(action, name, description, manaCost, cooldown, 0, 0);
    }

//    @Override
//    public Map<String, Object> serialize() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("action", action);
//        map.put("name", name);
//        map.put("description", description);
//        map.put("manaCost", manaCost);
//        map.put("cooldown", cooldown);
//        return map;
//    }
//
//    public static ItemAbility deserialize(Map<String, Object> args) {
//        return new ItemAbility((AbilityAction) args.get("action"), (String) args.get("name"), (String) args.get("description"), (int) args.get("manaCost"), (int) args.get("cooldown"));
//    }
}

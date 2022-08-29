package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAbility implements MaterialModifier {
    private AbilityAction action;
    private String name;
    private String description;
    private int manaCost;
    private boolean customManaCost;
    private int cooldown;
    private double abilityDamage;
    private double abilityScaling;

    public ItemAbility(AbilityAction action, String name, String description, int manaCost, boolean customManaCost, int cooldown, double abilityDamage, double abilityScaling) {
        this.action = action;
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.customManaCost = customManaCost;
        this.cooldown = cooldown;
        this.abilityDamage = abilityDamage;
        this.abilityScaling = abilityScaling;
    }

    public ItemAbility(AbilityAction action, String name, String description, int manaCost, boolean customManaCost, int cooldown) {
        this(action, name, description, manaCost, customManaCost, cooldown, 0, 0);
    }

    public ItemAbility(AbilityAction action, String name, String description) {
        this(action, name, description, 0, false, 0, 0, 0);
    }

    public ItemAbility(AbilityAction action, String name, String description, boolean customManaCost) {
        this(action, name, description, 0, customManaCost, 0, 0, 0);
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

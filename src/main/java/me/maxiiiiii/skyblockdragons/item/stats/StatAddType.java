package me.maxiiiiii.skyblockdragons.item.stats;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.Enchant;
import me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs.PowerOrbDeployAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.accessorybag.PowerStone;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;

public class StatAddType<T> {
    public static final StatAddType<Item> ITEM = new StatAddType<>(Item.class);
    public static final StatAddType<Enchant> ENCHANT = new StatAddType<>(Enchant.class);
    public static final StatAddType<PowerStone> POWER_STONE = new StatAddType<>(PowerStone.class);
    public static final StatAddType<Object> TUNING_POINTS = new StatAddType<>(Object.class);
    public static final StatAddType<Skill> SKILL = new StatAddType<>(Skill.class);
    public static final StatAddType<PowerOrbDeployAbility.PowerOrbType> POWER_ORB = new StatAddType<>(PowerOrbDeployAbility.PowerOrbType.class);
    public static final StatAddType<ItemAbility> ITEM_ABILITY = new StatAddType<>(ItemAbility.class);
    public static final StatAddType<ItemFullSetBonus> FULL_SET_BONUS = new StatAddType<>(ItemFullSetBonus.class);

    public static final StatAddType<Object> NULL = new StatAddType<>(Object.class);

    private final Class<T> clazz;

    public StatAddType(Class<T> clazz) {
        this.clazz = clazz;
    }
}

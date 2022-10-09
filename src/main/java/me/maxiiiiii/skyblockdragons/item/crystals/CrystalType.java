package me.maxiiiiii.skyblockdragons.item.crystals;

import com.google.common.collect.Lists;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;

import java.util.List;

@Getter
public enum CrystalType {
    STRENGTH(Items.get("WITHER_STONE_STRENGTH"), StatType.STRENGTH, Lists.newArrayList(5d, 10d, 20d)),
    SPEED(Items.get("WITHER_STONE_SPEED"), StatType.SPEED, Lists.newArrayList(5d, 10d, 20d)),
    CRIT(Items.get("WITHER_STONE_CRIT"), StatType.CRIT_DAMAGE, Lists.newArrayList(5d, 10d, 20d)),
    HEALTH(Items.get("WITHER_STONE_HEALTH"), StatType.HEALTH, Lists.newArrayList(5d, 10d, 20d));
//    MIND,
//    DEMETER;

    private final ItemMaterial material;
    private final StatType stat;
    private final List<Double> bonus;

    CrystalType(ItemMaterial material, StatType stat, List<Double> bonus) {
        this.material = material;
        this.stat = stat;
        this.bonus = bonus;
    }
}

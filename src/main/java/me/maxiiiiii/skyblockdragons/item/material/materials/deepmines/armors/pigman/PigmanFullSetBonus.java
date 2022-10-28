package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.pigman;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.swords.PigmanDagger;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.ChatColor;

public class PigmanFullSetBonus extends ItemFullSetBonus {
    public PigmanFullSetBonus() {
        super("Burning",
                "Deal " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.GREEN + "Pigman Dagger" + ChatColor.GRAY + "."
        );
    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {
        if (entityDamage.getEquipment().getTool().getMaterial() instanceof PigmanDagger) {
            entityDamage.getMultiplier().addBase(20);
        }
    }
}

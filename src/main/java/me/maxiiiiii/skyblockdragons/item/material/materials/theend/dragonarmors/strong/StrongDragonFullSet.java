package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.strong;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords.AspectOfTheEnd;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSetBonus;
import org.bukkit.ChatColor;

public class StrongDragonFullSet extends ItemFullSetBonus {
    public StrongDragonFullSet() {
        super("Strong blood",
                "Deals " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.BLUE + "Aspect of The End" + ChatColor.GRAY + "."
        );
    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {
        if (entityDamage.getEquipment().getTool().getMaterial() instanceof AspectOfTheEnd) {
            entityDamage.getMultiplier().addBaseMultiplier(20);
        }
    }
}

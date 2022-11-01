package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.damage.interfaces.DamageCritable;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public class CriticalMagicEntityDamageEntity extends MagicEntityDamageEntity implements DamageCritable {
    public CriticalMagicEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment, boolean isFerocity, ItemAbility ability) {
        super(attacker, victim, equipment, isFerocity, ability);
    }

    public CriticalMagicEntityDamageEntity(EntitySD attacker, EntitySD victim, Equipment equipment, ItemAbility ability) {
        super(attacker, victim, equipment, ability);
    }

    @Override
    protected double calculateDamageFormula() {
        if (attacker instanceof PlayerSD) {
            PlayerSD player = (PlayerSD) attacker;
            multiplier.addPost(player.getStats().getAbilityDamage().get());

            double baseAbilityDamage = ability instanceof ItemAbilityMagicDamage ? ((ItemAbilityMagicDamage) ability).getFinalAbilityDamage(player) : 1;
            double abilityScaling = player.getStats().getAbilityScaling().get() +  (ability instanceof ItemAbilityMagicDamage ? ((ItemAbilityMagicDamage) ability).getFinalAbilityDamage(player) : 0);

            return baseAbilityDamage * (1 + (player.getStats().getIntelligence().amount / 100) * (player.getStats().getAbilityScaling().amount + abilityScaling));
        }
        return 1;
    }
}

package me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity;

import me.maxiiiiii.skyblockdragons.damage.suppliers.FerocitySupplier;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public class MagicEntityDamageEntity extends EntityDamageEntity {
    protected final ItemAbility ability;

    public MagicEntityDamageEntity(EntitySD attacker, EntitySD victim, FerocitySupplier isFerocity, ItemAbility ability) {
        super(attacker, victim, isFerocity);
        this.ability = ability;
    }

    public MagicEntityDamageEntity(EntitySD attacker, EntitySD victim, ItemAbility ability) {
        super(attacker, victim);
        this.ability = ability;
    }

    @Override
    protected double calculateDamageFormula() {
        if (attacker instanceof PlayerSD) {
            PlayerSD player = (PlayerSD) attacker;
            multiplier.addPost(player.getStats().getAbilityDamage().get() / 2);

            double baseAbilityDamage = ability instanceof ItemAbilityMagicDamage ? ((ItemAbilityMagicDamage) ability).getFinalAbilityDamage(player) : 1;

            return baseAbilityDamage * (1 + (player.getStats().getIntelligence().get() / 100));
        }
        return 1;
    }
}

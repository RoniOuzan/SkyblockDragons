package me.maxiiiiii.skyblockdragons.item.objects.pet;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;

public abstract class PetAbilityNew extends ItemAbility {
    private final PetAbilitySupplier description;

    public PetAbilityNew(String name, PetAbilitySupplier description) {
        super("Pet Ability:",
                AbilityAction.PET,
                name,
                description.get(1)
        );
        this.description = description;
    }

    public String getDescription(int level) {
        return this.description.get(level);
    }
    // TODO: make it work
    public void updateItemStats(PlayerSD player, Stats stats, int i) {

    }

    public void updateStats(PlayerStats stats, int i) {
    }

    public void updateDamage(EntityDamage<?, ?> entityDamage, int i) {
    }

    public void updateDamageHolder(EntityDamage<?, ?> entityDamage, int i) {
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {};
    }
}

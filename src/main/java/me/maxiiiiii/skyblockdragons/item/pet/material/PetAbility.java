package me.maxiiiiii.skyblockdragons.item.pet.material;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;

import java.util.ArrayList;
import java.util.List;

public abstract class PetAbility extends ItemAbility {
    private final PetAbilitySupplier description;

    public PetAbility(String name, PetAbilitySupplier description) {
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

    public List<String> getLore(PlayerSD player, int level) {
        List<String> lores = new ArrayList<>();
        lores.add(getAbilityFullTitle());
        lores.addAll(Functions.loreBuilder(this.description.get(level)));
        lores.addAll(getModifiersLore(player));
        return lores;
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {};
    }
}

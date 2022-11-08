package me.maxiiiiii.skyblockdragons.item.pet.material;

import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class PetAbility extends ItemAbility implements Listener {
    private final PetAbilitySupplier description;

    public PetAbility(String name, PetAbilitySupplier description) {
        super("Pet Ability:",
                AbilityAction.PET,
                name,
                (p, d) -> description.get(p, 1)
        );
        this.description = description;
    }

    public String getDescription(PlayerSD player, int level) {
        return this.description.get(player, level);
    }

    public List<String> getLore(PlayerSD player, int level) {
        List<String> lores = new ArrayList<>();
        lores.add(getAbilityFullTitle());
        lores.addAll(Functions.loreBuilder(this.description.get(player, level)));
        lores.addAll(getModifiersLore(player));
        return lores;
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {};
    }
}

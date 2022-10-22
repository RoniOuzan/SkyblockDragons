package me.maxiiiiii.skyblockdragons.item.objects.fullset;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;

@Getter
public abstract class ItemFullSetBonus extends ItemAbility {
    private final int amountOfPieces;

    protected ItemFullSetBonus(AbilityAction action, String name, String description, int amountOfPieces) {
        super(action, name, description);
        this.amountOfPieces = amountOfPieces;
    }

    protected ItemFullSetBonus(String name, String description, int amountOfPieces) {
        this(AbilityAction.NONE, name, description, amountOfPieces);
    }

    protected ItemFullSetBonus(AbilityAction action, String name, String description) {
        this(action, name, description, 4);
    }

    protected ItemFullSetBonus(String name, String description) {
        this(AbilityAction.NONE, name, description, 4);
    }

    public void updateStats(PlayerStats stats) {

    }

    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    @Override
    public boolean hasCosts(PlayerSD player) {
        return super.hasCosts(player) && isPlayerWearingFullSet(player);
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {};
    }

    public boolean isPlayerWearingFullSet(PlayerSD player) {
        int amount = 0;
        for (Item item : player.getItems()) {
            if (item.getMaterial() instanceof ItemAbilityAble) {
                ItemFullSetBonus fullSet = ((ItemAbilityAble) item.getMaterial()).getAbilities().stream()
                        .filter(a -> a instanceof ItemFullSetBonus)
                        .map(a -> (ItemFullSetBonus) a)
                        .findFirst().orElse(null);
                if (fullSet == this) {
                    amount++;
                }
            }

            if (amount >= this.amountOfPieces) {
                return true;
            }
        }
        return false;
    }
}

package me.maxiiiiii.skyblockdragons.item.objects.abilities;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.DeeperMinesFullSet;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.pigman.PigmanFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.WitherArmorFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.old.OldDragonFullSet;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.protector.ProtectorDragonFullSet;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.strong.StrongDragonFullSet;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.superior.SuperiorDragonFullSet;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.unstable.UnstableDragonFullSet;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.wise.WiseDragonFullSet;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.young.YoungDragonFullSet;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
public abstract class ItemFullSetBonus extends ItemAbility implements Listener {
    public static final ItemFullSetBonus NULL = new NullFullSetBonus();

    public static final List<ItemFullSetBonus> fullSets = new ArrayList<>();

    public static ItemFullSetBonus SUPERIOR_DRAGON_FULL_SET;
    public static ItemFullSetBonus STRONG_DRAGON_FULL_SET;
    public static ItemFullSetBonus WISE_DRAGON_FULL_SET;
    public static ItemFullSetBonus YOUNG_DRAGON_FULL_SET;
    public static ItemFullSetBonus UNSTABLE_DRAGON_FULL_SET;
    public static ItemFullSetBonus PROTECTOR_DRAGON_FULL_SET;
    public static ItemFullSetBonus OLD_DRAGON_FULL_SET;
    
    public static ItemFullSetBonus COBALT_FULL_SET;
    public static ItemFullSetBonus CHLOROPHYTE_FULL_SET;
    public static ItemFullSetBonus LUMINATE_FULL_SET;
    public static ItemFullSetBonus DERNIC_FULL_SET;
    public static ItemFullSetBonus HEMATITE_FULL_SET;
    public static ItemFullSetBonus VOID_CRYSTAL_FULL_SET;
    
    public static ItemFullSetBonus PIGMAN_FULL_SET;
    public static ItemFullSetBonus WITHER_ARMOR_FULL_SET;

    private final int amountOfPieces;

    protected ItemFullSetBonus(AbilityAction action, String name, Function<PlayerSD, String> description, int amountOfPieces) {
        super("Full Set Bonus:", action, name, (p, d) -> description.apply(p));
        this.amountOfPieces = amountOfPieces;
    }

    protected ItemFullSetBonus(AbilityAction action, String name, String description, int amountOfPieces) {
        this(action, name, p -> description, amountOfPieces);
    }

    protected ItemFullSetBonus(String name, Function<PlayerSD, String> description, int amountOfPieces) {
        this(AbilityAction.NONE, name, description, amountOfPieces);
    }

    protected ItemFullSetBonus(String name, String description, int amountOfPieces) {
        this(AbilityAction.NONE, name, p -> description, amountOfPieces);
    }

    protected ItemFullSetBonus(AbilityAction action, String name, Function<PlayerSD, String> description) {
        this(action, name, description, 4);
    }

    protected ItemFullSetBonus(AbilityAction action, String name, String description) {
        this(action, name, p -> description, 4);
    }

    protected ItemFullSetBonus(String name, Function<PlayerSD, String> description) {
        this(AbilityAction.NONE, name, description, 4);
    }

    protected ItemFullSetBonus(String name, String description) {
        this(AbilityAction.NONE, name, p -> description, 4);
    }

    @Override
    public boolean hasCosts(PlayerSD player, Item item) {
        return super.hasCosts(player, item) && isPlayerWearingFullSet(player);
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {};
    }

    public boolean isPlayerWearingFullSet(EntitySD entity) {
        int amount = 0;
        for (Item item : entity.getItems()) {
            if (item == null) continue;

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

    public static void registerFullSets() {
        // Dragons
        SUPERIOR_DRAGON_FULL_SET = new SuperiorDragonFullSet(); fullSets.add(SUPERIOR_DRAGON_FULL_SET);
        STRONG_DRAGON_FULL_SET = new StrongDragonFullSet(); fullSets.add(STRONG_DRAGON_FULL_SET);
        YOUNG_DRAGON_FULL_SET = new YoungDragonFullSet(); fullSets.add(YOUNG_DRAGON_FULL_SET);
        UNSTABLE_DRAGON_FULL_SET = new UnstableDragonFullSet(); fullSets.add(UNSTABLE_DRAGON_FULL_SET);
        WISE_DRAGON_FULL_SET = new WiseDragonFullSet(); fullSets.add(WISE_DRAGON_FULL_SET);
        PROTECTOR_DRAGON_FULL_SET = new ProtectorDragonFullSet(); fullSets.add(PROTECTOR_DRAGON_FULL_SET);
        OLD_DRAGON_FULL_SET = new OldDragonFullSet(); fullSets.add(OLD_DRAGON_FULL_SET);
        
        // Deeper Mines
        COBALT_FULL_SET = new DeeperMinesFullSet(5); fullSets.add(COBALT_FULL_SET);
        CHLOROPHYTE_FULL_SET = new DeeperMinesFullSet(10); fullSets.add(CHLOROPHYTE_FULL_SET);
        LUMINATE_FULL_SET = new DeeperMinesFullSet(15); fullSets.add(LUMINATE_FULL_SET);
        DERNIC_FULL_SET = new DeeperMinesFullSet(20); fullSets.add(DERNIC_FULL_SET);
        HEMATITE_FULL_SET = new DeeperMinesFullSet(30); fullSets.add(HEMATITE_FULL_SET);
        VOID_CRYSTAL_FULL_SET = new DeeperMinesFullSet(50); fullSets.add(VOID_CRYSTAL_FULL_SET);
        
        // Other
        PIGMAN_FULL_SET = new PigmanFullSetBonus(); fullSets.add(PIGMAN_FULL_SET);
        WITHER_ARMOR_FULL_SET = new WitherArmorFullSetBonus(); fullSets.add(WITHER_ARMOR_FULL_SET);
    }

    private static class NullFullSetBonus extends ItemFullSetBonus {
        public NullFullSetBonus() {
            super(AbilityAction.NULL, "Null", "", Integer.MAX_VALUE);
        }
    }
}

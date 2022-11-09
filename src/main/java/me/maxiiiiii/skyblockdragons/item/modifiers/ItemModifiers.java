package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.crystals.Crystals;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.pet.PetSupplier;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * To add a modifier create a class and extend ItemModifier
 * Then add public static ItemModifier getModifier(ItemStack item) method
 * And then create an instance in ItemModifiers of the class and add it to the method toArray() and to getModifiers()
 */
public class ItemModifiers implements Iterable<ItemModifier> {
    private final EnchantModifier enchants;
    private final HotPotatoModifier hotPotato;
    private final NecronBladeScrollsModifier necronBladeScrolls;
    private final RecombabulatorModifier recombabulator;
    private final ReforgeModifier reforge;
    private final SkinModifier skin;
    private final CrystalModifier crystals;
    private final PetModifier pet;

    public ItemModifiers(EnchantModifier enchants, HotPotatoModifier hotPotato, NecronBladeScrollsModifier necronBladeScrolls, RecombabulatorModifier recombabulator, ReforgeModifier reforge, SkinModifier skin, CrystalModifier crystals, PetModifier pet) {
        this.enchants = enchants;
        this.hotPotato = hotPotato;
        this.necronBladeScrolls = necronBladeScrolls;
        this.recombabulator = recombabulator;
        this.reforge = reforge;
        this.skin = skin;
        this.crystals = crystals;
        this.pet = pet;
    }

    public ItemModifiers(ItemModifier... modifiers) {
        this(
                (EnchantModifier) getOrDefault(modifiers, EnchantModifier.class, new EnchantModifier()),
                (HotPotatoModifier) getOrDefault(modifiers, HotPotatoModifier.class, new HotPotatoModifier()),
                (NecronBladeScrollsModifier) getOrDefault(modifiers, NecronBladeScrollsModifier.class, new NecronBladeScrollsModifier()),
                (RecombabulatorModifier) getOrDefault(modifiers, RecombabulatorModifier.class, new RecombabulatorModifier()),
                (ReforgeModifier) getOrDefault(modifiers, ReforgeModifier.class, new ReforgeModifier()),
                (SkinModifier) getOrDefault(modifiers, SkinModifier.class, new SkinModifier()),
                (CrystalModifier) getOrDefault(modifiers, CrystalModifier.class, new CrystalModifier()),
                (PetModifier) getOrDefault(modifiers, PetModifier.class, new PetModifier())
        );
    }

    public static ItemModifier[] override(ItemModifier[] itemModifiers, ItemModifier... modifiers) {
        Map<Class<? extends ItemModifier>, ItemModifier> modifierMap = new HashMap<>();
        for (ItemModifier modifier : itemModifiers) {
            modifierMap.put(modifier.getClass(), modifier);
        }
        for (ItemModifier modifier : modifiers) {
            modifierMap.put(modifier.getClass(), modifier);
        }
        return modifierMap.values().toArray(new ItemModifier[0]);
    }

    public ItemModifiers getOverrided(ItemModifier... modifiers) {
        return new ItemModifiers(override(toArray(), modifiers));
    }

    public Map<EnchantType, Short> getEnchants() {
        return this.enchants.get();
    }

    public Crystals getCrystals() {
        return this.crystals.get();
    }

    public int getHotPotato() {
        return this.hotPotato.get();
    }

    public List<NecronBladeMaterial.NecronBladeAbility> getNecronBladeScrolls() {
        return this.necronBladeScrolls.get();
    }

    public boolean getRecombabulated() {
        return this.recombabulator.get();
    }

    public ReforgeType getReforge() {
        return this.reforge.get();
    }

    public SkinMaterial getSkin() {
        return this.skin.get();
    }

    public PetSupplier getPet() {
        return this.pet.get();
    }

    public ItemModifier[] toArray() {
        return new ItemModifier[]{
                this.enchants,
                this.hotPotato,
                this.necronBladeScrolls,
                this.recombabulator,
                this.reforge,
                this.crystals,
                this.skin
        };
    }

    @Override
    public Iterator<ItemModifier> iterator() {
        return Arrays.stream(this.toArray()).iterator();
    }

    @Override
    public void forEach(Consumer<? super ItemModifier> action) {
        Objects.requireNonNull(action);
        for (ItemModifier e : this.toArray()) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<ItemModifier> spliterator() {
        return Spliterators.spliterator(this.toArray(), Spliterator.ORDERED);
    }

    public Stream<ItemModifier> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    private static ItemModifier getOrDefault(ItemModifier[] modifiers, Class<? extends ItemModifier> clazz, ItemModifier defaultValue) {
        for (ItemModifier modifier : modifiers) {
            if (modifier.getClass().isAssignableFrom(clazz)) {
                return modifier;
            }
        }
        return defaultValue;
    }

    public static ItemModifiers getModifiers(ItemStack item) {
        return new ItemModifiers(EnchantModifier.getModifier(item),
                HotPotatoModifier.getModifier(item),
                NecronBladeScrollsModifier.getModifier(item),
                RecombabulatorModifier.getModifier(item),
                ReforgeModifier.getModifier(item),
                SkinModifier.getModifier(item),
                CrystalModifier.getModifier(item),
                PetModifier.getModifier(item)
        );
    }
}

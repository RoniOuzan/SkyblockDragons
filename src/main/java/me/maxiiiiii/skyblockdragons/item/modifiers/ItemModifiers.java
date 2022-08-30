package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ItemModifiers implements Iterable<ItemModifier> {
    private final EnchantModifier enchants;
    private final HotPotatoModifier hotPotato;
    private final NecronBladeScrollsModifier necronBladeScrolls;
    private final RecombabulatorModifier recombabulator;
    private final ReforgeModifier reforge;
    private final SkinModifier skin;

    public ItemModifiers(EnchantModifier enchants, HotPotatoModifier hotPotato, NecronBladeScrollsModifier necronBladeScrolls, RecombabulatorModifier recombabulator, ReforgeModifier reforge, SkinModifier skin) {
        this.enchants = enchants;
        this.hotPotato = hotPotato;
        this.necronBladeScrolls = necronBladeScrolls;
        this.recombabulator = recombabulator;
        this.reforge = reforge;
        this.skin = skin;
    }

    public ItemModifiers(ItemModifier... modifiers) {
        this(
                (EnchantModifier) getOrDefault(modifiers, EnchantModifier.class, new EnchantModifier()),
                (HotPotatoModifier) getOrDefault(modifiers, HotPotatoModifier.class, new HotPotatoModifier()),
                (NecronBladeScrollsModifier) getOrDefault(modifiers, NecronBladeScrollsModifier.class, new NecronBladeScrollsModifier()),
                (RecombabulatorModifier) getOrDefault(modifiers, RecombabulatorModifier.class, new RecombabulatorModifier()),
                (ReforgeModifier) getOrDefault(modifiers, ReforgeModifier.class, new ReforgeModifier()),
                (SkinModifier) getOrDefault(modifiers, SkinModifier.class, new SkinModifier())
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

    public ItemModifier[] toArray() {
        return new ItemModifier[]{
                this.enchants,
                this.hotPotato,
                this.necronBladeScrolls,
                this.recombabulator,
                this.reforge,
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
        List<ItemModifier> modifiers = new ArrayList<>();
        for (Class<? extends ItemModifier> clazz : new ArrayList<>(ItemModifier.getClasses())) {
            try {
                ItemModifier modifier = (ItemModifier) clazz.getDeclaredMethod("getModifier", ItemStack.class).invoke(null, item);
                modifiers.add(modifier);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return new ItemModifiers(modifiers.toArray(new ItemModifier[0]));
    }
}

package me.maxiiiiii.skyblockdragons.item.material.types;

import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilitySilentCooldown;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public abstract class ShortBowMaterial extends RangeWeaponMaterial {
    public ShortBowMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description, InstaShoot instaShoot, MaterialModifier... modifiers) {
        super(itemID, material, family, name, ItemType.SHORT_BOW, rarity, stats, description, add(instaShoot, modifiers));
    }

    public ShortBowMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, String description, MaterialModifier... modifiers) {
        this(itemID, material, family, name, rarity, stats, description, new InstaShoot(itemID), modifiers);
    }

    private static MaterialModifier[] add(MaterialModifier materialModifier, MaterialModifier... materialModifiers) {
        List<MaterialModifier> modifiers = new ArrayList<>();
        modifiers.add(materialModifier);
        modifiers.addAll(Arrays.asList(materialModifiers));
        return modifiers.toArray(new MaterialModifier[0]);
    }

    public static class InstaShoot extends me.maxiiiiii.skyblockdragons.item.objects.abilities.general.MultiShot implements ItemAbilitySilentCooldown {
        private final Function<PlayerSD, String> description;

        public InstaShoot(String itemID, Function<PlayerSD, String> description, int amountOfArrows, double power) {
            super(itemID, "", amountOfArrows, power, 0);
            this.description = description;
        }

        public InstaShoot(String itemID, String description, int amountOfArrows, double power) {
            this(itemID, p -> description, amountOfArrows, power);
        }

        public InstaShoot(String itemID, int amountOfArrows, double power) {
            this(itemID, "", amountOfArrows, power);
        }

        public InstaShoot(String itemID) {
            this(itemID, "", 1, 1);
        }

        @Override
        public String getDescription(PlayerSD player) {
            String description = this.description.apply(player);
            if (this.getAmountOfArrows() == 1)
                return description;
            return "Shoots " + ChatColor.AQUA + getAmountOfArrows() + ChatColor.GRAY + " arrows at once!" + (description.isEmpty() ? "" : " NEW_LINE " + description);
        }

        @Override
        protected String getAbilityFullTitle() {
            return ChatColor.GOLD + "Shortbow: Instantly Shoots!";
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return player.getHitTick() / 1000d;
        }
    }
}

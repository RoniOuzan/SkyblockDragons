package me.maxiiiiii.skyblockdragons.item.material.materials.theend.pets;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.pet.PetAbilityNew;
import me.maxiiiiii.skyblockdragons.item.objects.pet.PetRarity;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

import java.util.Arrays;

public class EndermanPet extends PetMaterial {
    public EndermanPet() {
        super("ENDERMAN",
                "Enderman",
                new Stats(0, 0, 0.5, 0.1, 0, 0),
                Arrays.asList(
                        new PetRarity(Rarity.COMMON, new EnderianCommon()),
                        new PetRarity(Rarity.UNCOMMON, new EnderianUncommon()),
                        new PetRarity(Rarity.RARE,
                                new EnderianUncommon(),
                                new Enderator())
                ),
                SkillType.COMBAT,
                EnumWrappers.Particle.SPELL_WITCH,
                Sound.ENTITY_ENDERMEN_AMBIENT
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("6e4d2ed0-05e8-4959-b06c-e649d9113349",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZjMGIzNmQ1M2ZmZjY5YTQ5YzdkNmYzOTMyZjJiMGZlOTQ4ZTAzMjIyNmQ1ZTgwNDVlYzU4NDA4YTM2ZTk1MSJ9fX0="
        );
    }

    private static class EnderianCommon extends PetAbilityNew {
        private static final double MULTIPLIER = 0.1;

        public EnderianCommon() {
            super("Enderian",
                    l -> "Take " + ChatColor.GREEN + (l * MULTIPLIER) + "% " + ChatColor.GRAY + "less damage from end monsters."
            );
        }

        @Override
        public void updateDamageHolder(EntityDamage<?, ?> entityDamage, int i) {
            entityDamage.getMultiplier().addBaseMultiplier(-i * MULTIPLIER);
        }
    }

    private static class EnderianUncommon extends PetAbilityNew {
        private static final double MULTIPLIER = 0.2;

        public EnderianUncommon() {
            super("Enderian",
                    l -> "Take " + ChatColor.GREEN + (l * MULTIPLIER) + "% " + ChatColor.GRAY + "less damage from end monsters."
            );
        }

        @Override
        public void updateDamageHolder(EntityDamage<?, ?> entityDamage, int i) {
            entityDamage.getMultiplier().addBaseMultiplier(-i * MULTIPLIER);
        }
    }

    private static class Enderator extends PetAbilityNew {
        private static final double MULTIPLIER = 0.5;

        public Enderator() {
            super("Enderator",
                    i -> "Increases the chance of enderman drops by " + ChatColor.GREEN + (i * MULTIPLIER) + "%" + ChatColor.GRAY + "."
            );
        }

        @Override
        public void updateStats(PlayerStats stats, int i) {
            // TODO: increase drops
        }
    }
}

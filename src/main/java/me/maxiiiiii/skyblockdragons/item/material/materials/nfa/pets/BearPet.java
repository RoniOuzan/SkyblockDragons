package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.pets;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.pet.PetAbilityNew;
import me.maxiiiiii.skyblockdragons.item.objects.pet.PetRarity;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

import java.util.Arrays;

public class BearPet extends PetMaterial {
    public BearPet() {
        super("BEAR",
                "Bear",
                new Stats(0, 2, 1, 0.5, 0, 0.5),
                Arrays.asList(
                        new PetRarity(Rarity.EPIC, new FuriousBear()),
                        new PetRarity(Rarity.EPIC,
                                new FuriousBear(),
                                new FerocityIsStronger())
                ),
                SkillType.COMBAT,
                EnumWrappers.Particle.SPELL_WITCH,
                Sound.ENTITY_ENDERMEN_AMBIENT

        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("877042bf-3a95-4a20-8c42-aaa234bfea69",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWIxMjUwM2Q2MWM0OWY3MDFmZWU4NjdkNzkzZjFkY2M1MjJlNGQ3YzVjNDFhNjhmMjk1MTU3OWYyNGU3Y2IyYSJ9fX0="
        );
    }

    private static class FuriousBear extends PetAbilityNew {
        private static final double MULTIPLIER = 0.5;

        public FuriousBear() {
            super("Furious Bear",
                    i -> "Increases " + ChatColor.RED + "⫽ Ferocity " + ChatColor.GRAY + "by" + ChatColor.GREEN + (i * MULTIPLIER) + "%"
            );
        }

        @Override
        public void updateStats(PlayerStats stats, int i) {
            stats.addMultiplier(StatType.FEROCITY, 25, 0);
        }
    }

    private static class FerocityIsStronger extends PetAbilityNew {
        private static final double MULTIPLIER = 0.5;

        public FerocityIsStronger() {
            super("Ferocity is Stronger",
                    i -> "Ferocity Attacks deal " + ChatColor.GREEN + (i * MULTIPLIER) + "%" + ChatColor.GRAY + "more damage"
            );
        }

        @Override
        public void updateDamage(EntityDamage<?, ?> entityDamage, int i) {
            // TODO
        }
    }
}

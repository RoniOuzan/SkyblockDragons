package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.pets;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetAbility;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetRarity;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;

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

    private static class FuriousBear extends PetAbility {
        private static final double MULTIPLIER = 0.25;

        public FuriousBear() {
            super("Furious Bear",
                    (p, i) -> "Increases " + ChatColor.RED + "⫽ Ferocity " + ChatColor.GRAY + "by" + ChatColor.GREEN + (i * MULTIPLIER) + "%"
            );
        }

        @EventHandler
        public void updateStats(UpdateStatsEvent e) {
            if (!(e.getPlayer().getActivePetMaterial() instanceof BearPet)) return;

            e.getStats().addMultiplier(StatType.FEROCITY, MULTIPLIER * e.getPlayer().getActivePet().getModifiers().getPet().getLevel(), 0);
        }
    }

    private static class FerocityIsStronger extends PetAbility {
        private static final double MULTIPLIER = 0.5;

        public FerocityIsStronger() {
            super("Ferocity is Stronger",
                    (p, i) -> "Ferocity Attacks deal " + ChatColor.GREEN + (i * MULTIPLIER) + "%" + ChatColor.GRAY + "more damage"
            );
        }

        @EventHandler
        public void updateDamage(UpdateEntityDamageEntityEvent e) {
            if (e.getAttacker() == null || !(e.getAttacker().getActivePetMaterial() instanceof BearPet)) return;

            if (e.getDamage().getFerocity().isFerocityAttack()) {
                e.getDamage().getMultiplier().addBase(MULTIPLIER * e.getAttacker().getActivePet().getModifiers().getPet().getLevel());
            }
        }
    }
}

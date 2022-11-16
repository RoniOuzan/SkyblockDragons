package me.maxiiiiii.skyblockdragons.item.material.materials.theend.pets;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.drops.UpdateDropChanceEvent;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetAbility;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetRarity;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;

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

    private static class EnderianCommon extends PetAbility {
        private static final double MULTIPLIER = 0.1;

        public EnderianCommon() {
            super("Enderian",
                    (p, i) -> "Take " + ChatColor.GREEN + (i * MULTIPLIER) + "% " + ChatColor.GRAY + "less damage from end monsters."
            );
        }

        @EventHandler
        public void updateDamage(UpdateEntityDamageEntityEvent e) {
            if (!(e.getVictim() instanceof PlayerSD) || !(((PlayerSD) e.getVictim()).getActivePetMaterial() instanceof EndermanPet)) return;

            if (e.getDamage().getAttacker().isEndMob()) {
                e.getDamage().getMultiplier().addBase(-MULTIPLIER * ((PlayerSD) e.getVictim()).getActivePet().getModifiers().getPet().getLevel());
            }
        }
    }

    private static class EnderianUncommon extends PetAbility {
        private static final double MULTIPLIER = 0.2;

        public EnderianUncommon() {
            super("Enderian",
                    (p, i) -> "Take " + ChatColor.GREEN + (i * MULTIPLIER) + "% " + ChatColor.GRAY + "less damage from end monsters."
            );
        }

        @EventHandler
        public void updateDamage(UpdateEntityDamageEntityEvent e) {
            if (!(e.getVictim() instanceof PlayerSD) || !(((PlayerSD) e.getVictim()).getActivePetMaterial() instanceof EndermanPet)) return;

            if (e.getDamage().getAttacker().isEndMob()) {
                e.getDamage().getMultiplier().addBase(-MULTIPLIER * ((PlayerSD) e.getVictim()).getActivePet().getModifiers().getPet().getLevel());
            }
        }
    }

    private static class Enderator extends PetAbility {
        private static final double MULTIPLIER = 0.5;

        public Enderator() {
            super("Enderator",
                    (p, i) -> "Increases the chance of enderman drops by " + ChatColor.GREEN + (i * MULTIPLIER) + "%" + ChatColor.GRAY + "."
            );
        }

        @EventHandler
        public void updateDrop(UpdateDropChanceEvent e) {
            if (!(e.getPlayer().getActivePetMaterial() instanceof EndermanPet)) return;

            if (e.getSource() instanceof EntitySD && ((EntitySD) e.getSource()).isEndMob()) {
                e.getMultiplier().addBase(MULTIPLIER * e.getPlayer().getActivePet().getModifiers().getPet().getLevel());
            }
        }
    }
}

package me.maxiiiiii.skyblockdragons.item.material.materials.theend.pets;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords.AspectOfTheDragons;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetAbility;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetRarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateItemStatsEvent;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;

import java.util.Arrays;

public class EnderDragonPet extends PetMaterial {
    public EnderDragonPet() {
        super("ENDER_DRAGON",
                "Ender Dragon",
                new Stats(0, 0.5, 0.5, 0.1, 0, 0),
                Arrays.asList(
                        new PetRarity(Rarity.EPIC,
                                new EndStrike(),
                                new OneWithTheDragons()),
                        new PetRarity(Rarity.LEGENDARY,
                                new EndStrike(),
                                new OneWithTheDragons(),
                                new Superior())
                ),
                SkillType.COMBAT,
                EnumWrappers.Particle.SMOKE_NORMAL, EnumWrappers.Particle.SPELL_WITCH,
                Sound.ENTITY_ENDERDRAGON_FLAP
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("083a89e8-c8b9-4c15-bccb-7b4af8d31b20",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFkMDhjMDI4OWQ5ZWZlNTE5ZTg3ZjdiODE0Y2IyMzQ5ZjQ0NzViZDNjMzdkNDRmOWM0ZjBlNTA4ZTc3OTgxZSJ9fX0="
        );
    }

    private static class EndStrike extends PetAbility {
        private static final double MULTIPLIER = 0.2;

        public EndStrike() {
            super("End Strike",
                    (p, i) -> "Deal " + ChatColor.GREEN + (i * MULTIPLIER) + "% " + ChatColor.GRAY + "more damage to end mobs"
            );
        }

        @EventHandler
        public void updateDamage(UpdateEntityDamageEntityEvent e) {
            if (e.getPlayerAttacker() == null || !(e.getPlayerAttacker().getActivePetMaterial() instanceof EnderDragonPet)) return;

            if (e.getDamage().getVictim().isEndMob()) {
                e.getDamage().getMultiplier().addBase(MULTIPLIER * e.getPlayerAttacker().getActivePet().getModifiers().getPet().getLevel());
            }
        }
    }

    private static class OneWithTheDragons extends PetAbility {
        private static final double DAMAGE_MULTIPLIER = 0.5;
        private static final double STRENGTH_MULTIPLIER = 0.3;

        public OneWithTheDragons() {
            super("One with the Dragons",
                    (p, i) -> "Buffs the Aspect of the Dragons Sword by " + ChatColor.GREEN + (i * DAMAGE_MULTIPLIER) + " " + StatTypes.DAMAGE + " " + ChatColor.GRAY + "and " + ChatColor.GREEN + (i * STRENGTH_MULTIPLIER) + " " + StatTypes.STRENGTH
            );
        }

        @EventHandler
        public void updateItemStats(UpdateItemStatsEvent e) {
            if (!(e.getPlayer().getActivePetMaterial() instanceof EnderDragonPet)) return;

            if (e.getPlayer().getItems().getToolMaterial() instanceof AspectOfTheDragons) {
                int level = e.getPlayer().getActivePet().getModifiers().getPet().getLevel();
                e.getStats().addMultiplier(StatTypes.DAMAGE, level * DAMAGE_MULTIPLIER);
                e.getStats().addMultiplier(StatTypes.STRENGTH, level * STRENGTH_MULTIPLIER);
            }
        }
    }

    private static class Superior extends PetAbility {
        private static final double MULTIPLIER = 0.1;

        public Superior() {
            super("Superior",
                    (p, i) -> "Increases most stats by " + ChatColor.GREEN + (i * MULTIPLIER) + "%"
            );
        }

        @EventHandler
        public void updateStats(UpdateStatsEvent e) {
            if (!(e.getPlayer().getActivePetMaterial() instanceof EnderDragonPet)) return;

            e.getStats().addAllStatsMultipliers(MULTIPLIER * e.getPlayer().getActivePet().getModifiers().getPet().getLevel(), 0);
        }
    }
}

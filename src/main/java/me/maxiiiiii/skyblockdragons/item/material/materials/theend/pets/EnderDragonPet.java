package me.maxiiiiii.skyblockdragons.item.material.materials.theend.pets;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.events.events.update.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetAbility;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetRarity;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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

    private static class EndStrike extends PetAbility implements Listener {
        private static final int MULTIPLIER = 2;

        public EndStrike() {
            super("End Strike",
                    (p, i) -> "Deal " + ChatColor.GREEN + (i * MULTIPLIER) + "% " + ChatColor.GRAY + "more damage to end mobs"
            );
        }

        @EventHandler
        public void updateDamage(UpdateEntityDamageEntityEvent e) {
            if (e.getAttacker() == null || !(e.getAttacker().getActivePetMaterial() instanceof EnderDragonPet)) return;

            if (e.getDamage().getVictim().getType() == EntityType.ENDER_DRAGON ||
                    e.getDamage().getVictim().getType() == EntityType.ENDERMITE ||
                    e.getDamage().getVictim().getType() == EntityType.ENDERMAN
            ) {
                e.getDamage().getMultiplier().addBase(MULTIPLIER * e.getAttacker().getActivePet().getModifiers().getPet().getLevel());
            }
        }
    }

    private static class OneWithTheDragons extends PetAbility {
        private static final double DAMAGE_MULTIPLIER = 0.5;
        private static final double STRENGTH_MULTIPLIER = 0.3;

        public OneWithTheDragons() {
            super("One with the Dragons",
                    (p, i) -> "Buffs the Aspect of the Dragons Sword by " + ChatColor.GREEN + (i * DAMAGE_MULTIPLIER) + " " + StatType.DAMAGE.getIconAndText() + " " + ChatColor.GRAY + "and " + ChatColor.GREEN + (i * STRENGTH_MULTIPLIER) + " " + StatType.STRENGTH.getIconAndText()
            );
        }

        // TODO
//        @Override
//        public void updateItemStats(PlayerSD player, Stats stats, int i) {
//            if (player.getItems().getTool().getMaterial() instanceof AspectOfTheDragons) {
//                stats.add(StatType.DAMAGE, i * DAMAGE_MULTIPLIER);
//                stats.add(StatType.STRENGTH, i * STRENGTH_MULTIPLIER);
//            }
//        }
    }

    private static class Superior extends PetAbility implements Listener {
        private static final double MULTIPLIER = 0.1;

        public Superior() {
            super("Superior",
                    (p, i) -> "Increases most stats by " + ChatColor.GREEN + (i * MULTIPLIER) + "%"
            );
        }

        @EventHandler
        public void updateStats(UpdateStatsEvent e) {
            e.getStats().addAllStatsMultipliers(MULTIPLIER * e.getPlayer().getActivePet().getModifiers().getPet().getLevel(), 0);
        }
    }
}

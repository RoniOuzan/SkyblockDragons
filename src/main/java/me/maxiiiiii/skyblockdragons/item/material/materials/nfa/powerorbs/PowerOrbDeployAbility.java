package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCostPercentage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

// TODO
public class PowerOrbDeployAbility extends ItemAbility implements ItemAbilityManaCostPercentage {
    public enum PowerOrb {
        RADIANT(30, 5, 18, 0.5, 0, 0),
        MANA_FLUX(30, 5, 20, 1, 50, 10),
        OVERFLUX(60, 7, 25, 1.5, 75, 15),
        PLASMA(60, 7, 30, 2, 100, 25);

        private final int duration;
        private final int players;
        private final int range;
        private final double healthRegenPercent;
        private final double manaRegenPercent;
        private final int strength;

        PowerOrb(int duration, int players, int range, double healthRegenPercent, double manaRegenPercent, int strength) {
            this.duration = duration;
            this.players = players;
            this.range = range;
            this.healthRegenPercent = healthRegenPercent;
            this.manaRegenPercent = manaRegenPercent;
            this.strength = strength;
        }
    }

    private final PowerOrb type;

    public PowerOrbDeployAbility(PowerOrb type) {
        super(AbilityAction.RIGHT_CLICK,
                "Deploy",
                "Place an orb for " + ChatColor.GREEN + type.duration + "s " + ChatColor.GRAY + "buffing " +
                        "up to " + ChatColor.AQUA + type.players + " " + ChatColor.GRAY + "players within " + ChatColor.GREEN + type.range + " " +
                        ChatColor.GRAY + "blocks. Only one orb applies per player." + " NEW_LINE " +
                        (type.manaRegenPercent != 0 ? "Grants " + ChatColor.AQUA + "+" + type.manaRegenPercent + "% " + ChatColor.GRAY + " base mana regen. " : "") +
                        (type.healthRegenPercent != 0 ? "Heals " + ChatColor.RED + type.healthRegenPercent + "% " + ChatColor.GRAY + " of max " + ChatColor.RED + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + " per second. " : "") +
                        (type.strength != 0 ? "Grants " + StatType.STRENGTH.getColor() + "+" + type.strength + StatType.STRENGTH.getIconAndText() : "")
        );
        this.type = type;
    }

    @Override
    public double getBaseManaCostPercentage(PlayerSD player) {
        return 50;
    }

    @Override
    protected PlayerAbilityRunnable setupAbility() {
        return e -> {

        };
    }

//    private static class AbilityRunnable
}

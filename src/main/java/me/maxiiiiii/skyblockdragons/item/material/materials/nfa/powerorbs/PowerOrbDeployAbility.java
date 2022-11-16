package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCostPercentage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PowerOrbDeployAbility extends ItemAbility implements ItemAbilityManaCostPercentage {
    @Getter
    public enum PowerOrbType {
        RADIANT(new ParticleUtil(Particle.REDSTONE, 0.1f, 1f, 0.1f, 1f, 1), ChatColor.GREEN, 30, 5, 18, 1, 0, 0),
        MANA_FLUX(new ParticleUtil(Particle.REDSTONE, 0.2f, 0.2f, 1, 1f, 1), ChatColor.AQUA, 30, 5, 20, 2, 50, 10),
        OVERFLUX(new ParticleUtil(Particle.REDSTONE, 1, 0.1f, 0.1f, 1f, 1), ChatColor.RED, 60, 7, 25, 2.5, 100, 25),
        PLASMA(new ParticleUtil(Particle.REDSTONE, 0.34f, 0.15f, 0.3f, 1f, 1), ChatColor.LIGHT_PURPLE, 60, 7, 30, 3, 125, 35);

        private final ParticleUtil particle;
        private final ChatColor color;
        private final int duration;
        private final int players;
        private final int range;
        private final double healthRegenPercent;
        private final double manaRegenPercent;
        private final int strength;

        PowerOrbType(ParticleUtil particle, ChatColor color, int duration, int players, int range, double healthRegenPercent, double manaRegenPercent, int strength) {
            this.particle = particle;
            this.color = color;
            this.duration = duration;
            this.players = players;
            this.range = range;
            this.healthRegenPercent = healthRegenPercent;
            this.manaRegenPercent = manaRegenPercent;
            this.strength = strength;
        }

        @Override
        public String toString() {
            return this.color + Functions.setTitleCase(this.name().replace("_", " "));
        }
    }

    private final PowerOrbType type;

    public PowerOrbDeployAbility(PowerOrbType type) {
        super(AbilityAction.RIGHT_CLICK,
                "Deploy",
                "Place an orb for " + ChatColor.GREEN + type.duration + "s " + ChatColor.GRAY + "buffing " +
                        "up to " + ChatColor.AQUA + type.players + " " + ChatColor.GRAY + "players within " + ChatColor.GREEN + type.range + " " +
                        ChatColor.GRAY + "blocks. Only one orb applies per player. " +
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
        return new PowerOrb();
    }

    private class PowerOrb implements PlayerAbilityRunnable {
        private UUID uuid = null;

        @Override
        public void run(PlayerAbilityUsage e) {
            uuid = UUID.randomUUID();

            Location location = e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection().setY(0)).subtract(0, 1, 0);

            ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            ItemStack head = new Item(Items.get(type.name() + "_POWER_ORB"));
            stand.setHelmet(head);
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setVisible(false);
            stand.setCustomName("PowerOrb");
            stand.addScoreboardTag("PowerOrb");
            SkyblockDragons.entitiesToKill.add(stand);

            Hologram hologram = HologramsAPI.createHologram(SkyblockDragons.plugin, location);
            TextLine line = hologram.appendTextLine(type + " " + ChatColor.YELLOW + (type.duration) + "s");

            UUID firstUUID = UUID.fromString(uuid.toString());
            Functions.Loop(type.duration * 20, 1L, i -> {
                if (!firstUUID.equals(uuid) || stand.isDead()) {
                    stand.remove();
                    hologram.delete();
                    return;
                }

                Location newLocation = stand.getLocation();
                if (i % 80 < 40) {
                    newLocation.add(0, 0.05, 0);
                } else {
                    newLocation.add(0, -0.05, 0);
                }
                newLocation.setYaw(newLocation.getYaw() + 15f);

                stand.teleport(newLocation);
                line.setText(type + " " + ChatColor.YELLOW + (type.duration - (i / 20)) + "s");
                hologram.teleport(stand.getLocation().add(0, 2.5, 0));

                Location particleLocation = stand.getLocation().add(0, 1.7, 0);
                particleLocation.add(stand.getLocation().getDirection().multiply(0.4));
                type.particle.spawn(particleLocation);

                for (PlayerSD player : e.getPlayer().getWorldSD().getPlayers()) {
                    Location playerLocation = player.getLocation();
                    playerLocation.setY(location.getY());
                    if (playerLocation.distance(location) <= type.range) {
                        if (player.getActivePowerOrb() != null && player.getActivePowerOrb().ordinal() > type.ordinal())
                            continue;

                        player.setActivePowerOrb(type);
                    } else
                        player.setActivePowerOrb(null);
                }
            }, i -> {
                stand.remove();
                hologram.delete();
            });
        }
    }
}

package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCostPercentage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

// TODO
public class PowerOrbDeployAbility extends ItemAbility implements ItemAbilityManaCostPercentage {
    private final PowerOrb.Type type;

    public PowerOrbDeployAbility(PowerOrb.Type type) {
        super(AbilityAction.NULL,
                "Deploy",
                "Place an orb for " + ChatColor.GREEN + "30s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player."
        ); this.type = type;
    }

    @Override
    public double getBaseManaCostPercentage(PlayerSD player) {
        return 50;
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {
            PlayerSD player = e.getPlayer();

            Location location = player.getLocation().clone().add(player.getLocation().getDirection().setY(0)).subtract(0, 1, 0);

            new PowerOrb(this.type, location);
        };
    }

    public static class PowerOrb extends BukkitRunnable {
        public enum Type {
            RADIANT(600, ChatColor.GREEN, new ParticleUil(Particle.REDSTONE, 0.1f, 1f, 0.1f, 1f, 1)),
            MANA_FLUX(600, ChatColor.BLUE, new ParticleUil(Particle.REDSTONE, 0.2f, 0.2f, 1, 1f, 1)),
            OVERFLUX(1200, ChatColor.RED, new ParticleUil(Particle.REDSTONE, 1, 0.1f, 0.1f, 1f, 1)),
            PLASMA(1200, ChatColor.LIGHT_PURPLE, new ParticleUil(Particle.REDSTONE, 0.34f, 0.15f, 0.3f, 1f, 1));

            private final long standsFor;
            private final ChatColor color;
            private final ParticleUil particle;

            Type(long standsFor, ChatColor color, ParticleUil particle) {
                this.standsFor = standsFor;
                this.color = color;
                this.particle = particle;
            }

            @Override
            public String toString() {
                return this.color + Functions.setTitleCase(this.name());
            }
        }

        public Type type;
        public ArmorStand armorStand;
        public Hologram hologram;
        public TextLine line;
        private int i;

        public PowerOrb(Type type, Location location) {
            this.type = type;

            this.armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            ItemStack head = new Item(Items.get(this.type.name() + "_POWER_ORB"));
            armorStand.setHelmet(head);
            armorStand.setGravity(false);
            armorStand.setInvulnerable(true);
            armorStand.setVisible(false);
            armorStand.setCustomName("PowerOrb");
            armorStand.addScoreboardTag("PowerOrb");
            SkyblockDragons.entitiesToKill.add(armorStand);

            hologram = HologramsAPI.createHologram(SkyblockDragons.plugin, location);
            line = hologram.appendTextLine(this.type.toString() + " " + ChatColor.YELLOW + (this.type.standsFor / 20) + "s");

            this.i = 0;
            this.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
        }

        @Override
        public void run() {
            if (armorStand.isDead() || i >= this.type.standsFor) {
                armorStand.remove();
                hologram.delete();
                cancel();
                return;
            }

            i++;
            Location newLocation = armorStand.getLocation();
            if (i % 80 < 40) {
                newLocation.add(0, 0.05, 0);
            } else {
                newLocation.add(0, -0.05, 0);
            }
            newLocation.setYaw(newLocation.getYaw() + 15f);

            armorStand.teleport(newLocation);
            line.setText(type.toString() + " " + ChatColor.YELLOW + ((this.type.standsFor - i) / 20) + "s");
            hologram.teleport(armorStand.getLocation().add(0, 2.5, 0));

            Location particleLocation = armorStand.getLocation().add(0, 1.7, 0);
            particleLocation.add(armorStand.getLocation().getDirection().multiply(0.4));
//        armorStand.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 0, 0.34, 0.15, 0.3);
            type.particle.spawn(particleLocation);
        };
    }
}